(** This module implements a type checker for Hopix. *)
open HopixAST
open HopixTypes

let initial_typing_environment = HopixTypes.initial_typing_environment

type typing_environment = HopixTypes.typing_environment

let type_error = HopixTypes.type_error

let located f x = f (Position.position x) (Position.value x)

(** [check_program_is_fully_annotated ast] performs a syntactic check
 that the programmer wrote sufficient type annotations for [typecheck]
 to run correctly. *)
let check_program_is_fully_annotated ast =
  (**
      We check the presence of type ascriptions on:
      - variables
      - tagged values patterns
   *)
  let rec program p = List.iter (located definition) p

  and definition _ = function
    | DefineValue vdef ->
      value_definition vdef
    | _ ->
      ()

  and value_definition = function
    (** A toplevel definition for a value. *)
    | SimpleValue (x, s, e) ->
       if s = None then missing_type_annotation (Position.position x);
       located expression e
    (** A toplevel definition for mutually recursive functions. *)
    | RecFunctions fs ->
       List.iter function_definition fs

  and function_definition = function
    | (f, s, FunctionDefinition (_, e)) ->
       if s = None then missing_type_annotation (Position.position f);
       located expression e

  and expression pos = function
    | Define (vdef, e) ->
       value_definition vdef;
       located expression e
    | Apply (a, b) ->
       List.iter (located expression) [a; b]
    | Tuple ts ->
       List.iter (located expression) ts
    | Record (fields, a) ->
       if a = None then type_error pos "A type annotation is missing.";
       List.iter (fun (_, e) -> located expression e) fields
    | TypeAnnotation ({ Position.value = Fun (FunctionDefinition (_, e)) },
                      _) ->
       located expression e
    | Fun (FunctionDefinition (_, _)) ->
       type_error pos "An anonymous function must be annotated."
    | Field (e, _) | TypeAnnotation (e, _) | Ref e | Read e ->
       located expression e
    | Sequence es ->
       List.iter (located expression) es
    | Tagged (_, a, es) ->
       if a = None then type_error pos "A type annotation is missing.";
       List.iter (located expression) es
    | For (_, e1, e2, e3) ->
       List.iter (located expression) (
           [ e1; e2; e3 ]
         )
    | IfThenElse (c, t, f) ->
       List.iter (located expression) [c; t; f]
    | Case (e, bs) ->
      located expression e;
      List.iter (located branch) bs
    | Assign (e1, e2) | While (e1, e2) ->
      located expression e1;
      located expression e2
    | Literal _ | Variable _ ->
      ()
  and pattern pos = function
    | PTypeAnnotation ({ Position.value = (PWildcard | PVariable _) }, _) ->
      ()
    | PRecord (fields, a) ->
       if a = None then type_error pos "A type annotation is missing.";
       List.iter (fun (_, p) -> located pattern p) fields
    | PTuple ps ->
       List.iter (located pattern) ps
    | PTypeAnnotation (p, _) ->
      located pattern p
    | PVariable _ | PWildcard ->
      missing_type_annotation pos
    | PTaggedValue (_, a, ps) ->
       if a = None then type_error pos "A type annotation is missing.";
       List.iter (located pattern) ps
    | POr ps | PAnd ps ->
      List.iter (located pattern) ps
    | PLiteral _ ->
      ()
  and branch _ = function
    | Branch (p, e) ->
      located pattern p;
      located expression e
  and missing_type_annotation pos =
    type_error pos "A type annotation is missing."
  in
  program ast

let invalid_instantiation pos given expected =
  type_error pos (
      Printf.sprintf
        "Invalid number of types in instantiation: \
         %d given while %d were expected." given expected
    )

(** [typecheck tenv ast] checks that [ast] is a well-formed program
    under the typing environment [tenv]. *)
let typecheck tenv ast : typing_environment =
  check_program_is_fully_annotated ast;

  let rec program p =
    List.fold_left (fun env x -> located (definition env) x) tenv p

  and definition tenv _ = function
    | DefineValue vdef ->
       value_definition tenv vdef

    | DefineType (t, ts, tdef) ->
       let ts = List.map Position.value ts in
       HopixTypes.bind_type_definition (Position.value t) ts tenv tdef

    | DeclareExtern (x, s) ->
       let s = located (type_scheme tenv) s in
       bind_value (Position.value x) s tenv

  and type_scheme tenv pos (ForallTy (ts, ty)) =
    let ts = List.map Position.value ts in
    let tenv = bind_type_variables pos tenv ts in
    Scheme (ts, internalize_ty tenv ty)

  and bind_type_variables pos tenv ts =
    List.iter (fun v ->
        if HopixTypes.is_type_variable_defined pos tenv v then
          type_error pos (
              Printf.sprintf
                "The type variable `%s' is already bound in the environment."
                (HopixPrettyPrinter.(to_string type_variable v))
            )
      ) ts;
    HopixTypes.bind_type_variables pos tenv ts

  and value_definition (tenv : typing_environment) = function
    | SimpleValue (x, Some s, e) ->
       let pos = Position.position s in
       let Scheme (ts, aty) as s = located (type_scheme tenv) s in
       let tenv' = bind_type_variables pos tenv ts in
       check_expression_monotype tenv' aty e;
       bind_value (Position.value x) s tenv

    | SimpleValue (_, _, _) ->
       assert false (* By check_program_is_fully_annotated. *)

    | RecFunctions fs ->
       recursive_definitions tenv fs

  and recursive_definitions tenv recdefs =
    let tenv =
      List.fold_left (fun tenv (f, fs, _) ->
          match fs with
          | None ->
             assert false  (* By check_program_is_fully_annotated. *)
          | Some fs ->
             let f = Position.value f in
             let fs = located (type_scheme tenv) fs in
             let fs = refresh_type_scheme fs in
             bind_value f fs tenv
        ) tenv recdefs
    in
    List.iter (fun (f, fs, d) ->
        match fs with
        | None ->
           assert false
        | Some fs ->
           let pos = Position.position f in
           let fs = located (type_scheme tenv) fs in
           check_function_definition pos tenv fs d
      ) recdefs;
    tenv

  (** [check_function_definition tenv fdef] checks that the
      function definition [fdef] is well-typed with respect to the
      type annotations written by the programmer. We assume that
      [tenv] already contains the type scheme of the function [f]
      defined by [fdef] as well as all the functions which are
      mutually recursively defined with [f]. *)
  and check_function_definition pos tenv aty = function
    | FunctionDefinition (p, e) ->
       match aty with
       | Scheme (ts, ATyArrow (_, out)) ->
          let tenv = bind_type_variables pos tenv ts in
          let tenv, _ = located (pattern tenv) p in
          check_expression_monotype tenv out e
       | _ ->
          type_error pos "A function must have an arrow type."

  (** [check_expected_type pos xty ity] verifies that the expected
      type [xty] is syntactically equal to the inferred type [ity]
      and raises an error otherwise. *)
  and check_expected_type pos xty ity =
    if xty <> ity then
      type_error pos (
          Printf.sprintf "Type error:\nExpected:\n  %s\nGiven:\n  %s\n"
            (print_aty xty) (print_aty ity)
        )

  (** [check_expression_monotype tenv xty e] checks if [e] has
      the monotype [xty] under the context [tenv]. *)
  and check_expression_monotype tenv xty e : unit =
    let pos = Position.position e in
    let ity = located (type_of_expression tenv) e in
    check_expected_type pos xty ity

  (** [type_of_expression tenv pos e] computes a type for [e] if it exists. *)
  and type_of_expression tenv pos : expression -> aty =
failwith "Students! This is your job!"

  and patterns tenv = function
    | [] ->
       tenv, []
    | p :: ps ->
       let tenv, ty = located (pattern tenv) p in
       let tenv, tys = patterns tenv ps in
       tenv, ty :: tys

  (** [pattern tenv pos p] computes a new environment completed with
      the variables introduced by the pattern [p] as well as the type
      of this pattern. *)
  and pattern tenv pos =
  failwith "Students! This is your job!"
  in
  program ast


let print_typing_environment = HopixTypes.print_typing_environment

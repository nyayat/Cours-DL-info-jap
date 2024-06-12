(** From Hopix to Hobix *)

module Source = Hopix
module Target = Hobix

(** The compilation environment.
    ———————————————————————————–

    To translate a program written in a source language into another
    semantically equivalent program written in a target language, it
    is convenient to carry some information about the correspondence
    between the two programs along the process. The compilation
    environment is meant to that.

    In this particular pass, we want to remember an assignment of
    integers to constructor and label identifiers. Therefore, the
    compilation environment is composed of two maps representing these
    assignments. The environment is populated each time we cross a
    type definitions while it is read each time we translate language
    constructions related to record and tagged values.
*)

module ConstructorMap = Map.Make (struct
  type t = HopixAST.constructor
  let compare = compare
end)

module LabelMap = Map.Make (struct
  type t = HopixAST.label
  let compare = compare
end)

type environment = {
  constructor_tags : Int64.t ConstructorMap.t;
  label_positions  : Int64.t LabelMap.t;
}

let initial_environment () = {
  constructor_tags = ConstructorMap.empty;
  label_positions  = LabelMap.empty;
}

let index_of_constructor env k =
  ConstructorMap.find k env.constructor_tags

let position_of_label env l =
  LabelMap.find l env.label_positions

(** Code generation
    ———————————————

    A compilation pass produces code. We could directly
    write down caml expressions made of applications of
    HobixAST constructors. Yet, the resulting code would
    be ugly...

    A better way consists in defining functions that build
    Hobix AST terms and are convenient to use. Here are a
    list of functions that may be convenient to you when
    you will implement this pass.

*)

(** [fresh_identifier ()] returns a fresh identifier, that is
    an identifier that has never been seen before. *)
let fresh_identifier =
  let r = ref 0 in
  fun () -> incr r; HobixAST.Id ("_h2h_" ^ string_of_int !r)

(** [def w (fun x -> e)] returns an abstract syntax tree of
    the form:

    val x = w; e

    where [x] is chosen fresh.
*)
let def w f =
  let x = fresh_identifier () in
  HobixAST.(Define (SimpleValue (x, w), f x))

(** [defines [d1; ..; dN] e] returns an abstract syntax tree of
    the form:

    val d1;
    ..
    val dN;
    e

*)
let defines =
  List.fold_right (fun (x, xe) e ->
      HobixAST.(Define (SimpleValue (x, xe), e)))

(** [seq s1 s2] is

    val _ = s1;
    s2

*)
let seq s1 s2 =
  HobixAST.(Define (SimpleValue (fresh_identifier (), s1), s2))

(** [htrue] represents the primitive true in Hobix. *)
let htrue =
  HobixAST.(Variable (Id "true"))

(** [seqs [s1; ...; sN] is

    val _ = s1;
    ...
    val _ = s(N - 1);
    sN
*)
let rec seqs = function
  | [] -> assert false
  | [e] -> e
  | e :: es -> seq e (seqs es)

(** [is_equal e1 e2] is the boolean expression [e1 = e2]. *)
let is_equal l e1 e2 =
  let equality = HobixAST.(match l with
    | LInt _ -> "`=?`"
    | LString _ -> "equal_string"
    | LChar _ -> "equal_char"
  ) in
  HobixAST.(Apply (Variable (Id equality), [e1; e2]))

(** [conj e1 e2] is the boolean expression [e1 && e2]. *)
let conj e1 e2 =
  HobixAST.(Apply (Variable (Id "`&&`"), [ e1; e2 ]))

(** [conjs [e1; ..; eN]] is the boolean expression [e1 && .. && eN]. *)
let rec conjs = function
  | [] -> htrue
  | [c] -> c
  | c :: cs -> conj c (conjs cs)

(** [component x i] returns [x[i]] where x is an Hobix expression
    denoting a block. *)
let component x i =
  failwith "Students! This is your job!"


let located  f x = f (Position.value x)
let located' f x = Position.map f x

let is_binop = function
  | "`+`" | "`-`" | "`*`" | "`/`"
  | "`=?`" | "`>=?`" | "`<=?`" | "`>?`" | "`<?`"
  | "`||`" | "`&&`" ->
     true
  | _ ->
     false

let arity_of_type = HopixAST.(function
  | TyVar _           -> 0
  | TyCon (_, _)     -> 0
  | TyArrow (_, _) -> 1
  | TyTuple _ -> 0
)

let eta2 f =
     failwith "Students! This is your job!"

(** [program env p] turns an Hopix program into an equivalent
    Hobix program. *)
let rec program env p =
  let env, defs = ExtStd.List.foldmap definition' env p in
  (List.flatten defs, env)

(** Compilation of Hopix toplevel definitions. *)
and definition' env p =
  definition env (Position.value p)

and definition env = HobixAST.(function
  | HopixAST.DeclareExtern (x, s) ->
    let { Position.value = HopixAST.ForallTy (_, ty); _ } = s in
    let ty = Position.value ty in
    env, [DeclareExtern (located identifier x, arity_of_type ty)]

  | HopixAST.DefineValue vd ->
     let vd = value_definition env vd in
     env, [DefineValue vd]

  | HopixAST.DefineType (_, _, tydef) ->
    type_definition env tydef, []
)

and value_definition env = function
  | HopixAST.SimpleValue (x, _, e) ->
     HobixAST.SimpleValue (located identifier x, located (expression env) e)
  | HopixAST.RecFunctions fs ->
     HobixAST.RecFunctions (List.map (function_binding env) fs)

and function_binding env (f, _, fdef) =
  (located identifier f, function_definition env fdef)

and function_definition env (HopixAST.FunctionDefinition (x, e)) =
  let y = HopixASTHelper.fresh_identifier () in
  let wpos t = Position.(with_pos (position x) t) in
  let e = HopixAST.(
      Case (wpos (Variable (wpos y, None)),
            [
              wpos (Branch (x, e))
            ])
  )
  in
  (HobixAST.Fun ([identifier y], expression env e))

and identifier (HopixAST.Id x) =
  HobixAST.Id x

(** Compilation of Hopix expressions. *)
and expression env = HobixAST.(function
  | HopixAST.Variable ({ value = HopixAST.Id x }, _) when is_binop x ->
     eta2 (HobixAST.Id x)

  | HopixAST.Variable (x, _) ->
    Variable (located identifier x)

  | HopixAST.Tagged (k, _, es) ->
    failwith "Students! This is your job!"

  | HopixAST.Case (e, bs) ->
    failwith "Students! This is your job!"

  | HopixAST.Ref e ->
    failwith "Students! This is your job!"

  | HopixAST.Read r ->
    failwith "Students! This is your job!"

  | HopixAST.Assign (r, v) ->
    failwith "Students! This is your job!"

  | HopixAST.While (c, b) ->
    HobixAST.While (located (expression env) c,
                    located (expression env) b)

  | HopixAST.Apply (a, b) ->
    Apply (located (expression env) a,
           [located (expression env) b])

  | HopixAST.Literal l ->
    Literal (located literal l)

  | HopixAST.Define (vd, e) ->
    Define (value_definition env vd, located (expression env) e)

  | HopixAST.TypeAnnotation (e, _) ->
    located (expression env) e

  | HopixAST.IfThenElse (c, t, f) ->
     let f = located (expression env) f in
     HobixAST.IfThenElse (located (expression env) c,
                          located (expression env) t,
                          f)

  | HopixAST.Record (fs, _) ->
    failwith "Students! This is your job!"

  | HopixAST.Tuple ts ->
    failwith "Students! This is your job!"

  | HopixAST.Field (e, l) ->
    failwith "Students! This is your job!"

  | HopixAST.Sequence es ->
     seqs (List.map (located (expression env)) es)

  | HopixAST.For (x, start, stop, e) ->
    failwith "Students! This is your job!"

  | HopixAST.Fun fdef ->
    failwith "Students! This is your job!"
)


(** [expands_or_patterns branches] returns a sequence of branches
    equivalent to [branches] except that their patterns do not contain
    any disjunction. {ListMonad} can be useful to implement this
    transformation. *)
and expands_or_patterns branches =
 failwith "Students! This is your job!"


(** [pattern env scrutinee p] returns an HopixAST expression
    representing a boolean condition [c] and a list of definitions
    [ds] such that:

    - [c = true] if and only if [p] matches the [scrutinee] ;
    - [ds] binds all the variables that appear in [p].

    Precondition: p does not contain any POr.
 *)
and pattern env scrutinee p = HobixAST.(
    failwith "Students! This is your job!"
)

and literal = HobixAST.(function
  | HopixAST.LInt x -> LInt x
  | HopixAST.LString s -> LString s
  | HopixAST.LChar c -> LChar c
)

(** Compilation of type definitions. *)
and type_definition env t =
  failwith "Students! This is your job!"

(** Here is the compiler! *)
let translate source env =
  program env source

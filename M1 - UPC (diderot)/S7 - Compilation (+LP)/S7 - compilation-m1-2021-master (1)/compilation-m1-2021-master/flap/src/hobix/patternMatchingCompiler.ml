(** This module implements an optimized compilation of pattern-matching.

    It is based on the article

    "Compiling Pattern Matching to Good Decision Trees"

    written by Luc Maranget
    and published in the proceedings of the ML workshop 2005.

*)
open Position

module S = HopixAST
module T = HobixAST

(** A path is a sequence of indices which allow for accessing
    a component which may be deeply nested inside a block. *)
type path = int list

(** Each identifier bound by the pattern will finally be associated to
    a path. *)
type binding = T.identifier * path

(** A pattern-matching matrix has a row for each possible
    case and as many columns as the number of components of
    the matched value. *)
type matrix = row list

and row = S.pattern list * binding list * T.expression

(** [nb_columns m] returns the number of columns of [m]. *)
let nb_columns = function
  | [] ->
     0
  | (ps, _, _) :: rows ->
     let n = List.length ps in
     assert (List.for_all (fun (ps, _, _) -> List.length ps = n) rows);
     n

(** [string_of_path occ] produces a human-readable version of [occ]. *)
let string_of_path occ =
  String.concat "." (List.rev_map string_of_int occ)

(** [string_of_bindings bs] produces a human-readable version of [bs]. *)
let string_of_bindings bs =
  String.concat ", " (
      List.map (fun (T.Id x, p) ->
          Printf.sprintf "%s = %s" x (string_of_path p)
        ) bs)

(** [string_of_matrix m] produces a human-readable version of [m]. *)
let string_of_matrix m =
  let csizes = Array.make (nb_columns m) 0 in
  let string_of_pattern i p =
    let s = HopixPrettyPrinter.(to_string pattern p) in
    csizes.(i) <- max csizes.(i) (String.length s);
    s
  in
  let complete_pattern i p =
    p ^ String.make (csizes.(i) - String.length p) ' '
  in
  let string_of_expression e =
    HobixPrettyPrinter.(to_string expression e)
  in
  let b = Buffer.create 13 in
  List.map (fun (ps, bs, e) ->
      (List.mapi string_of_pattern ps,
       string_of_bindings bs,
       string_of_expression e)) m
  |> List.iter (fun (ps, bs, e) ->
         Buffer.add_string b (
             String.concat " " (List.mapi complete_pattern ps)
             ^ " -> " ^ bs ^ " in " ^ e ^ "\n"
           )
       );
  Buffer.contents b

(** We may observe if a value is a tagged value with a specific
    constructor or is equal to a given literal. *)
type observation =
  | CTag of S.constructor
  | CLit of S.literal

(** [head_constructors m] returns the list of observations of the
   first column of [m] without repetition. Each observation comes with
   its arity. A literal has an arity of 0 and constructor has for arity
   the number of arguments it is applied to. This function assumes
   that the patterns in the matrix are well-typed, hence we can deduce
   the arity of the constructors directly from their application. *)
let head_constructors : matrix -> (observation * int) list =
  failwith "Students! This is your job!"

(** [specialize occ c arity m] returns the matrix [m] in which rows that
    do not match the observation [c] are removed and the others
    have new columns to match the subcomponents of [c].
    - [m] must have at least one row and one column.
    - [arity] is the arity of [c].
    - [occ] is the path to the scrutinee's component matched by the
      first column of [m].
*)
let specialize occ c arity (m : matrix) =
  failwith "Students! This is your job!"

(** [default occ m] returns the default matrix of [m], that is the matrix
    corresponding to the remaining tests to do if the default case of the
    first column of [m] has been chosen. *)
let default occ (m : matrix) =
  failwith "Students! This is your job!"

(** [split n] returns the list of occurrences [occ.0; occ.1; ..;
    occ.(n - 1)]. *)
let split n occ =
  failwith "Students! This is your job!"

(** [swap_columns i m] returns a new matrix which is [m] in which
    column 0 and column i have been exchanged. *)
let swap_columns i m =
  failwith "Students! This is your job!"

(** [swap_occurences i occs] returns a new list of occurrences in
    which the occ numbered 0 and the occ numbered i have been
    exchanged. *)
let swap_occurences i occs =
  failwith "Students! This is your job!"

type decision_tree =
  | Fail
  | Leaf   of binding list * T.expression
  | Switch of path * (observation * decision_tree) list * decision_tree option
  | Swap   of int * decision_tree

let string_of_constructor = function
  | CTag (S.KId s) -> s
  | CLit l -> HopixPrettyPrinter.(to_string literal l)

(** [string_of_decision_tree t] produces a human-readable version of [t]. *)
let string_of_decision_tree t =
  let b = Buffer.create 13 in
  let offset = 2 in
  let show indent s =
    Buffer.add_string b (String.make (offset * indent) ' ' ^ s ^ "\n")
  in
  let rec aux prefix indent = function
  | Fail ->
     show indent (prefix ^ "fail")
  | Leaf (bs, e) ->
     show indent (
         prefix
         ^ string_of_bindings bs
         ^ HobixPrettyPrinter.(to_string expression e))
  | Switch (occ, ts, default) ->
     show indent (prefix ^ string_of_path occ ^ "?");
     List.iter (fun (c, t) -> aux (string_of_constructor c) (indent + 1) t) ts;
     begin match default with
     | None -> ()
     | Some t -> aux "default: " (indent + 1) t
     end
  | Swap (i, t) ->
     aux ("swap" ^ string_of_int i ^ ":") (indent + 1) t
  in
  aux "" 0 t;
  Buffer.contents b


(** [decision_tree_of_matrix m] returns a decision tree that
    implements [m] efficiently. *)
let decision_tree_of_matrix (m : matrix) : decision_tree =
  failwith "Students! This is your job!"

(** [compile_decision_tree index_of_constructor x t] returns an
    expression in Hobix which corresponds to the application of [t] to
    [x]. [index_of_constructor k] returns the integer which represents
    the constructor k in Hobix. *)
let compile_decision_tree (index_of_constructor : S.constructor -> int) x t =
  failwith "Students! This is your job!"

(** [translate branches x] returns an [Hobix] expression which implements
    an efficient pattern matching of [x] with the [branches]. *)
let translate (index_of_constructor : S.constructor -> int) bs x =
  let matrix = List.map (fun (p, e) -> ([p.value], [], e)) bs in
  let decision_tree = decision_tree_of_matrix matrix in
  compile_decision_tree index_of_constructor x decision_tree

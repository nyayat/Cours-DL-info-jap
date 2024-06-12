open RetrolixAST
open RetrolixLivenessAnalysis
open RetrolixUtils

(** Interference graph. *)

(** In the interference graph, there will be two kinds of edges: *)
type relation =
  (** If two variables cannot be represented in the same register
      because their liveness ranges intersect, we say that they are in
      a conflict relation. *)
  | Conflict

  (** If two variables are related by a MOVE instruction, we will try
      to put them in the same register, we say that they are in
      a preference relation. *)
  | Preference

(** Interference graph. *)

module EdgeLabel = struct
  type t = relation
  let compare = compare
  let all = [Conflict; Preference]
  let to_string = function Conflict -> "c" | Preference -> "p"
end

module NodeLabel = struct
  type t = RetrolixAST.lvalue
  let compare = compare
  let to_string = RetrolixUtils.string_of_lvalue
end

module InterferenceGraph = Graph.Make (EdgeLabel) (NodeLabel)

type t = InterferenceGraph.t

(** [add_node g n] inserts [n] in [g] if it is not already there. *)
let add_node g n =
  try InterferenceGraph.add_node g [n]
  with InterferenceGraph.InvalidNode -> g

(** [add_relation g c n1 n2] creates an edge of kind [c] between [n1]
   and [n2]. This function inserts [n1] and [n2] in [g] if needed.*)
let add_relation g c n1 n2 =
  assert (n1 <> n2);
  let g = add_node g n1 in
  let g = add_node g n2 in
  InterferenceGraph.add_edge g n1 c n2

(** [are_in_relation g c] is a predicate returning [true] if [n1]
    and [n2] are in relation [c] in [g]. *)
let are_in_relation g c n1 n2 =
  InterferenceGraph.are_connected g n1 c n2

(** The empty graph. *)
let empty_graph = InterferenceGraph.empty

(**

   To construct the interference graph:

   1. At any non-move instruction that defines variable a (where
   live-out variables are b1, ..., bj) add interference edges (a, b1),
   ..., (a, bj).

   2. At a move instruction a ← c (where variables b1, ..., bj are
   live-out) add interference edges (a, b1), ..., (a, bj) for any bi
   that is not the same as c. Besides, add an preference edge (a, c)
   if a and c are not in interference. Notice that interference
   overrides preference: if a subsequel instruction implies an
   interference between a and c, the preference relation is removed.

   [forbidden] represents the list of global variables: they must not be
   colorized. Hence, they more or less behave as the hardware registers.

*)
let interference_graph forbidden b liveness : t =
   failwith "Student! This is your job!"

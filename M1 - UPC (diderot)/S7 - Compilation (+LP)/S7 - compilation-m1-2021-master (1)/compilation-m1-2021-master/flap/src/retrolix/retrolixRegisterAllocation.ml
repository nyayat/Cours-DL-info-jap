(**

   The register allocation translates a Retrolix program into an
   equivalent Retrolix program that uses hardware registers as much as
   possible to hold intermediate results.

   Register allocation is done in two steps:

   - a static analysis called "Liveness Analysis of Variables" is
   performed to compute a graph. This graph overapproximates the interference
   relation of program variables, i.e. the intersection between the
   live ranges of variables. The nodes of the graph are the program
   variables and, in this graph, a node for 'x' and a node 'y' are
   connected iff 'x' and 'y' are in interference.

   - a graph coloring algorithm is executed on the interference graph:
   if two variables live at the same time, then their values cannot
   be carried by the same register ; thus, it suffices to use a different
   color for their nodes. Graph coloring is NP-complete. Yet, we will
   use a simple recursive algorithm that provides good results in
   practice.

*)

open RetrolixAST
open RetrolixUtils
open RetrolixInterferenceGraph

(**

    Register allocation is an optimization.

    Hence, we register this translation as such in the compiler.

*)

let activated = ref false

module Source = Retrolix

let shortname = "regalloc"

let longname = "register allocation"

(**

   Coloring, definitions and operators.

*)

type colorization = Color of register | OnStack | Undecided

type coloring = colorization LValueMap.t

let color_of_register r =
  match RetrolixUtils.register r with `Register r -> r | _ -> assert false

let colors =
  List.map color_of_register X86_64_Architecture.allocable_registers

let is_precolored_node n =
  match n with
    | `Register r -> List.mem r colors
    | _ -> false

let nb_colors = List.length colors

let colorization coloring x =
  try LValueMap.find x coloring with Not_found -> Undecided

let assign_colorization coloring x c =
  LValueMap.add x c coloring

(** In the initial coloring, hardware registers are colored by themselves. *)
let initial_coloring : coloring =
  List.fold_left (fun c r ->
      let color = Color (color_of_register r)
      and register = RetrolixUtils.register r in
      assign_colorization c register color)
    LValueMap.empty
    X86_64_Architecture.all_registers

let string_of_colorization = function
  | OnStack -> "On stack"
  | Color r -> RetrolixUtils.string_of_register r
  | Undecided -> "undecided"

let string_of_coloring coloring =
  LValueMap.bindings coloring |>
  List.map (fun (x, c) ->
      Printf.sprintf "%s -> %s\n"
        (string_of_lvalue x) (string_of_colorization c)
  ) |> String.concat ""

(** [build_variable_relations forbidden b] computes the interference
    graph for the block [b], assuming that coloring global variables
    is [forbidden]. *)
module G = RetrolixInterferenceGraph.InterferenceGraph
let build_variable_relations forbidden b : G.t =
  RetrolixLivenessAnalysis.process b |>
  RetrolixInterferenceGraph.interference_graph forbidden b

(** [rewrite_block coloring b] rewrites [b] to use more hardware
    registers as described by [coloring]. *)
let rewrite_block coloring (xs, is) =
  let lv : lvalue -> lvalue = function
    | `Variable (Id _) as v ->
       begin match colorization coloring v with
       | Color r -> `Register r
       | OnStack -> v
       | Undecided -> v
       end
    | l -> l
  in
  let rv = function
    | `Immediate l -> `Immediate l
    | #lvalue as l -> (lv l :> rvalue)
  in
  List.(
    let var x = `Variable x in
    let xs = filter (fun x -> colorization coloring (var x) = OnStack) xs in
    let is = map (fun (l, i) -> (l, RetrolixUtils.map_on_value lv rv i)) is in
    (xs, is)
  )

(**

   Graph simplification
   ====================

   Given an interference graph, there are three possible cases:

   1. The graph only contains nodes that are not colorable because
      they are hardware registers or global variables for instance.
      The [initial_coloring] is fine for this graph.

   2. There is a simplifiable node in the graph, that is a node whose
      degree is strictly less than the number of available [colors].

   3. There are no simplifiable nodes in the graph. The coloring
      algorithm must try different from simplification to continue
      its work.

*)
type simplify_result =
  | PrecoloredGraph
  | SimplifiableNode of NodeLabel.t
  | NoSimplifiableNode

(** [simplify uncolorable g] observes [g] to determine the
   [simplify_result]. [uncolorable] is a predicate to filter
   nodes that are not colorable. *)
let simplify (uncolorable : NodeLabel.t -> bool) g : simplify_result =
   failwith "Student! This is your job!"

(**

   Variable spilling
   =================

   At some point, if there is no more simplification (or coalescing)
   to do, we must choose a variable that can be potentially spilled,
   that is allocated [OnStack]. As graph coloring is NP-complete,
   there is no way to quickly compute a local optimal choice. Yet,
   considering the graph and the instructions, some reasonable
   heuristic can be defined.

*)

(** [pick_spilling_candidate uncolorable g b] returns a node to
    consider for spilling. *)
let pick_spilling_candidate (uncolorable : NodeLabel.t -> bool) g _
: NodeLabel.t =
   failwith "Student! This is your job!"

(** [colorize_block_variables_naively forbidden b] rewrites [b] to
    use more hardware registers if that is possible. [forbidden]
    is a list of variables that cannot be stored in hardware registers. *)
let colorize_block_variables_naively forbidden b =
   failwith "Student! This is your job!"

(**

   Coalescence
   ===========

   We can coalesce two nodes if they are not in conflict and if Briggs'
   or George's criterion is satisfied.

*)
let can_coalesce g n1 n2 =
   failwith "Student! This is your job!"

(** [colorize_block_variables_meticulously forbidden b] performs
    register allocation on [b] trying to optimize variable copy. *)
let colorize_block_variables_meticulously forbidden b =
   failwith "Student! This is your job!"

(**

   Putting all together.

*)
let translate_block forbidden b = Options.(
  match get_regalloc_variant () with
  | Naive ->
     colorize_block_variables_naively forbidden b
  | Realistic ->
     colorize_block_variables_meticulously forbidden b
  )

let translate p =
  let variables = List.map (fun x -> `Variable  x) in
  let globals = variables (RetrolixUtils.global_variables p) in
  let rec program ds =
    List.map definition ds
  and definition = function
    | DValues (xs, b) ->
       DValues (xs, translate_block globals b)
    | DFunction (f, xs, b) ->
       DFunction (f, xs, translate_block (variables xs @ globals) b)
    | DExternalFunction f ->
       DExternalFunction f
  in
  program p |> RetrolixKillMove.kill_moves

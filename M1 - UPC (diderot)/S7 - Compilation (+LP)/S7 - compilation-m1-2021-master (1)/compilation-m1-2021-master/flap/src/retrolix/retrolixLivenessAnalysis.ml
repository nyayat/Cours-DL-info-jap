(**

   Liveness Analysis
   =================

   Liveness analysis is a *data flow* analysis. This means that it
   overapproximates the set of possible values that can get involved
   at each program point. The notion of "set of possible values" here
   should be understood in a very broad set as it usually characterize
   an abstract semantic notion like "the definitions that are
   available", "the variables that are alive", ... etc.

   To do that, the analysis works on the control-flow graph (CFG) (i)
   by defining a *transfer function* for each node that
   overapproximates the effects of the node instruction on the values
   ; (ii) by refining the overapproximation iteratively until a
   fixpoint is reached.

   More precisely, a transfer function is defined by two functions
   _IN_ and _OUT_ such that for each program point ℓ, IN(ℓ) is the set
   of possible values entering ℓ and OUT(ℓ) is the set of possible
   values leaving ℓ. If the _domain_ of the transfer function is equiped
   with a partial order with no infinite descending chain and if
   _IN_ and _OUT_ are monotonic with respect to this partial order,
   then by Kleene-Knaster-Tarski's theorem, there exist a fixpoint.

   For liveness analysis, the transfer functions are defined as follows:

   1. The analysis abstract domain contains sets of alive variables.
      The partial order is ⊆. Given that there is only a finite number
      of variables, there is no infinite descending chain.

   2. x ∈ IN(ℓ)
      if x ∈ (OUT(ℓ) \ DEF(ℓ)) ∨ (∃ ℓ' -> ℓ, x ∈ OUT(ℓ')) ∨ x ∈ USE(ℓ)

      x ∈ OUT(ℓ)
      if ∃ ℓ', ℓ -> ℓ', x ∈ IN(ℓ')

      where:
      - USE(ℓ) is the set of variables possibly read at ℓ.
      - DEF(ℓ) is the set of variables possibly written at ℓ.

      or equivalently, removing the redundancy between IN and OUT:

      IN(ℓ)  = USE(ℓ) ∪ (OUT(ℓ) ∖ DEF(ℓ))
      OUT(ℓ) = ⋃_{s ∈ successors (ℓ)} IN(s)

   Notice that OUT(ℓ) only depends on the values IN(s) obtained from
   its successors. This is a characteristic of *backward data flow
   analysis*. We will consider *forward* analyses is a forthcoming
   optimization.

*)

open RetrolixAST
open RetrolixUtils

(**

   The result of the liveness analysis is a mapping from program
   points to pairs of sets of variables.

*)
type liveness_analysis_result = {
  live_in  : LSet.t LabelMap.t;
  live_out : LSet.t LabelMap.t;
}

let empty_results =
  {
    live_in  = LabelMap.empty;
    live_out = LabelMap.empty;
  }

let string_of_results r =
  Printf.sprintf
    "IN:\n%s\nOUT:\n%s\n"
    (string_of_lmap r.live_in)
    (string_of_lmap r.live_out)

(** [def i] returns the variables defined by the instruction [i]. *)
let def i =
  failwith "Student! This is your job!"

(** [use i] returns the variables used by [i]. *)
let use i =
  failwith "Student! This is your job!"

(** [instructions_of_labels b] returns a function [instruction_of_label]
    such that [instruction_of_label l] returns the instruction labelled by
    [l] in the block [b]. *)
let instructions_of_labels ((_, is) : block) =
  let m = LabelMap.(List.fold_left (fun m (l, i) -> add l i m) empty is) in
  fun l -> try LabelMap.find l m with Not_found -> assert false

(** [liveness_analysis b] returns the liveness analysis of block [b].

   There are many ways to implement this analysis, but some
   implementations will converge faster than others! Let us recall
   what we said during the course:

   1. A backward analysis converges faster by traversing the CFG
      from exit to entry.

   2. A fixpoint computation is better implemented using a *work list*
      that maintains the nodes whose analysis may need a refinement.

   Typically, in the case of the liveness analysis, when considering a
   node [n], we compute [IN(n)] and if it has changed we must update
   [OUT(p)] for all predecessors of [n] and consider these predecessors
   on more time. (This again suggests a backward traversal of the CFG.)

*)
let liveness_analysis b : liveness_analysis_result =
  failwith "Student! This is your job!"


(**

   Some debugging functions.

*)

let debug_liveness b results =
  if Options.get_verbose_mode () then RetrolixPrettyPrinter.(
    let get_decoration space m l =
      let s = try LabelMap.find l m with Not_found -> LSet.empty in
      [PPrint.string ("{ " ^ string_of_lset s ^ " }")]
      @ (if space then [PPrint.empty] else [])
    in
    let decorations = {
        pre = get_decoration false results.live_in;
        post = get_decoration true results.live_out
    }
    in
    let p = ExtPPrint.to_string (block decorations) b in
    Printf.eprintf "Liveness:\n%s\n" p;
  );
  results

let process b =
  liveness_analysis b |> debug_liveness b

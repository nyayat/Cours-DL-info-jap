(** This module removes all useless MOV in a program. *)

open RetrolixAST

(** [kill_moves p] produces a program [p] in which we have deleted
    all the moves whose destination and source are equal. *)
let kill_moves p =
  let rec definition = function
    | DValues (xs, b) ->
      DValues (xs, block b)
    | DFunction (f, xs, b) ->
      DFunction (f, xs, block b)
    | x ->
      x
  and block (locals, instructions) =
    (locals, List.map (fun (l, i) -> (l, instruction i)) instructions)
  and instruction = function
    | Assign (x, Copy, [r]) when (x :> rvalue) = r ->
      Comment "Killed move"
    | i ->
      i
  in
  List.map definition p

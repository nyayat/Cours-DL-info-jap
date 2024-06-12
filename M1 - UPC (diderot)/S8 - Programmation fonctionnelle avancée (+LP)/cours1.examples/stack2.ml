(* Implementation *)
module OrdIntStack : Stack =
struct
  type elt = int
  type t = int list
  let rec push x = function [] -> [x]
    | h::t as l when x < h -> x::l
    | h::t -> h::push x t
  let pop = function [] -> raise Not_found
    | h::t -> (h,t)
  let is_empty s = s = []
  let empty () = []
end;;

open OrdIntStack
let x = push 42 (empty());;
(* ou est l'erreur ? *)

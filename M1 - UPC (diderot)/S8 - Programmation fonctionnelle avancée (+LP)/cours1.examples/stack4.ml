(* signature pour des types ordonnes *)
module type Comparable = sig
 type elt
 val compare : elt -> elt -> int
end;;
(* module parametrique *)
module OrdStack (T:Comparable) :
  Stack with type elt = T.elt =
struct
  type elt = T.elt
  type t = elt list
  let rec push x = function [] -> x::[]
    | h::t as l when T.compare x h < 0 -> x::l
    | h::t -> h::push x t
  let pop = function [] -> raise Not_found
    | h::t -> (h,t)
  let is_empty s = s = []
  let empty () = []
end;;

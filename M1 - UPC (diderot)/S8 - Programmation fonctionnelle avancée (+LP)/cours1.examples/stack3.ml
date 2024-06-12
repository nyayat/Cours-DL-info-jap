(* structure d'entiers *)
module T = struct
  type elt = int
  let compare x y = x-y
end;;

(* utiliser "with" pour faire le lien *)
module OrdTStack : Stack with type elt = T.elt =
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

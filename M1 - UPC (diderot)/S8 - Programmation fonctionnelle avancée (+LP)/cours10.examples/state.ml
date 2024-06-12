(* The idealized imperative state we handle here :
   an infinite array of integers, indexed by integers. *)
module type STATE = sig
  type t
  (* Initial state, containing 0 everywhere *)
  val init : t
  (* Reading the state at some position *)
  val get : t -> int -> int
  (* (set s i x) is s except that it contains x at i *)
  val set : t -> int -> int -> t
end

(* A possible representation, via functions *)
module State : STATE = struct
 type t = int -> int
 let init = fun _ -> 0
 let get s i = s i
 let set s i x = fun j -> if i=j then x else s j
end

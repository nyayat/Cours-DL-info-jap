(* signature d'un module pour les piles triees *)
module type Stack = sig
 type elt
 type t
 val push : elt -> t -> t
 val pop : t -> elt * t
 val is_empty : t -> bool
 val empty : unit -> t
end;;

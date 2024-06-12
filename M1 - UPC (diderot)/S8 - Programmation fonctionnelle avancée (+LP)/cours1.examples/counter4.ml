module type MultiCounter =
   sig
    type t
    val create : unit -> t
    val incr : t -> unit
    val show : t -> int
   end;;

module MultiCounter: MultiCounter =
 struct
   type t = int ref
   let create () = ref 0
   let incr c = c:= !c+1
   let show c = !c
 end
;;

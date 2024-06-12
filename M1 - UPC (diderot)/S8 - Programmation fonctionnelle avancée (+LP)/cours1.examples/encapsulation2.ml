(* equivalent *)
module type CounterItf =
   sig
    val incr : unit -> unit
    val show : unit -> int
   end;;
module CounterHide : CounterItf =
   struct
     let c = ref 0
     let incr () = c:= !c+1
     let show () = !c
   end;;
CounterHide.c;;
CounterHide.show() ;;

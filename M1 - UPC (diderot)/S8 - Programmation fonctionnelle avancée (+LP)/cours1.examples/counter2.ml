module type CounterFullItf =
  sig
    val c : int ref
    val incr : unit -> unit
    val show : unit -> int
  end

module Counter : CounterFullItf =
  struct
    let c = ref 0
    let incr () = c:= !c+1
    let show () = !c
  end;;

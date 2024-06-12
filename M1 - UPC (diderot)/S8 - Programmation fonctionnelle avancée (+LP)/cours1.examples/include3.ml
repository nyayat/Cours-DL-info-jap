module type CounterFullItf =
sig
  val c : int ref
  val incr : unit -> unit
  val show : unit -> int
end;;

module type Counter2 =
sig
  include CounterFullItf
  val step : int -> unit
end;;

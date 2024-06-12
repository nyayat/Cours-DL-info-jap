(* module type of : usage typique *)

module type Counter2 =
sig
  val step : int -> unit
  include module type of Counter
end;;

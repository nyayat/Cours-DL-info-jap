(* Interface abstraite pour Read et Write *)
module type ReadWrite = sig
  type t
  val create : unit -> t
  val step : t -> unit
  val get : t -> int
end ;;

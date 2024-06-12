module type Write = sig
  type t
  val create : unit -> t
  val step : t -> unit
end ;;

module type Read = sig
  type t
  val get : t -> int
end ;;

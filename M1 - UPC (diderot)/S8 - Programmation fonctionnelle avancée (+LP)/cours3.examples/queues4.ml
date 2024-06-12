module type FIFOIMP = sig
  type 'a t
  exception Empty
  val create : unit -> 'a t
  val is_empty : 'a t -> bool
  val add : 'a -> 'a t -> unit
  val remove : 'a t -> 'a
  (* raises Empty if the queue is empty *)
end;;

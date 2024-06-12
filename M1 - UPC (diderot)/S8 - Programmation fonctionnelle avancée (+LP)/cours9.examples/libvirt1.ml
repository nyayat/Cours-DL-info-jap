module type CONNECTION = sig
  type 'a t
  val connect_readonly : unit -> [`Readonly] t
  val connect : unit -> [`Readonly|`Readwrite] t
  val status : [>`Readonly] t -> int
  val destroy : [>`Readwrite] t -> unit
end
module Connection:CONNECTION = struct
  type 'a t = int
  let count = ref 0
  let connect_readonly () = incr count; !count
  let connect () = incr count; !count
  let status c = c
  let destroy c = ()
end

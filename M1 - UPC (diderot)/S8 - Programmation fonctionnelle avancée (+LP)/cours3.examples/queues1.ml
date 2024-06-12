module type FIFO = sig
  type 'a t
  exception Empty
  val empty : 'a t
  val is_empty : 'a t -> bool
  val add : 'a -> 'a t -> 'a t
  val remove : 'a t -> ('a * 'a t)
  (** leve l'exception Empty sur une file vide *)
end;;

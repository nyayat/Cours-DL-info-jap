exception Empty

module type FIFO = sig
  type 'a t
  val empty : 'a t
  val add : 'a -> 'a t -> 'a t
  val remove : 'a t -> 'a * 'a t
  (** leve l'exception [Empty] sur une file vide *)
end

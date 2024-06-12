module type NEL = sig
  type 'a t
  val cons : 'a -> 'a t -> 'a t
  val pop : 'a t -> 'a * 'a t option
  val create : 'a -> 'a t
end

module NonEmptyList : NEL = struct
  type 'a t = One of 'a | Cons of 'a * 'a t
  let cons x l = Cons(x,l)
  let pop = function
    | Cons(x,l) -> x, Some l
    | One(x) -> x, None
  let create x = One(x)
end;;

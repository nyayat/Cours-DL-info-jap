(* Un type ordonne et la fonction de comparaison *)
module type ORDERED = sig
  type t
  val compare : t -> t -> int
end

module type SET = sig
  type elem
  type set
  val empty : set
  val insert : elem -> set -> set
  val member : elem -> set -> bool
end;;

module RedBlackSet (Element : ORDERED) :
      (SET with type elem = Element.t) =
struct

 type elem = Element.t
 let islt x y = (Element.compare x y) < 0

 type color = R | B
 type tree = E | T of color * tree * elem * tree
 type set = tree

 let empty = E

 let rec member x = function
   | E -> false
   | T (_, a, y, b) ->
       if islt x y then member x a
       else if islt y x then member x b
       else true

 let bal = function
   | B, T (R, T (R, a, x, b), y, c), z, d
   | B, T (R, a, x, T (R, b, y, c)), z, d
   | B, a, x, T (R, T (R, b, y, c), z, d)
   | B, a, x, T (R, b, y, T (R, c, z, d)) ->
       T (R, T (B, a, x, b), y, T (B, c, z, d))
   | a, b, c, d -> T (a, b, c, d)

 let insert x s =
   let rec ins = function
   | E -> T (R, E, x, E)
   | T (color, a, y, b) as s ->
     if islt x y then bal (color, ins a, y, b)
     else if islt y x then bal (color, a, y, ins b)
     else s
   in
   match ins s with
   | T (_, a, y, b) -> T (B, a, y, b)
   | _ -> assert false
end

(* Exemple: les listes vides/non-vides, encore *)

type empty
type nonempty

type ('empty_or_not, 'e) t =
  | Nil : (empty, 'e) t
  | Cons : 'e * ('empty_or_not, 'e) t ->
              (nonempty, 'e) t

let hd = function Cons (x,r) -> x

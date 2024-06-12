open NonEmptyList

let is_singleton = function
  | One(_) -> true
  | Cons(_,_) -> false
;;

(* le type est abstrait *)

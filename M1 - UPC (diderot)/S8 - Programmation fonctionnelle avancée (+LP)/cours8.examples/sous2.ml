let rec g = function
  | `Nil -> true
  | `Cons (h,r) -> h=1 && (g r)
  | `Snoc (h,r) -> h=2 && (g r)

(* wlist satisfait les contraintes obtenues pour g *)
let z = (`Cons (42,`Nil) : int wlist)
let _ = g z

(* 'a vlist est un sous-type de 'a wlist *)
let y = (`Cons (42,`Nil) : int vlist)
let _ = g y


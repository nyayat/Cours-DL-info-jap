(* Listes paresseuses utilisant mylazy *)

type 'a lazylist = 'a lazycell mylazy
and 'a lazycell = Cons of 'a * 'a lazylist

let rec fact_from n =
  mklazy (fun () -> Cons (fact n, fact_from (n+1)))

let fact_nat = fact_from 0

let rec take (n:int) (s:'a lazylist) =
  if n=0 then []
  else match force s with Cons(v,r) -> v::(take (n-1) r)

let _ = take 5 fact_nat
let _ = take 7 fact_nat

(* Pour une liste infinie, pas besoin de cas de base *)
type 'a lazylist = unit -> 'a lazycell
and 'a lazycell = Cons of 'a * 'a lazylist

let rec fact_from n =
  fun () -> Cons (fact n, fact_from (n+1))

let rec take n s =
  if n = 0 then [] else
    match s () with Cons(v,r) -> v::(take (n-1) r)

let fact_nat = fact_from 0
let _ = take 5 fact_nat

(* Et pour calculer les fact de proche en proche : *)
let rec fact_from2 n p =
  fun () -> Cons (p, fact_from2 (n+1) ((n+1)*p));;

take 10 (fact_from2 0 1);;

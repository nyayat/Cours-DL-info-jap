let fact n =
  let rec calc = function 0 -> 1 | n -> n*(calc (n-1))
  in Printf.printf "calcule fact(%d)\n" n; calc n

type 'a lazylist = unit -> 'a lazycell
and 'a lazycell = Nil | Cons of 'a * 'a lazylist

let rec fact_from n : int lazylist =
  fun () -> Cons (fact n, fact_from (n+1))

let rec take n s =
  if n = 0 then []
  else match s () with
       | Nil -> []
       | Cons(v,r) -> v::(take (n-1) r)

let fact_nat = fact_from 0
let _ = take 5 fact_nat
let _ = take 5 fact_nat

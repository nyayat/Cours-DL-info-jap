type 'a stream = 'a cell Lazy.t
and 'a cell = Cons of 'a * 'a stream

let rec fact_from n  =
  lazy (Cons (fact n, fact_from (n+1)))

let fact_nat = fact_from 0

let rec take n (s:'a stream) =
  if n = 0 then []
  else match Lazy.force s with
       | Cons(v,r) -> v :: (take (n-1) r)

let _ = take 5 fact_nat
let _ = take 7 fact_nat

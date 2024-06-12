open FifoDLCount
let rec iterate_add n q =
  if n=0 then q
  else iterate_add (n-1) (add n q)
let rec iterate_remove n q =
  if n=0 then q
  else iterate_remove (n-1) (snd (remove q))

let test n =
  reset ();
  let _ = iterate_remove n (iterate_add n empty)
  in (!ncalls,!cost)

let _= test 10;; (* as expected *)

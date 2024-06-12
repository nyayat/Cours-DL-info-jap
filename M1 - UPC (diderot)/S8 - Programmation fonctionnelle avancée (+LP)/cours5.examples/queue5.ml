let rec repeat_remove n q =
  if n=0 then q
  else let _ = remove q in repeat_remove (n-1) q

let test2 n =
  reset ();
  let _ = repeat_remove n (iterate_add n empty)
  in (!ncalls,!cost)

let _ = test2 10
(* ne correspond pas *)

(* does not work *)
let rec powers = lazy (Cons(1,times_bis 2 powers))

let _ = take 5 powers

(* works *)
let rec powers = lazy (Cons(1,times 2 powers))

let _ = take 5 powers

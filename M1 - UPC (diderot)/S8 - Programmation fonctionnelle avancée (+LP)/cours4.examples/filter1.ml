(* Attention aux situations de famines *)

let rec filter f s =
  lazy (filter_cell f s)
and filter_cell f = function
  | lazy (Cons (h,t)) ->
     if f h then Cons (h, filter f t)
     else filter_cell f t

let rec nums n = lazy (Cons (n, nums (n+1)))
let pairs = filter (fun n -> n mod 2 = 0) (nums 0)
let _ = take 10 pairs (* ok *)
let unicorns = filter (fun n -> n mod 2 = 1) pairs
let _ = take 10 unicorns (* calcul infini *)

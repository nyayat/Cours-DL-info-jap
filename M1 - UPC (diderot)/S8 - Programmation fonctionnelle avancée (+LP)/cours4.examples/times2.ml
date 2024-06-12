(* times via recursion interne *)

let times n s =
  let rec timesrec = function
    | lazy(Cons(h,t)) -> Cons(n*h,lazy(timesrec t))
  in lazy (timesrec s)

(* Equivalent : times via recursion mutuelle *)

let rec times n s =
  lazy (timescell n (Lazy.force s))
and timescell n = function
  | Cons (h,t) -> Cons (n*h,times n t)

let _ = times 42 (fact_from 0)

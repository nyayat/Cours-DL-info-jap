let times n s =
  let rec timesrec = function
    | lazy(Cons(h,t)) -> Cons(n*h,lazy(timesrec t))
  in lazy (timesrec s);;

let rec times_bis n = function
  | lazy(Cons(h,t)) -> lazy(Cons(n*h,times_bis n t))

let _ = times 42 (fact_from 0)
let _ = times_bis 42 (fact_from 0)


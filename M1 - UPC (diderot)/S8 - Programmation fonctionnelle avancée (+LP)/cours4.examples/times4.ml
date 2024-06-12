(* Les streams peuvent finir, même sans cas de base *)
exception Empty

let rec countdown n =
  lazy (if n < 0 then raise Empty
        else Cons (n, countdown (n-1)))

let _ = take 11 (countdown 10)
let _ = take 12 (countdown 10)
let _ = take 11 (times 2 (countdown 10))
let _ = take 11 (times_bis 2 (countdown 10))

let rec safe_take n s =
  if n = 0 then []
  else match Lazy.force s with
       | Cons (h,t) -> h::(safe_take (n-1) t)
       | exception Empty -> []

let _ = safe_take 20 (countdown 10)

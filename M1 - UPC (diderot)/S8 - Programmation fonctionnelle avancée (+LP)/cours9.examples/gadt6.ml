let rec length : type v e. (v,e) t -> int
 = function
 | Nil -> 0
 | Cons (_,r) -> 1 + length r

let rec map :
 type v e1 e2. (e1 -> e2) -> (v,e1) t -> (v,e2) t
 = fun f -> function
            | Nil -> Nil
            | (Cons (x,r)) -> Cons(f x,map f r)

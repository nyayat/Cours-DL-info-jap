let rec merge s1 s2 = lazy (merge_cell s1 s2)
and merge_cell s1 s2 =
  match s1, s2 with
  | lazy (Cons(h1,t1)), lazy (Cons(h2,t2)) ->
     if h1 < h2 then Cons(h1, merge t1 s2)
     else if h1 = h2 then Cons(h1, merge t1 t2)
     else Cons(h2, merge s1 t2)

let merge3 s1 s2 s3 = merge s1 (merge s2 s3)

let rec hamming =
  lazy (Cons (1, merge3 (times 2 hamming)
                        (times 3 hamming)
                        (times 5 hamming)))
let _ = take 100 hamming

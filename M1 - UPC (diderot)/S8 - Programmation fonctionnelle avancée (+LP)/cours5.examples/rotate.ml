(* rotate f r acc = f ++ (reverse r) ++ acc *)
(* Hypothesis: length(r) = length(f)+1 *)

let rec rotate f r acc =
  match Lazy.force f, Lazy.force r with
  | Nil, Cons(y, _) -> lazy (Cons (y, acc))
  | Cons (x, xs), Cons(y, ys) ->
     let acc' = lazy (Cons (y, acc)) in
     lazy (Cons (x, rotate xs ys acc'))
  | _, _ -> assert false (* cas impossible *)


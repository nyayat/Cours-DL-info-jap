let rec eqtree t1 t2 = match t1,t2 with
  | Leaf v1, Leaf v2 -> v1=v2
  | Node (l1,r1), Node (l2,r2) ->
     eqtree l1 l2 && eqtree r1 r2
  | _ -> false

let _ = eqtree t1 t2

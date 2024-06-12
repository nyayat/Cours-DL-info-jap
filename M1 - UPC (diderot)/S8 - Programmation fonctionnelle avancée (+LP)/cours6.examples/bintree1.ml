type 'a tree =
  | Leaf of 'a
  | Node of 'a tree * 'a tree

let rec bintree1 h c =
  if h=0 then Leaf c
  else
    Node (bintree1 (h-1) c, bintree1 (h-1) c)

let t1 = bintree1 5 42

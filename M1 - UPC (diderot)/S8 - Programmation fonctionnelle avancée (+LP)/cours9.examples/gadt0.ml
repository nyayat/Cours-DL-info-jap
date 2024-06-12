type tree =
  | Leaf of int
  | Node of tree * int * tree

type tree =
  | Leaf: int -> tree
  | Node: tree * int * tree -> tree

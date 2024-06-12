type zero = unit         (* codage des entiers en type, *)
type 'n succ = 'n * unit (* concret cette fois-ci *)

type ('a,'h) tree =
| Leaf: ('a,zero) tree
| Node: ('a,'h) tree*'a*('a,'h) tree -> ('a,'h succ) tree

let good = Node(Node(Leaf,1,Leaf), 3, (Node(Leaf,2,Leaf)))
let bad  = Node(Leaf,1,Node(Leaf,2,Leaf))

let rec map : type a b h. (a->b) -> (a,h) tree -> (b,h) tree
  = fun f -> function
  | Leaf -> Leaf
  | Node (t1,v,t2) -> Node (map f t1, f v, map f t2)

let _ = map (fun x -> x+1) good

let rec bintree2 h c =
  if h=0 then Leaf c
  else let t = bintree2 (h-1) c in Node (t,t)

let t2 = bintree2 5 42

let _ = t1=t2
let _ = t1==t2

let _ = match t1 with Node(x,y) -> x==y | _ -> false
let _ = match t2 with Node(x,y) -> x==y | _ -> false

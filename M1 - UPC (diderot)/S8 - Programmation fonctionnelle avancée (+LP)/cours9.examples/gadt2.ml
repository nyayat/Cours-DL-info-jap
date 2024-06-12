type empty
type nonempty

type 'empty_or_not ilist =
 | Cons : int * 'v ilist -> nonempty ilist
 | Nil : empty ilist

(* jusqu'ici, tout va bien *)
let head  = function Cons(x,r) -> x

(* mais le typeur exige des motifs du meme type *)
let head_or_one = function
 | Nil -> 1
 | (Cons _) as l -> head l

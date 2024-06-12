(* Version de silly qui ne donne plus de warning *)

let silly : type v . v ilist -> int = function
 | Nil -> 1
 | (Cons _) as l ->
     (* here l has type nonempty ilist *)
      match l with Cons (x,_) -> 2

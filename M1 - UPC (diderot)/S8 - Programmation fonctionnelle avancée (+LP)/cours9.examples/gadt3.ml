(* On peut aider le typeur, et lui dire
   que tail_or_empty est une fonction ayant
   un type polymorphe.  Chaque cas peut donc
   avoir une  instance differente de ce type.
 *)

let head_or_one: type v . v ilist -> int =
 function
 | Nil -> 1
 | (Cons _) as l -> head l

(* attend une fonction qui accepte `T *)
let k p = function `S _ -> 4 | `T -> p `T
let _ = fun x -> k f x
let _ = fun x -> k g x

(* attend une fonction qui accepte `T et `Z *)
let h p = function `S _ -> 4 | `Z -> p `Z | `T -> p `T
let _ = fun x -> h f x
let _ = fun x -> h g x

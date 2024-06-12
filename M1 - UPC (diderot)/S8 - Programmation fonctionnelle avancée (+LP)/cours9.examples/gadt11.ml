let rec eval : type a. a expr -> a = function
 | Int x -> x
 | Bool b -> b
 | Pair (e1,e2) -> (eval e1, eval e2)
 | IfThenElse (b,e1,e2) ->
    if eval b then eval e1 else eval e2

(* Erreurs de typage ! *)
let _ = eval (IfThenElse (Int 0, Int 1, Int 2))
let _ =
  eval (IfThenElse (Bool true, Int 1, Bool true))
(* Ok *)
let _ = eval (IfThenElse (Bool true, Int 1, Int 2))

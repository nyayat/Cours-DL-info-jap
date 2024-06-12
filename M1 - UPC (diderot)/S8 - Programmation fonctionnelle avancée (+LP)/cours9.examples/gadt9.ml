let rec eval = function
  | Int x -> I x
  | Bool b -> B b
  | Pair (e1,e2) -> P (eval e1,eval e2)
  | IfThenElse (e1,e2,e3) ->
     match eval e1 with
     | B b -> if b then eval e2 else eval e3
     | _ -> failwith "Boolean expected in an If!"

(* exception pendant l'execution : *)
let _ = eval (IfThenElse (Int 0, Int 1, Int 2))
(* OK : *)
let _ = eval (IfThenElse (Bool true, Int 3, Int 4))
let _ =
  eval (IfThenElse (Bool true, Int 2, Bool true))

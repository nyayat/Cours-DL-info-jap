(* Appliquer un operateur ou une comparaison *)

let app_op o v1 v2 =
  match v1, v2 with
  | `VInt x, `VInt y ->
     (match o with
      | Plus -> `VInt (x + y)
      | Minus -> `VInt (x - y)
      | Mult -> `VInt (x * y)
      | Div -> `VInt (x / y)
      | Eq -> `VBool (x = y)
      | Lt -> `VBool (x < y)
      | Gt -> `VBool (x > y))
  | _ -> failwith "Operation on a non-integer"

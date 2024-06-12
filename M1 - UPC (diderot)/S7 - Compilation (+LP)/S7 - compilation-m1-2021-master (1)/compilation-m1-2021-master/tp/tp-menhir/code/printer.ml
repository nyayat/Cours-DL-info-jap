open AST

let string_of_exp e =
  let rec aux = function
    | Id x ->
       x
    | LInt x ->
       string_of_int x
    | Add (e1, e2) ->
       Printf.sprintf "(%s + %s)" (aux e1) (aux e2)
    | Mul (e1, e2) ->
       Printf.sprintf "(%s * %s)" (aux e1) (aux e2)
    | Sum (x, start, stop, exp) ->
       Printf.sprintf "sum(%s, %s, %s, %s)"
         x (aux start) (aux stop) (aux exp)
  in
  aux e

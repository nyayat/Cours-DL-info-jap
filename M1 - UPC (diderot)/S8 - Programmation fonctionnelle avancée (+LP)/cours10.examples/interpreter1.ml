let rec interp (exp:exp) env : result = match exp with
  | `Var id      -> List.assoc id env
  | `Int i       -> `VInt i
  | `Bool b      -> `VBool b
  | `Op(o,e1,e2) -> app_op o (interp e1 env) (interp e2 env)
  | `If(b,e1,e2) ->
      (match interp b env with
       | `VBool true -> interp e1 env
       | `VBool false -> interp e2 env
       | _ -> failwith "Non boolean test")
  | `App (e1,e2)  ->
      (match interp e1 env with
       | `VFun f -> f (interp e2 env)
       | _ -> failwith "Application of non function")
  | `Fun (id,e) -> `VFun (fun a -> interp e ((id,a)::env))


(* Well typed program *)
let ok =
  interp (`App(`Fun("x",`If(`Var "x",`Int 1,`Int 2)),
               `Bool true)) []

(* Ill typed program *)
let ko = interp (`App(`Int 1, `Int 2)) [];;

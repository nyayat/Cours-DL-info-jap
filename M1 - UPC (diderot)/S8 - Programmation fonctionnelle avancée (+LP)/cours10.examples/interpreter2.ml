(* Interpreter with error handling *)

type value =
  [ `VInt of int | `VBool of bool
  | `VFun of value -> result_or_err ]
and result_or_err = Err | Val of value

let rec interperr exp env : result_or_err = match exp with
  | `Var(id)      -> Val (List.assoc id env)
  | `Int i        -> Val (`VInt i)
  | `Bool b       -> Val (`VBool b)
  | `Op(op,e1,e2) ->
     (match interperr e1 env with
      | Err -> Err
      | Val v1 ->
         (match interperr e2 env with
          | Err -> Err
          | Val v2 -> Val (app_op op v1 v2)))
  | `If(b,e1,e2) ->
     (match interperr b env with
      | Err -> Err
      | Val (`VBool true) -> interperr e1 env
      | Val (`VBool false) -> interperr e2 env
      | _ -> failwith "Non boolean test")
  | `App (e1,e2) ->
     (match interperr e1 env with
      | Err -> Err
      | Val (`VFun f) ->
         (match interperr e2 env with
          | Err -> Err
          | Val v -> f v)
      | _ -> failwith "Application of non function")
  | `Fun (id,exp)->
     Val (`VFun (fun a -> interperr exp ((id,a)::env)))
  | `Fail -> Err

(* Program with no error *)
let _ = interperr
          (`App(`Fun("x",`If(`Var "x",`Int 1,`Int 2)),
                `Bool true)) []

(* interperr returns an error value *)
let _ = interperr
          (`App(`Fun("x",`If(`Var "x",`Fail,`Int 2)),
                `Bool true)) []

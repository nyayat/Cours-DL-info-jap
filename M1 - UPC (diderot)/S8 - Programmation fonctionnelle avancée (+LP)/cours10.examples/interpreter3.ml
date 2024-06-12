(* Imperative interpreter *)

(* Types of values and results *)
type state = State.t
type value_st =
  [ `VInt of int | `VBool of bool (* as before *)
  | `VFun of value_st -> state -> result_st
  | `VUnit (* result of imperative actions *) ]
and result_st = value_st * state

let rec interpst exp env (s:state) : result_st =
  match exp with
  | `Var id      -> (List.assoc id env), s
  | `Int i       -> `VInt i, s
  | `Bool b      -> `VBool b, s
  | `Op(o,e1,e2) ->
     let v1,s = interpst e1 env s in
     let v2,s = interpst e2 env s in
     app_op o v1 v2, s
  | `If(b,e1,e2) ->
     let v,s = interpst b env s in
     (match v with
      | `VBool true -> interpst e1 env s
      | `VBool false -> interpst e2 env s
      | _ -> failwith "Non boolean condition in conditional")
  | `App (e1,e2) ->
     let v1,s = interpst e1 env s in
     (match v1 with
      | `VFun f -> let v2,s = interpst e2 env s in f v2 s
      | _ -> failwith "Non functional value in application")
  | `Fun (id,e) ->
     `VFun (fun a -> interpst e ((id,a)::env)), s
  (* Some new imperative expressions *)
  | `Unit -> `VUnit, s
  | `Seq (e1,e2) ->
     let _,s = interpst e1 env s in interpst e2 env s
  | `While (b,e) ->
     interpst (`If(b,`Seq(e,exp),`Unit)) env s
  | `Get e ->
     let v,s = interpst e env s in
     (match v with
      | `VInt i -> `VInt (State.get s i), s
      | _ -> failwith "Not an integer in Get")
  | `Set (e,e') ->
     let v,s = interpst e env s in
     (match v with
      | `VInt i ->
         let v',s = interpst e' env s in
         (match v' with
          | `VInt x -> `VUnit, State.set s i x
          | _ -> failwith "Not an integer in Set")
      | _ -> failwith "Not an integer in Set")

(* Example of imperative program. Pseudo-code :
while m[0]<10 do m[0]:=m[0]+1; m[1]:=m[1]+m[0] done; m[1] *)

let code_imp =
  let x = `Int 0 and y = `Int 1 in
  `Seq(`While(`Op(Lt,`Get x,`Int 10),
              `Seq(
                  `Set(x,`Op(Plus,`Get x,`Int 1)),
                  `Set(y,`Op(Plus,`Get y,`Get x)))),
       `Get y)
let _ = interpst code_imp [] State.init

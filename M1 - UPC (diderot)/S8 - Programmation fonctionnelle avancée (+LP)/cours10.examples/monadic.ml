#use "syntax.ml";;
#use "ops.ml";;
#use "state.ml";;

module type Monad = sig
  type +'a t
  val return : 'a -> 'a t
  val bind : 'a t -> ('a -> 'b t) -> 'b t
  val reveal : 'a t -> 'a
end

module IdMonad (* : Monad *) = struct
  type 'a t = 'a
  let return v = v
  let bind m f = f m
  let reveal v = v
end

module ErrMonad (* : Monad *) = struct
  type 'a t = Err | Val of 'a
  let return v = Val v
  let bind m f = match m with Err -> Err | Val v -> f v
  let reveal m = match m with
    | Err -> assert false
    | Val v -> v
end

module StateMonad (* : Monad *) = struct
  type state = State.t
  type 'a t = state -> 'a * state
  let return v = fun s -> (v,s)
  let bind m f s = let (v,s) = m s in f v s
  let reveal m = fst (m State.init)
end

module InterpGen (M: Monad) =
struct
 let ( >>= ) = M.bind

 let interp_gen interp e env =
   match e with
   | `Var id -> M.return (List.assoc id env)
   | `Int i  -> M.return (`VInt i)
   | `Bool b -> M.return (`VBool b)
   | `Op(o,e1,e2) ->
      interp e1 env >>= fun v1 ->
      interp e2 env >>= fun v2 ->
      M.return (app_op o v1 v2)
   | `If(b,e1,e2) ->
      (interp b env >>= function
       | `VBool true -> interp e1 env
       | `VBool false -> interp e2 env
       | _ -> failwith "Non boolean test")
   | `App (e1,e2) ->
      (interp e1 env >>= function
       | `VFun f -> interp e2 env >>= f
       | _ -> failwith "Application of non function")
   | `Fun (id,e) ->
      M.return (`VFun (fun a -> interp e ((id,a)::env)))
   | _ -> assert false (* pour permettre des extensions *)
end

module InterpPlain = struct
  type value =
    [ `VInt of int | `VBool of bool
    | `VFun of value -> result ]
  and result = value
  type env = (id * value) list
  include InterpGen(IdMonad)
  let rec interp (e:exp) (env : env) : result =
    interp_gen interp e env
end

module InterpErr = struct
  type value =
    [ `VInt of int | `VBool of bool
    | `VFun of value -> result ]
  and result = value ErrMonad.t
  type env = (id * value) list
  include InterpGen(ErrMonad)
  let rec interp exp (env : env) : result = match exp with
    | `Fail -> ErrMonad.Err
    | _ -> interp_gen interp exp env
end

module InterpState = struct
  type value =
    [ `VInt of int | `VBool of bool
    | `VFun of value -> result
    | `VUnit ]
  and result = value StateMonad.t
  type env = (id * value) list
  include InterpGen(StateMonad)
  let to_int = function
    | `VInt i -> StateMonad.return i
    | _ -> failwith "Not an integer"
  let rec interp exp (env : env) : result = match exp with
    | `Unit -> StateMonad.return `VUnit
    | `Seq (e1,e2) ->
       interp e1 env >>= fun _ -> interp e2 env
    | `While (b,e) ->
       interp (`If(b,`Seq(e,exp),`Unit)) env
    | `Get e ->
       interp e env >>= to_int >>= fun i ->
       fun s -> `VInt (State.get s i), s
    | `Set (e1,e2) ->
       interp e1 env >>= to_int >>= fun i ->
       interp e2 env >>= to_int >>= fun x ->
       fun s -> `VUnit, State.set s i x
    | _ -> interp_gen interp exp env
end

let prog =
 `App(`Fun("x",`If(`Var "x",`Int 1,`Int 2)),`Bool true)

let _ = InterpPlain.interp prog []
let _ = ErrMonad.reveal (InterpErr.interp prog [])
let _ = StateMonad.reveal (InterpState.interp prog [])

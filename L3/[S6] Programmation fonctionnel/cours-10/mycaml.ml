open AST

let parse content =
  let lexbuf = Lexing.from_string content in
  Parser.program Lexer.token lexbuf

type value =
  | VInt of int
  | VFun of identifier * term * env
  | VPrim of operation * int option (* premier argument présent ou non *)
  | VRecord of (label * value) list
  | VKApp of constructor * value list

and env = (identifier * value) list

let eval_op = function
  | Add -> (+)
  | Sub -> (-)
  | Mul -> ( * )
  | Div -> (/)

let eval : AST.program -> (identifier * value) list =
fun p ->
  let rec program env p =
    let rec fold env values = function
      | [] -> values
      | d :: ds ->
         let (env, v) = toplevel_definition env d in
         fold env (v :: values) ds
    in
    fold env [] p
  and toplevel_definition env (ToplevelValue vdef) =
    let (x, v) = value_definition env vdef in
    let env = (x, v) :: env in
    (env, (x, v))
  and value_definition env = function
    | SimpleValue (x, t) ->
      (* let x = t *)
       (x, term env t)
    | RecFunction (_f, [], _t) -> assert false (* Cf. parsing *)
    | RecFunction (f, x::xs, t) ->
       (* let rec f x x1 x2 ... = t *)
       (* les variables suivantes x1 x2 ... sont gardés pour plus tard
          via des Lam *)
       let body = List.fold_right (fun y e -> Lam (y,e)) xs t in
       (* la valeur récursive v est cyclique avec son environnement
          (OCaml permet le "let rec" de données dans certains cas comme ici) *)
       let rec v = VFun (x,body,env')
       and env' = (f,v)::env
       in
       (f,v)
  and term (env : (identifier * value) list) : term -> value = function
    | Lit (LInt x) ->
       VInt x
    | Var x ->
       List.assoc x env
    | Let (d, t) ->
       let (x, v) = value_definition env d in
       term ((x, v) :: env) t
    | Op o -> VPrim (o,None)
    | Lam (x, t) ->
       (* fun x -> t *)
       VFun (x, t, env)
    | App (a, b) ->
       begin match term env a, term env b with
       | VFun (x, t, env), vb -> term ((x, vb) :: env) t
       | VPrim (o,None), VInt n -> VPrim (o, Some n)
       | VPrim (o,Some n), VInt m -> VInt ((eval_op o) n m)
       | _ -> failwith "programme mal typé!"
       end
    | Record rs ->
       VRecord (List.map (fun (l, e) -> (l, term env e)) rs)

    | Proj (e, l) ->
       begin match term env e with
       | VRecord vs ->
          List.assoc l vs
       | _ ->
          failwith "programme mal typé"
       end
    | KApp (c, ts) -> VKApp (c, List.map (term env) ts)
    | Match (e,br) ->
       let v = term env e in
       matching env v br
  and matching env v = function
    | [] -> failwith "match non exhaustif"
    | (pat,t)::branches ->
       (match pattern env pat v with
        | Some env' -> term env' t
        | None -> matching env v branches)
  and pattern env pat v =
    match pat, v with
    | PVar id, _ -> Some ((id,v)::env)
    | PLit (LInt n), VInt m when n=m -> Some env
    | PKApp (c,pats), VKApp (c',vs) when c=c' -> patterns env pats vs
    | PWildcard, _ -> Some env
    | _ -> None
  and patterns env pats vs =
    match pats, vs with
    | [], [] -> Some env
    | pat::pats, v::vs ->
       (match pattern env pat v with
        | Some env' -> patterns env' pats vs
        | None -> None)
    | _ -> None
  in
  program [] p

(* Un peu d'affichage *)

let print_operation = function
  | Add -> "(+)"
  | Sub -> "(*)"
  | Mul -> "( * )"
  | Div -> "(/)"

let rec print_value = function
  | VInt n -> string_of_int n
  | VFun (Id x,_,_) -> "fun "^x^" -> ..."
  | VPrim (o,None) -> (print_operation o)
  | VPrim (o,Some n) -> (print_operation o)^" "^string_of_int n
  | VRecord l -> "{"^(String.concat ";" (List.map print_labelvalue l))^"}"
  | VKApp (CId c, l) -> c^"("^(String.concat "," (List.map print_value l))^")"

and print_labelvalue (LId lab, v) = lab^"="^print_value v

let print_env env =
  List.iter
    (fun (Id x,v) -> Printf.printf "%s = %s\n" x (print_value v))
    (List.rev env)

let run content =
  print_env (eval (parse content))

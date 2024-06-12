(* Le type des identifiants, de simples chaînes de caractères ici. *)
type id = string

(* Le type des expressions/termes Marthe. *)
type e =
  | EInt  of int                 (* Ex: "42", "31"              *)
  | EVar  of id                  (* Ex: "x", "y", "foo"         *)
  | EPlus of e * e               (* Ex: "1 + 2", "2 * 3 + 4"    *)
  | EMult of e * e               (* Ex: "1 * 2", "(1 + 2) * 3"  *)
  | ESum  of id * e * e * e      (* Ex: "sum (x, 1, 10, x * x)" *)

module Id = struct type t = id let compare = Stdlib.compare end

(* Le module Env fournit un type d'environnement plus efficace qu'une liste
   d'association, mais moins efficace qu'une résolution statique dans un tableau
   comme utilisée par la machine abstraite du cours 01. *)
module Env = Map.Make(Id)

(* Similairement, le module IdSet fournit un type non naïf pour les ensembles
   d'identifiants. *)
module IdSet = Set.Make(Id)

(* Le fonctionnement de la fonction [eval] ci-dessous est identique à celle de
   la fonction [interpret] du cours 01, à ceci près que la fonction ci-dessous
   travaille sur des environnements de type [int Env.t]. *)
let rec eval env m =
  match e with
  | EInt i -> i

  | EVar x -> Env.find x env

  | EPlus (m, n) -> eval env m + eval env n

  | EMult (m, n) -> eval env m * eval env n

  | ESum (x, start, stop, body) ->
     let vstart = eval env start in
     let vstop = eval env stop in
     let rec iter i accu =
       if i > vstop
       then accu
       else iter (i + 1) (accu + eval (Env.add x i env) body)
     in
     iter vstart 0
;;

let free_vars : e -> IdSet.t =
  assert false
;;

let rename : e -> id -> id -> e =
  assert false
;;

let fresh_in : IdSet.t -> id =
  assert false
;;

let alpha_eq : e -> e -> bool =
  assert false
;;

let subst : e -> id -> e -> e =
  assert false
;;

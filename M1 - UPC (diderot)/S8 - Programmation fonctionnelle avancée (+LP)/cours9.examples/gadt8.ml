(* expressions sans GADT, de l'OCaml classique *)
type expr =
  | Int of int
  | Bool of bool
  | Pair of expr * expr
  | IfThenElse of expr * expr * expr

(* valeurs *)
type res = I of int | B of bool | P of res * res

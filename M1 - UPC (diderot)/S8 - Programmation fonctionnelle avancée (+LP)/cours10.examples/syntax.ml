(* Syntaxe Abstraite *)

type id = string
and exp =
  [ `Int of int
  | `Bool of bool
  | `Var of id
  | `Op of op * exp * exp
  | `App of exp * exp
  | `Fun of id * exp
  | `If of exp * exp * exp ]
and op = Plus | Minus | Mult | Div | Eq | Lt | Gt

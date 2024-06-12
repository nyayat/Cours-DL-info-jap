type exp =
  | Id of identifier
  | LInt of int
  | Add of exp * exp
  | Mul of exp * exp
  | Sum of identifier * exp * exp * exp

and identifier = string

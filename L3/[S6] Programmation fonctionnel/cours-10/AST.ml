type program = toplevel_definition list

and toplevel_definition =
  | ToplevelValue of value_definition

and value_definition =
  | SimpleValue of identifier * term
  | RecFunction of identifier * identifier list * term

and term =
  | Var     of identifier
  | Lit     of literal
  | Op      of operation
  | Let     of value_definition * term
  | Lam     of identifier * term
  | App     of term * term
  | Proj    of term * label
  | Record  of (label * term ) list
  | KApp    of constructor * term list
  | Match   of term * branch list

and branch = pattern * term

and pattern =
  | PVar of identifier
  | PLit of literal
  | PKApp of constructor * pattern list
  | PWildcard

and literal =
  | LInt of int

and identifier = Id of string

and label = LId of string

and constructor = CId of string

and operation = Add | Mul | Sub | Div

(* expression avec GADT *)

type _ expr =
 | Int : int -> int expr
 | Bool : bool -> bool expr
 | Pair : 'a expr * 'b expr -> ('a * 'b) expr
 | IfThenElse : bool expr * 'a expr * 'a expr
                   -> 'a expr

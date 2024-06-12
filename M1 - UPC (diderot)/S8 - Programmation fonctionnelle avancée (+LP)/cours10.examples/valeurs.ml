(* The type of values.
   Values can be bound to identifiers. *)
type value =
  [ `VInt of int
  | `VBool of bool
  | `VFun of value -> result ]
(* The type of results. Results are obtained by *)
(* applying a function or operator to values. *)
and result = value

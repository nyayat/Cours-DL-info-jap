(* borne superieure *)
let f = function
  | `B x -> x
  | `A x -> x

(* quelle est le type de cette fonction ? *)
let switch = function
  | `A -> `B
  | `B -> `A

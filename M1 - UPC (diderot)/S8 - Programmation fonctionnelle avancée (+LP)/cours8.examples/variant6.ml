(* lower bounds are combined by union *)
let (x: [> `A | `B ]) = `A
let y = (x: [> `A | `C])

(* upper bounds are combined by intersection *)
let (x: [< `A|`B|`C]) = `A
let y = (x: [< `A|`B|`D])

(* redundant constraints are dropped *)
let f = function `A -> 0 | `B -> 1
let g = (f: [<`A | `B | `C | `D] -> int)


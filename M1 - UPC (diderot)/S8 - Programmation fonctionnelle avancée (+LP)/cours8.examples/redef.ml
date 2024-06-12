type t1 = A | B
let x = B
type t2 = B | C
let y = B (* now of type t2 *)

let isB2 = function
  | B -> true
  | C -> false

let isB1 = function
  | A -> false
  | B -> true

let _ = isB1 x
let _ = isB2 y

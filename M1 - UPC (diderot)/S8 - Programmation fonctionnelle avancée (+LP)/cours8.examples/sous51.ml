type t1 = [`A|`B]
type t2 = [`A|`B|`C]

let f = function
  | `A -> 0
  | `B -> 1
  | `C -> 2
  | _ -> 42

let (x:t1) = `A
let bad = f x
let ok = f (x :> t2)

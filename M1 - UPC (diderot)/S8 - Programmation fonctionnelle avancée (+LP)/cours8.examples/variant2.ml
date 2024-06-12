let ko = function
  | `A   -> 1
  | `A 3 -> 2

let ko = [`A; `A 3]

let ok = (`A, `A 5)

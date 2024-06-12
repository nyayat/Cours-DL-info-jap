type good = [`A | `B of int]
type bad = [> `A | `B of int]

let f = function true -> `A | false -> `B
let fc = (f : bool -> [> `A | `B | `C])

let g = function `A -> 1 | `B -> 2
let gc = (g : [< `A] -> int)

let fcc = (f : bool -> [< `A | `C])


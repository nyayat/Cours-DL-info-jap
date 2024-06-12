type t1 = [`A | `B]
type t2 = [`A | `B | `C | `D ]

(* contraintes incompatibles *)
let x = (`A:t1)
let y = (x:t2)

(* une coercion ``ouvre'' le type *)
let y = (x :> t2)

(* contrainte redondante *)
let z = (x : [> `A])

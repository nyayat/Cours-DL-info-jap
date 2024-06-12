type t = [`A | `B]

let x = `A
let y = `C

(* types compatibles *)
let _ = (x = y)

(* types incompatibles *)
let _ = ((x:t) = y)

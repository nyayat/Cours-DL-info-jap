let id = fun x -> x

(* type error *)
let f = id id
let _ = f 42
let _ = f "truc"

(* solution: eta-expansion
   (i.e. expliciter l'argument) *)
let g = fun x -> (id id) x
let _ = g 42
let _ = g "truc"

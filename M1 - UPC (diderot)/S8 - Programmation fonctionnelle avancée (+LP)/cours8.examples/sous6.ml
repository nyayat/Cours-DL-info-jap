let f : [`T | `Z] -> int =
  function `T -> 1 | `Z -> 2

let g : [ `T] -> int =
  function `T -> 4

(* le type de f est un sous-type du type de g
   (en "oubliant" que f sait traiter `Z) *)
let ok = (f :> [ `T] -> int)

(* la reciproque n'est pas vraie
   (g ne sait pas gerer `Z) *)
let ko = (g :> [ `T | `Z] -> int)

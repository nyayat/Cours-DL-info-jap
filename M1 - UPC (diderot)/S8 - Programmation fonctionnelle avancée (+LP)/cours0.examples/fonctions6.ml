(* n est une variable libre dans function x-> x*n *)
let multiplier_avec n = function x -> x*n;;
let f = multiplier_avec 5;;
f 3;;

let m = 17;;
let g x = x + m;; (* m est libre *)
let m = 42;;
g 1;; (* quel est le resultat ? *)

let f x = x + 55;;
let g y = f (f y);;
let f x = x + 1000;;
g 10;;

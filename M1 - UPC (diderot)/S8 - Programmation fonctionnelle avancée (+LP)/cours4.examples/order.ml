(* l'ordre n'est pas specifie *)
(print_string "gauche\n"; fun x -> x)
(print_string "droite\n"; 42)
;;

(* forcer un ordre d'evaluation *)
let f = print_string "gauche\n"; fun x -> x
in f (print_string "droite\n"; 42)

let f = function x -> 1/0 * x;;
(* pas d'erreur *)

f 42;;
(* exception division par zero *)

let double x = 2*x ;;

(* une fonction avec un argument fonctionnel *)
let apply_twice f n = f (f n);;

apply_twice double 5;;

(* arguments, et resultat fonctionnels *)
let compose f g = fun x -> f (g x);;

compose double (fun x -> x+2);;

compose double double 10;;

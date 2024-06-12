open NonEmptyListPriv;;

(* impossible de construire directement des listes *)
cons 3 (One(4));;

(* on peut faire une definition par cas *)
let rec map f = function
   | One(x) -> create (f x)
   | Cons(x,l) -> cons (f x) (map f l)
;;
map (function x -> x+1) (cons 1 (cons 2 (create 3)));;

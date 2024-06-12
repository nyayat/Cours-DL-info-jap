(* une insertion dans l'ordre, sans doublon *)
let rec insert x l = match l with
  | [] -> [x]
  | a::_ when x = a -> l
  | a::_ when x < a -> x::l
  | a::r -> a :: insert x r ;;

let l = [1;3;5;7];;
let l' = insert 4 l;;
(* l reste intacte *)
l;;
(* l et l' partagent la même sous-liste [5;7] *)
List.(tl (tl l) == tl (tl (tl l')));;
(* Exemple avec moins de partage que possible *)
l == insert 3 l;;

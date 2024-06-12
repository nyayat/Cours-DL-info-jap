(* une liste avec une position et une longueur *)
type 'a listpos = int * int * 'a list
(* changer de position, temps constant *)
let agauche = function
  | (0,_,_) -> failwith "debut de liste"
  | (p,t,l) -> (p-1,t,l)
let adroite = function
  | (p,t,l) when p=t -> failwith "fin de liste"
  | (p,t,l) -> (p+1,t,l)
let from_list l = (0, (List.length l)-1, l)
let get_current (p,t,l) = List.nth l p

let x = from_list ['a'; 'b'; 'd'];;
get_current(adroite (adroite x));;

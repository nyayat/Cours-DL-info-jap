
type 'a pile = 'a list
type 'a listzipper = 'a pile * 'a list

(* navigation, 'a listzipper -> 'a listzipper *)
let agauche = function
  | ([],_)   -> failwith "Deja a gauche"
  | (a::p,l) -> (p, a::l);;

let adroite = function
  | (p,[])   -> failwith "Deja a droite"
  | (p,a::l) -> (a::p, l);;

(* chaque mouvement est en temps constant *)

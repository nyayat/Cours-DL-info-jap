let a_gauche : 'a narbrezipper -> 'a narbrezipper
= function
| (a::lp,l)::p, arbre -> (lp,arbre::l)::p,a
| ([],_)::p, _ -> failwith "Deja a gauche"
| _ -> failwith "Racine";;

let a_droite : 'a narbrezipper -> 'a narbrezipper
= function
| (lp,a::l)::p, arbre -> (arbre::lp,l)::p,a
| (_,[])::p, _ -> failwith "Deja a droite"
| _ -> failwith "Racine";;

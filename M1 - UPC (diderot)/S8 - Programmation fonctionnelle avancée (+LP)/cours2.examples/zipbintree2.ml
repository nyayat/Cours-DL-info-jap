let bas_a_gauche : 'a arbrezipper -> 'a arbrezipper
= function
| pile,Feuille -> failwith "Feuille"
| pile,Noeud (x,g,d) -> (Gauche, x, d)::pile, g;;

let bas_a_droite  : 'a arbrezipper -> 'a arbrezipper
= function
| pile,Feuille -> failwith "Feuille"
| pile,Noeud (x,g,d) -> (Droite, x, g)::pile, d;;

let en_haut  : 'a arbrezipper -> 'a arbrezipper
= function
| (Gauche,x,t)::p, arbre -> p, Noeud (x,arbre,t)
| (Droite,x,t)::p, arbre -> p, Noeud (x,t,arbre)
| [], _ -> failwith "Racine";;

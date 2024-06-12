type 'a arbre =
| Feuille
| Noeud of 'a * 'a arbre * 'a arbre

type marqueur = Gauche | Droite
type 'a block = marqueur * 'a * 'a arbre
type 'a pile = 'a block list
type 'a arbrezipper = 'a pile * 'a arbre;;

(*  NB pour les listzipper, un bloc = 'a *)

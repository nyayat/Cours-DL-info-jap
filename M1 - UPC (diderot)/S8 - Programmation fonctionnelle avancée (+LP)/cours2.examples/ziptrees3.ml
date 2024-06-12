let en_bas : 'a narbrezipper -> 'a narbrezipper
= function
| pile, Noeud (filsg::freres) ->
   ([],freres)::pile, filsg
| _ -> failwith "Deja en bas";;

let en_haut : 'a narbrezipper -> 'a narbrezipper
= function
| (lp,l)::p, arbre ->
   p,Noeud(List.rev_append lp (arbre::l))
| [], _ -> failwith "Deja en haut";;

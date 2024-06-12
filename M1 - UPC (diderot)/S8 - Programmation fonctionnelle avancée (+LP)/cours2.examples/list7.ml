(* deplacement, temps constant *)
let agauche = function
  | None -> failwith "Liste vide"
  | Some {prev=None} -> failwith "Deja a gauche"
  | Some {prev=c} -> c
;;
let adroite = function
  | None -> failwith "Liste vide"
  | Some {next=None} -> failwith "Deja a droite"
  | Some {next=c} -> c
;;
let x = of_list ['a'; 'b'; 'd'] ;;
let y = to_list(insert 'c' (adroite (adroite x)));;

(* insert et delete en temps constant *)
let insert v  = function
  (pile,liste) -> (pile, v::liste);;

let delete = function
| (p,[])   -> failwith "Trop a droite pour effacer"
| (p,a::r) -> (p, r)
;;

from_list ['a'; 'b'; 'd'; 'e']
  |> adroite |> adroite |> insert 'c' |> to_list ;;

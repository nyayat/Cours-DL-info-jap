(* conversions *)
let from_list l = ([], l) ;;

let to_list (c, l) =
  let rec revapp c l = match c with
    | [] -> l
    | h::r -> revapp r (h::l)
  in revapp c l
 (* ou List.rev_append c l *)
;;

to_list (['a'; 'b'; 'c'], ['d'; 'e'; 'f']);;

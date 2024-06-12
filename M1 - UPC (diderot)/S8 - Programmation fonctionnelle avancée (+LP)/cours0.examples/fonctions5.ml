(* fonctions locale a une autre fonction *)

let rev l =
  let rec aux acc ll =
    match ll with
    | [] -> acc
    | a::r -> aux (a::acc) r
  in
  aux [] l;;

rev [1; 2; 3];;

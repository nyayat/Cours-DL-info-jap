let rec reverse l = match l with
  | [] -> []
  | h::r -> (reverse r) @ [h]

let reverse l =
  let rec reverse_append l1 l2 =
    (* yields (reverse l1)@l2 *)
    match l1 with
    | [] -> l2
    | h::r -> reverse_append r (h::l2)
  in reverse_append l []
;;

reverse [1; 2; 3 ; 4];;

(* tester l'appartenance a une liste *)
let rec is_member x l = match l with
  | [] -> false
  | x::r -> true
  | h::r -> is_member x r
;;

(* ne fait pas ce qu'on attend ! *)
is_member 42 [1; 42; 73];;
is_member 42 [];;
is_member 17 [1; 42; 73];;

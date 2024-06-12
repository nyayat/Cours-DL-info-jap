(* insertion : temps lineaire en la position p *)
let insert_at_point x (p,t,l) =
   let rec ins n l =
     if n=0 then x::l
     else match l with
      | [] -> assert false
      | y::r -> y::(ins (n-1) r)
   in (p,t+1,ins p l);;

x;;
let y = insert_at_point 'c' (adroite (adroite x));;

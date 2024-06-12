(* suppression : temps lineaire en la position p *)
let delete_at_point (p,t,l) =
   let rec del p = function
     | [] -> assert false
     | h::r -> if p=0 then r else h::(del (p-1) r)
   in
   if p=t then failwith "fin de liste"
   else (p,t-1,del p l)
;;

y;;
let z = delete_at_point y;;

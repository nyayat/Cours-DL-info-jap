let mul x y = x*y;;

(* application partielle *)
let double = mul 2;;

double (double 3)

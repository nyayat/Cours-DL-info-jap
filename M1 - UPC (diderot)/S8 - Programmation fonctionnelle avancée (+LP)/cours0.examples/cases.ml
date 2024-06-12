let rec destutter l =
 match l with
 | []  -> []
 | x :: y :: rest ->
   if  x = y then destutter (y :: rest)
   else  x :: destutter (y :: rest) ;;

destutter [1; 2; 2; 3; 4];;

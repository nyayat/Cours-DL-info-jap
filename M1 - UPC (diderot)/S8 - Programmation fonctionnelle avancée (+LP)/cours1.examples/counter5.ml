let a = MultiCounter.create();;
MultiCounter.incr a;;
MultiCounter.show a;;

(* not authorized due to typing *)
a := !a+1;;

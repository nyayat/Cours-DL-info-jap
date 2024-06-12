(* suite de include1.ml *)

let c1 = (module Counter: CounterFullItf);;
let c2 = (module Counter2: CounterFullItf);;

let compteur sel =
  let module C = (val (if sel then c1 else c2))
  in (C.incr(),C.show())
;;

compteur true;;
compteur false;;

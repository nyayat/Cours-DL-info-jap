(* compteur c partage entre les deux modules ! *)

let _ = Counter.incr ();;
let _ = Counter.show ();;
let _ = Counter2.incr ();;
let _ = Counter.show ();;

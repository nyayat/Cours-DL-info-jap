(* Une valeur paresseuse sans calculs redondants *)

type 'a work =
  | Later of (unit -> 'a)
  | Done of 'a
type 'a mylazy = 'a work ref

let mklazy (f : unit -> 'a) : 'a mylazy = ref (Later f)

let force (r : 'a mylazy) : 'a =
  match !r with
  | Done x -> x
  | Later f -> let x = f () in r := Done x; x

let ex = mklazy (fun () -> Printf.printf "calcul\n"; 42)
let _ = force ex
let _ = force ex

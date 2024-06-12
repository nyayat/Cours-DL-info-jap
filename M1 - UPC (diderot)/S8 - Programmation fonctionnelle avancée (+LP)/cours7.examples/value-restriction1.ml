let c = ref (fun x -> x)
  (* type of c : ('_weak1 -> '_weak1) ref
     where here '_weak1 is a free variable! *)

let _ = c := (fun x -> x+1)
let _ = c
  (* type of c : (int -> int) ref, '_weak1 has been
     instanciated *)

let _ = !c true
  (* type error : clash between int and bool *)

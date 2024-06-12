open ListeVP

let _ = listevide
let x = cons 3 listevide
let _ = head x
let _ = head listevide (* type error *)

let _ = tail x

let _ = tail (tail x) (* assert failure *)

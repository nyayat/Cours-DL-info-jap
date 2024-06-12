open Liste

let _ = listevide
let _ = cons 3 listevide
let _ = head (cons 3 listevide)

(* erreur de typage ! *)
let _ = head listevide

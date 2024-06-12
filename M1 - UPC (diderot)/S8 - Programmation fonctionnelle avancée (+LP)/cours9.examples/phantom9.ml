open Listecount

let _ = head listevide

let l4 = cons 1 (cons 2 (cons 3 (cons 4 listevide)))

let l1 = tail (tail (tail l4))

let _ = head l1

let _ = tail (tail l1)

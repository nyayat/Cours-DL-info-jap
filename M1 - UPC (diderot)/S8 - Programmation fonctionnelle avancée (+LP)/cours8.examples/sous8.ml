let f1 = fun (`Nombre x) -> x = 0

let f2 = fun (`Nombre x) -> x = 0.0

(* Attention : type non habite ! *)
let f x = f1 x || f2 x

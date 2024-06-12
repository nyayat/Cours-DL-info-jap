type term =
  | Const of int
  | Plus of term * term
  | Opp of term

let table = Hashtbl.create 251

let hashcons x =
  try Hashtbl.find table x
  with Not_found -> Hashtbl.add table x x; x

let const i = hashcons (Const i)
let plus t1 t2 = hashcons (Plus (t1,t2))
let opp t = hashcons (Opp t)

let t1 = plus (const 2) (const 42)
let t2 = plus (const (1 + 1)) (const (73-31))
let _ = t1=t2
let _ = t1==t2

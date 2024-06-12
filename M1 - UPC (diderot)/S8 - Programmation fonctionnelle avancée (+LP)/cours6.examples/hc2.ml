module type TERMHC = sig
  type term
  val const: int -> term
  val plus: term -> term -> term
  val opp: term -> term
end

module TermHC:TERMHC = struct
  module Term = struct
    type t =
      | Const of int
      | Plus of t*t
      | Opp of t
    (* shallow comparison, uses == on substructures *)
    let equal x y = match x,y with
      | Const i, Const j -> i == j
      | Plus (t1,t2), Plus (u1,u2) ->
         t1 == u1 && t2 == u2
      | Opp t, Opp u -> t == u
      | _ -> false
    let hash = Hashtbl.hash (* generic function *)
  end

  module H = Hashtbl.Make(Term)

  type term = Term.t

  let table = H.create 251
  let hashcons x =
    try H.find table x
    with Not_found -> H.add table x x; x
  let const i = hashcons (Term.Const i)
  let plus t1 t2 = hashcons (Term.Plus (t1,t2))
  let opp t = hashcons (Term.Opp t)
end

open TermHC
let t1 = plus (const 2) (const 42)
let t2 = plus (const (1 + 1)) (const (73-31))
let _ = t1=t2
let _ = t1==t2


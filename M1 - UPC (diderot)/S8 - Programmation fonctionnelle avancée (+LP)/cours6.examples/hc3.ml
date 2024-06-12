type 'a with_hashkey = {
  node: 'a;
  hkey: int (* hash key *)
}

(* type des termes avec clef de hachage *)
type term = term_node with_hashkey
and term_node =
| Const of int
| Plus of term*term
| Opp of term

(* structure parametre *)
module Term_node = struct
  type t = term_node
  let equal x y = match x,y with
    | Const i, Const j -> i == j
    | Plus (t1,t2), Plus (u1,u2) ->
       t1 == u1 && t2 == u2
    | Opp t, Opp u -> t == u
    | _ -> false
  let hash = function
    | Const i -> i
    | Plus (t1,t2) ->
      abs(19*(19*t1.hkey+t2.hkey)+2)
    | Opp t -> abs (19 * t.hkey + 1)
end

(* signature de la structure parametre *)
module type HashedType = sig
  type t
  val equal: t -> t -> bool
  val hash: t-> int
end

(* signature de la structure obtenue *)
module type S =
  sig
    type value
    val hashcons : value -> value with_hashkey
  end
(* tres simplifiee *)

module Make(T : HashedType) : (S with type value = T.t) =
struct
  module H = Hashtbl.Make(T)
  type value = T.t
  let table = H.create 251
  let hashcons d =
    try H.find table d
    with Not_found ->
      let d_hc = {node = d; hkey = T.hash d}
      in H.add table d d_hc; d_hc
end
module H = Make(Term_node)

let const i = H.hashcons (Const i)
let plus t1 t2 = H.hashcons (Plus (t1,t2))
let opp t = H.hashcons (Opp t)

let t1 = const 2
let t2 = const (1 + 1)
let _ = t1==t2

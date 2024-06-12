type zero        (* type comptant 0 *)
type 'nat succ   (* type comptant +1 *)

module type LISTECOUNT =  sig
  type ('lon, 'ele) t
  val listevide : (zero, 'ele) t
  val estvide : ('lon, 'ele) t -> bool
  val cons : 'ele -> ('lon, 'ele) t -> ('lon succ, 'ele) t
  val head : ('lon succ, 'ele) t -> 'ele
  val tail : ('lon succ, 'ele) t -> ('lon, 'ele) t
end

module Listecount:LISTECOUNT = struct
  type ('a, 'b) t = 'b list
  let listevide = []
  let estvide = function [] -> true | _ -> false
  let cons v l  = v::l
  let head = function [] -> assert false | a::_ -> a
  let tail = function [] -> assert false | _::l -> l
end

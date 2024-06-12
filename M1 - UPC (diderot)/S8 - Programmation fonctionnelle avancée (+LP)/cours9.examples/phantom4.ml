module type LISTE = sig
  type vide
  type nonvide
  type ('vide_ou_non, 'e) t
  val listevide : (vide, 'e) t
  val cons: 'e -> ('vide_ou_non,'e) t -> (nonvide,'e) t
  val head: (nonvide, 'e) t -> 'e
end

module Liste:LISTE = struct
 type vide
 type nonvide
 type ('vide_ou_non, 'e) t = 'e list
 let listevide = []
 let cons v l = v::l
 let head = function [] -> assert false | a::_ -> a
end

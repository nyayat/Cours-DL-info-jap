module type LISTEVP =  sig
  type ('vide_ou_non, 'e) t
  val listevide : ([ `Vide ], 'e) t
  val cons : 'e -> ([< `Vide | `Nonvide ], 'e) t
             -> ([ `Nonvide ], 'e) t
  val head : ([ `Nonvide ], 'e) t -> 'e
  val tail : ([ `Nonvide ], 'e) t ->
             ([< `Vide | `Nonvide ], 'e) t
end

module ListeVP:LISTEVP = struct
  type ('vide_ou_non, 'e) t = 'e list
  let listevide = []
  let cons v l = v::l
  let head = function [] -> assert false | a::_ -> a
  let tail = function [] -> assert false | _::t -> t
end

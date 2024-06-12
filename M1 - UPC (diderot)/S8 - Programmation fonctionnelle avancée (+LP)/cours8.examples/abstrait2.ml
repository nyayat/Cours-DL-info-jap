module type NEG = sig
  type -'a t
end

(* Erreur *)
module P:NEG = struct
  type 'a t = 'a*'a
end

(* OK *)
module N:NEG = struct
  type 'a t = 'a->bool
end

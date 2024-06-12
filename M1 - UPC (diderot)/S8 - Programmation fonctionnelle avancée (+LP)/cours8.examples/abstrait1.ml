module type POS = sig
   type +'a t
end

(* OK *)
module P:POS = struct
  type 'a t = 'a*'a
end

(* Erreur *)
module N:POS = struct
  type 'a t = 'a->bool
end

(* corps plus general que l'interface *)
module A = struct let id x = x end;;
A.id;;
module type AintSig = sig val id : int -> int end;;
module Aint = (A:AintSig);;
Aint.id;;

(* interface plus general que le corps *)
module Bint = struct let id x:int = x end;;
module type BintSig = sig val id : 'a -> 'a end;;
module B = (Bint:BintSig);;

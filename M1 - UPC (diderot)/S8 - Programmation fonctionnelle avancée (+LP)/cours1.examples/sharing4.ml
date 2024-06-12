(* avec contraintes de partage *)
module Mwrite = (M:Write with type t = M.t) ;;
module Mread = (M:Read with type t = M.t) ;;

let counter = Mwrite.create();;
Mwrite.step counter;;
Mread.get counter;;

(* OK car Mwrite.t = M.t = Mread.t = int ref *)
(* NB : Pas encore de type abstrait ici *)

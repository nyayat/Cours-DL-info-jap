(* avec contraintes de partage ET types abstraits *)
module Mabs = (M:ReadWrite) ;;
module Mwrite = (Mabs:Write with type t = Mabs.t) ;;
module Mread = (Mabs:Read with type t = Mabs.t) ;;

let counter = Mwrite.create();;
Mwrite.step counter;;
Mread.get counter;;

(* OK car Mwrite.t = Mabs.t = Mread.t *)
(* Et aucun n'est compatible avec int ref *)

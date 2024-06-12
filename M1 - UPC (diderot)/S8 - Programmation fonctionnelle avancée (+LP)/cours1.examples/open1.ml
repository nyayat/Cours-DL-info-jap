(* open global *)

let x = 3;;
module A = struct let x = 3.14 end;;
module B = struct let x = "a" end;;
x;;
open A;;
x;;
open B;;
x;;
(* le dernier module ouvert a la priorite *)

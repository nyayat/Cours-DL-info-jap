(* open local, OCaml >= 3.12 *)

module A=struct let a=17 let b=42 end

let a = 1
let b = 2;;

a+b;;

A.(a+b);;
(* A open locally in expression between ( )  *)

a+b;;

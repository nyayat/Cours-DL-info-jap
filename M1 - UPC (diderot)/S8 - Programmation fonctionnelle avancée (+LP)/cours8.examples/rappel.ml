let f1 = function
  | `A x -> x = 1 | `B -> true | `C -> false;;

let f2 = function
  | `A x -> x = "a" | `B -> true | _ -> false;;

let f3 = function
  | `A -> `C | `B -> `D | x -> x;;

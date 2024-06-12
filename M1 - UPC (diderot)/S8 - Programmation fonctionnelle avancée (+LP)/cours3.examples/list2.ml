(* Une insertion avec plus de partage *)
let rec insert_opt x l = match l with
  | [] -> [x]
  | a::_ when x = a -> l
  | a::_ when x < a -> x::l
  | a::r ->
     let r' = insert_opt x r in
     if r' == r then l else a::r'

let l = [1;3;5;7];;
l == insert_opt 3 l;;

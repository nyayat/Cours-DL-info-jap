module Int = struct
  type elt = int
  let compare x y = x-y
end;;
module OrdIntStack = OrdStack (Int);;
(* or: *)
module OrdIntStack =
  OrdStack
    (struct type elt = int
            let compare x y = x-y
     end)

open OrdIntStack;;
let p = push 3 (push 9 (push 7 (empty())));;
pop p;;

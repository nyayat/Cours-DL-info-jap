let open_vlist l = (l : 'a vlist :> [> 'a vlist])
let vl = (`Cons(73,`Nil) : int vlist)
let _ = open_vlist vl

let switch (x: [`A | `B]) =
  (match x with
   | `A -> `B
   | `B -> `A
   :> [`A | `B | `C])

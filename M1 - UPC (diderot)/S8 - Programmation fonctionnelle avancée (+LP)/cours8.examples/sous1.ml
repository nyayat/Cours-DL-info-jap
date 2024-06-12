type 'a vlist = [`Nil | `Cons of 'a * 'a vlist]

type 'a wlist = [`Nil | `Cons of 'a * 'a wlist
                | `Snoc of 'a * 'a wlist]

(* 'a vlist est un sous-type de 'a wlist *)

let x = `Cons (42,`Nil)
let y = (`Cons (42,`Nil) : int vlist)
let z = (`Cons (42,`Nil) : int wlist)

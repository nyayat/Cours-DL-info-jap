type 'a vlist = [`Nil | `Cons of 'a * 'a vlist]
type 'a wlist = [`Nil | `Cons of 'a * 'a wlist
                | `Snoc of 'a * 'a wlist]

let wlist_of_vlist  l = (l : 'a vlist :> 'a wlist)

let a : int vlist = `Cons (1, `Nil)

let _ = wlist_of_vlist a

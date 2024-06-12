open Stream (* voir le cours 4 *)
module FileDS : FIFO = struct
 type 'a t = int * 'a stream * int * 'a stream

 let empty = (0, lazy Nil, 0, lazy Nil)

 let check ((nin,sin,nout,sout) as q) =
   if nin <= nout then q
   else (0, lazy Nil, nin + nout, sout ++ reverse sin)

 let add x (nin,sin,nout,sout) =
   check (nin + 1, lazy (Cons (x, sin)), nout, sout)

 let remove (nin,sin,nout,sout) =
   match Lazy.force sout with
   | Nil -> raise Empty
   | Cons (x, sout') -> x, check (nin,sin,nout-1,sout')
end

open Stream

module RealTimeQueue : FIFO = struct
  type 'a t = 'a list * 'a stream * 'a stream
  let mkqueue out = ([],out,out)
  let empty = mkqueue (lazy Nil)

  let rec rotate f en acc =
    match Lazy.force f, en with
    | Nil, [y] -> lazy (Cons (y, acc))
    | Cons (x, xs), y :: ys ->
       let acc' = lazy (Cons (y, acc)) in
       lazy (Cons (x, rotate xs ys acc'))
    | _, _ -> assert false

  let exec (en,out,s) = match Lazy.force s with
    | Cons (x, s') -> (en, out, s')
    | Nil -> mkqueue (rotate out en (lazy Nil))

  let add x (en,out,s) = exec (x::en, out, s)

  let remove (en,out,s) = match out with
    | lazy Nil -> raise Empty
    | lazy (Cons(x,out')) -> x, exec (en, out', s)
end

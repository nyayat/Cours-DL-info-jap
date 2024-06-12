(* Compilation : ocamlopt -o runreal runreal.ml *)

exception Empty

type 'a stream = 'a cell Lazy.t
and 'a cell = Nil | Cons of 'a * 'a stream

module RealTimeQueue = struct
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

open RealTimeQueue

let rec iterate_add n q =
  if n=0 then q
  else iterate_add (n-1) (add n q)

let rec iterate_remove n q =
  if n=0 then q
  else iterate_remove (n-1) (snd (remove q))

let test n =
  let _ = iterate_remove n (iterate_add n empty) in ()

let rec repeat_remove n q =
  if n=0 then q
  else let _ = remove q in repeat_remove (n-1) q

let test2 n =
  let _ = repeat_remove n (iterate_add n empty) in ()

let _ =
  match Sys.argv.(1), int_of_string (Sys.argv.(2)) with
  | "test", n -> test n
  | "test2", n -> test2 n
  | exception _ | _ ->
     failwith ("usage: "^Sys.argv.(0)^" (test|test2) <n>")

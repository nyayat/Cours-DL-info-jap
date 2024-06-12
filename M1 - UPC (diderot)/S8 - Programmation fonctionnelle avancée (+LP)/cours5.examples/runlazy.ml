(* Compilation : ocamlopt -o runlazy runlazy.ml *)

module type STREAM = sig
  type 'a stream = 'a cell Lazy.t
  and 'a cell = Nil | Cons of 'a * 'a stream

  (* concatenation de streams *)
  val (++) : 'a stream -> 'a stream -> 'a stream

  val reverse : 'a stream -> 'a stream
end

module Stream : STREAM = struct
  type 'a stream = 'a cell Lazy.t
  and 'a cell = Nil | Cons of 'a * 'a stream
  (* concatenation *)
  let rec (++) s1 s2 = lazy (app s1 s2)
  and app s1 s2 = match Lazy.force s1 with
  | Nil -> Lazy.force s2
  | Cons (h,t) -> Cons (h, t ++ s2)
  (* retourne un stream, monolithique! *)
  let reverse s =
    let rec rev acc s = match Lazy.force s with
      | Nil -> acc
      | Cons (h,t) -> rev (Cons (h, lazy acc)) t
  in lazy (rev Nil s)
end

open Stream (* voir le cours 4 *)
module FileDS = struct
 type 'a t = int * 'a stream * int * 'a stream
 exception Empty
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

open FileDS

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

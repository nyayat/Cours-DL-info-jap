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

module type STREAM = sig
  type 'a stream = 'a cell Lazy.t
  and 'a cell = Nil | Cons of 'a * 'a stream

  (* concatenation de streams *)
  val (++) : 'a stream -> 'a stream -> 'a stream

  (* stream avec juste les n premiers elements *)
  val prefix : int -> 'a stream -> 'a stream

  (* stream sans les n premiers elements *)
  val drop : int -> 'a stream -> 'a stream
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

(* recopie paresseuse des premiers n elements *)
let rec prefix n s = lazy (prefix' n s)
and prefix' n s = match n,s with
  | 0, _ -> Nil
  | _, lazy Nil -> Nil
  | _, lazy (Cons (h,t)) -> Cons (h, prefix (n-1) t)

(* suppression de n elements, monolithique! *)
let drop n s =
  let rec drop' n s = match n, s with
    | 0, _ -> Lazy.force s
    | _, lazy Nil -> Nil
    | _, lazy (Cons (_,t)) -> drop' (n-1) t
  in if n = 0 then s else lazy (drop' n s)

(* retourne un stream, monolithique! *)
let reverse s =
  let rec rev acc s = match Lazy.force s with
    | Nil -> acc
    | Cons (h,t) -> rev (Cons (h, lazy acc)) t
  in lazy (rev Nil s)
end

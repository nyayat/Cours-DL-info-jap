module FifoImp = struct
  exception Empty
  type 'a cell = Vide | Cons of 'a * 'a cell ref
  type 'a t = {mutable first: 'a cell;
               mutable last : 'a cell}
  let create () = {first = Vide; last = Vide}
  let is_empty f = (f.first = Vide)
  let add a f = match f.last with
    | Vide -> (* assert (f.first = Vide) *)
       f.first <- Cons (a, ref Vide);
       f.last <- f.first
    | Cons (_, r) ->
       r := Cons (a, ref Vide);
       f.last <- !r
  let remove f = match f.first with
    | Vide -> raise Empty
    | Cons (a, r) ->
       if f.last = f.first then f.last <- !r;
       f.first <- !r ;
       a
end;;

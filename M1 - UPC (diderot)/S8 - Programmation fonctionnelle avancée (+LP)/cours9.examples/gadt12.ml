type (_,_) seq =
  | Id : ('a,'a) seq
  | Seq : ('a -> 'b) * ('b,'c) seq -> ('a,'c) seq

let rec apply : type a b. (a,b) seq -> a -> b =
  function
  | Id -> fun x -> x (* here a = b *)
  | Seq (f,s) -> fun x -> x |> f |> apply s

let bad = Seq(truncate,Seq(string_of_float,Id))
let good = Seq(truncate,Seq(string_of_int,Id))
let _ = apply good 3.5


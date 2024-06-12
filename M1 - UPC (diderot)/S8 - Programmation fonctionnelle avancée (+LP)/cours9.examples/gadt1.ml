let silly x = match x with
  | [] -> 1
  | a::r -> match x with a'::r' -> 2

(* pattern matching non exhaustive *)

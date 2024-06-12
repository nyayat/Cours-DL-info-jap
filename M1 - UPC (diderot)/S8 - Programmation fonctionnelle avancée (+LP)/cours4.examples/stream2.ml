(* Version calculant les fact de proche en proche.
   Invariant : p = n! *)

let rec fast_fact_from n p : int stream =
  lazy (Cons (p, fast_fact_from (n+1) ((n+1)*p)))

let fast_fact_nat = fast_fact_from 0 1

let _ = take 50 fast_fact_nat

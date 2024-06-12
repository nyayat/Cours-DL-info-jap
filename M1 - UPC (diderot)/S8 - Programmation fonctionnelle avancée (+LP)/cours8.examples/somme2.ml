(* the compiler warns about missing cases *)

let rec add n m = match n with
  | S n -> S (add n m)

let two = S (S (Zero))

let _ = add two two

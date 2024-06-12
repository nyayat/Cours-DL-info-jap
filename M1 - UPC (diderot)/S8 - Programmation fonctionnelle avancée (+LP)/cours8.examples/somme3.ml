(* the compiler signals an error on unknown
    constructor *)

let rec add n m = match n with
  | Zero -> n
  | Succ n -> S (add n m)

let two = S (S (Zero))

let _ = add two two

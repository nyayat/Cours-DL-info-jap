type num = Zero | S of num

let rec add n m = match n with
  | Zero -> m
  | S n -> S (add n m)

let two = S (S (Zero))

let _ = add two two

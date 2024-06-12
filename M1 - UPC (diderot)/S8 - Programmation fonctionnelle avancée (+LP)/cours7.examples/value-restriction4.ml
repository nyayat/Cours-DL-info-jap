let twice_only f =
  (* yields a variant of f that can be applied twice *)
  (* only, and that behaves like identity after that.*)
  let counter = ref 0 in
  fun x ->
    counter := !counter+1;
    if !counter <= 2 then f x else x

(* the function double is not polymorphic   *)
(* since the ride-hand side is not a value. *)
let double = twice_only (fun x -> x@x)

let _ = double [1; 2]
let _ = double [1; 2]
let _ = double [1; 2]
let _ = double ["a"; "b"]

(* Using eta-expansion we get a polymorphic   *)
(* function, but it does not behave the same! *)
(* At each application of double_eta, a new   *)
(* counter is created. *)

let double_eta =
  fun y -> twice_only (fun x -> x@x) y

let _ = double_eta [1; 2]
let _ = double_eta [1; 2]
let _ = double_eta [1; 2]
let _ = double_eta ["a"; "b"]

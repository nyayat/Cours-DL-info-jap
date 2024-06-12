(* Compilation : ocamlopt -o runlist runlist.ml *)

exception Empty

module FifoDL = struct
  type 'a t = 'a list * 'a list
  let empty = ([], [])
  let add x (l1, l2) = (x::l1, l2)
  let remove (l1, l2) = match l2 with
  | a::l -> (a, (l1, l))
  | [] -> match List.rev l1 with
    | [] -> raise Empty
    | a::l -> (a, ([], l))
end

open FifoDL

let rec iterate_add n q =
  if n=0 then q
  else iterate_add (n-1) (add n q)

let rec iterate_remove n q =
  if n=0 then q
  else iterate_remove (n-1) (snd (remove q))

let test n =
  let _ = iterate_remove n (iterate_add n empty) in ()

let rec repeat_remove n q =
  if n=0 then q
  else let _ = remove q in repeat_remove (n-1) q

let test2 n =
  let _ = repeat_remove n (iterate_add n empty) in ()

let _ =
  match Sys.argv.(1), int_of_string (Sys.argv.(2)) with
  | "test", n -> test n
  | "test2", n -> test2 n
  | exception _ | _ ->
     failwith ("usage: "^Sys.argv.(0)^" (test|test2) <n>")


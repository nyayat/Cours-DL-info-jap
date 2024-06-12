module FifoDLCount = struct
(* instrumented to trace cost *)
type 'a t = 'a list * 'a list
let empty = ([], [])

let ncalls = ref 0 (* number of function calls *)
let cost = ref 0 (* accumulated cost *)
let incr c n = c:= !c + n
let reset () = cost := 0; ncalls :=0

let add x (l1, l2) =
  incr ncalls 1; incr cost 1; (x::l1, l2)

let remove (l1, l2) =
  incr ncalls 1;
  match l2 with
  | a::l -> incr cost 1; (a, (l1, l))
  | [] ->
     incr cost (List.length l1);
     match List.rev l1 with
     | [] -> raise Empty
     | a::l -> incr cost 1; (a, ([], l))
end

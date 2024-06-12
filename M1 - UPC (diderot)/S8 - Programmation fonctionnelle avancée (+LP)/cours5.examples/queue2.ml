module FifoDL : FIFO = struct
  type 'a t = 'a list * 'a list
  let empty = ([], [])
  let add x (l1, l2) = (x::l1, l2)
  let remove (l1, l2) = match l2 with
  | a::l -> (a, (l1, l))
  | [] -> match List.rev l1 with
    | [] -> raise Empty
    | a::l -> (a, ([], l))
end

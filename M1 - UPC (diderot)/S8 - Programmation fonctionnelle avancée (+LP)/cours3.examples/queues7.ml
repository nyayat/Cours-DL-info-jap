module FifoDL : FIFO = struct
  exception Empty
  type 'a t = 'a list * 'a list

  let empty = ([],[])
  let is_empty = function ([],[]) -> true | _ -> false

  let add x (l_in, l_out) = (x::l_in, l_out)

  let remove (l_in, l_out) = match l_out with
  | a::l -> (a, (l_in, l))
  | [] -> match List.rev l_in with
      | [] -> raise Empty
      | a::l -> (a, ([], l))
end;;

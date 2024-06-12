module FifoNaive : FIFO = struct
  type 'a t = 'a list
  exception Empty
  let empty = []
  let is_empty f = (f = [])
  let add a f = f@[a]
  let remove = function
  | [] -> raise Empty
  | a::l -> (a, l)
end;;

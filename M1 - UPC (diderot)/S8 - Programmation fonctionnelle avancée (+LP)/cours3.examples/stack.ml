module Lifo = struct
  exception Empty
  type 'a t = 'a list
  let empty = []
  let is_empty = function [] -> true | _ -> false
  let push a l = a::l
  let pop = function
    | [] -> raise Empty
    | h::r -> (h, r)
end;;

Lifo.(empty |> push 42 |> push 17 |> pop |> fst);;


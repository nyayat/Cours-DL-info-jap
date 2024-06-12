let f = function `S a -> 1 | `Z -> 2

let g = function `S _ -> 4 | _ -> 5

let h = function `S _ -> 4 | x -> f x

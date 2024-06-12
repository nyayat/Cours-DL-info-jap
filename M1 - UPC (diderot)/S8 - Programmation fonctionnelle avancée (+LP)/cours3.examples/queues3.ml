open FifoNaive

let q = empty |> add 1 |> add 2 |> add 3;;
let (x,r) = remove q;;
let (y,s) = remove r;;
let (x',r') = remove q;;
;; (* persistent ! *)

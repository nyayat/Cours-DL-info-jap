open RealTimeQueue

let _ =
  empty |> add 0 |> add 1 |> add 2 |> add 3
    |> remove |> snd |> remove |> fst

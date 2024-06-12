let rec of_list = function
  | [] -> None
  | a::r -> (* insert a (of_list r) *) (* ou bien: *)
     let o = of_list r in
     let c = Some {info=a; prev=None; next=o} in
     match o with
     | None -> c
     | Some c' -> c'.prev <- c; c ;;
(* refaire une liste, par la droite pour éviter List.rev *)
let rec toutadroite = function
  | None -> None
  | Some {next=None} as c -> c
  | Some {next=c} -> toutadroite c ;;
let to_list o =
  let rec build acc = function
    | None -> acc
    | Some {info=a;prev=c} -> build (a::acc) c
  in build [] (toutadroite o) ;;
of_list [1;2;3] |> to_list ;;

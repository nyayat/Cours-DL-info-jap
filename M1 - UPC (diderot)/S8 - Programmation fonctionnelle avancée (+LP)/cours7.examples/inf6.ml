let c = ref (fun x -> x)

let _ = c := (fun x -> x+1)

let _ = !c true

let l = []
let f l l' = if l = l' then l else l@l'
let _ = f l l
let _ = f l

let f g = (g 42) && (g "truc")

let f g h = fun x -> h (g x)

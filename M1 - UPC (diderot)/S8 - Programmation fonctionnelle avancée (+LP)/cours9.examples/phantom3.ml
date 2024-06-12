open Length

let m1 = meters 10.
let f1 = feet 40.

let _ = m1 +. m1
let _ = f1 +. f1
let _ = to_float (f1 +. f1)
let _ = m1 +. f1

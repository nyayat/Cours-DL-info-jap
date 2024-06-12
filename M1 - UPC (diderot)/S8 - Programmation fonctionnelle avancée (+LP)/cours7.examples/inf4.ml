let p x y = fun z -> z x y

let test x0 =
  let x1 = p x0 x0 in
  let x2 = p x1 x1 in
  let x3 = p x2 x2 in
  let x4 = p x3 x3 in
  let x5 = p x4 x4 in
  let x6 = p x5 x5 in
  let x7 = p x6 x6 in
  x7

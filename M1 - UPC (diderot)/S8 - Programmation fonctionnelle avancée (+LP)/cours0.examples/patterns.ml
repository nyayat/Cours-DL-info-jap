let f x y = match x,y with
  | true,false -> 1
  |  _  , true -> 2
  | false,  _   -> 3;;


let f1 x y =
  if x then
    if y then 2 else 1
  else
    if y then 2 else 3;;

let f2 x y =
  if y then 2
  else
    if x then 1 else 3;;

let tests = [true,true;false,false;false,true;true,false];;
let test f = List.map (fun (x,y) -> f x y) tests;;
test f;;
test f1;;
test f2;;

(* specifier un type *)
let (x:int) = 42;;

let triple x = (x, x, x);;

let triple (x: string) = (x, x, x);;

let triple x = ( (x,x,x) : string*string*string);;

(* pas un mecanisme de conversion *)
let (x:float) = 42;;

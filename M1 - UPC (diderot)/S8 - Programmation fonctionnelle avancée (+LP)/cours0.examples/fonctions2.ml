(* fonctions dans un record *)
type 'a foo = {n:int; f: 'a -> 'a} ;;
let v = {n=42; f=fun x -> x} ;;
v.f v.n;;

(* fonctions dans une paire *)
let fp = ( (fun x -> x+1), (fun x -> x*x) );;

(* attention aux parentheses *)
let fpp = ( fun x -> x+1, fun x -> x*x );;

(* extraction des fonctions d'une paire *)
(fst fp) 42;;
(fst fp) ((snd fp) 10);;
(fst fp) (snd fp) 10;; (* parentheses ! *)

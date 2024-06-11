dune exec ./mytop.exe "let x = (+) 33 44 let y = (+) x x"                                                      
## ANSWER
#x = 77             
#y = 154

dune exec ./mytop.exe \
"let rec fact n =
  match n with
    0 -> 1
  | _ -> ( * ) n (fact ((-) n 1))
  end
 let test = fact 10"

## ANSWER:
#fact = fun n -> ...
#test = 3628800

dune exec ./mytop.exe \
"let rec size tree =
  match tree with
   Leaf() -> 0
  |Node(x,g,d) -> (+) 1 ((+) (size g) (size d))
 end
 let test = size (Node(1,Node(2,Leaf(),Leaf()),Leaf()))"

## ANSWER:
#size = fun tree -> ...
#test = 2

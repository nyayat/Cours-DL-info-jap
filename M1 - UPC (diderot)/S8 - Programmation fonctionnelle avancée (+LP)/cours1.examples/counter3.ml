(* syntaxe alternative : signature apres struct ... end *)
module Counter =
  (struct
    let c = ref 0
    let incr () = c:= !c+1
    let show () = !c
   end: CounterFullItf);;

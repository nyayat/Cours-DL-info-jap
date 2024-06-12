(* careful, danger ahead, mutability ! *)
type 'a cell = { info:'a;
                 mutable next:'a cell option;
                 mutable prev:'a cell option
               };;

(* la bibliotheque standard definit :
type 'a option = None | Some of 'a
 *)

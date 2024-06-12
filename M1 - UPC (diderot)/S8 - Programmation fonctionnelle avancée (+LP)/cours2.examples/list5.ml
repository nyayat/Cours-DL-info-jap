(* insertion, temps constant *)
let insert a = function
  | None -> Some {info=a; prev=None; next=None}
  | Some c as here ->
     let p = c.prev in
     let c' = Some {info=a; prev=p; next=here} in
     c.prev <- c';
     begin match p with
     | None -> ()
     | Some cp -> cp.next <- c'
     end;
     c'
;;
let l = insert 1 None ;;
let l'= insert 2 l ;; (* cycles ! *)

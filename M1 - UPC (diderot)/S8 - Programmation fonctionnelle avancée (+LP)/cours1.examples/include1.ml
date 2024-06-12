module Counter =
  struct
    let c = ref 0
    let incr () = c:= !c+1
    let show () = !c
  end
;;

module Counter2 =
struct
  include Counter
  let step n = c:=!c+n
  let incr () =
    Printf.eprintf "Inside Counter2\n%!"; step 1
end;;


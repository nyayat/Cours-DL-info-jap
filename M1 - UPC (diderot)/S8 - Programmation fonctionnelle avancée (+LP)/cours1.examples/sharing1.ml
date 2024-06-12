module M =
struct
  type t = int ref
  let create() = ref 0
  let step x = x:=!x + 1;;
  let get x =
    let v = !x in
    if v>0 then (decr x; v) else failwith "Empty"
end;;


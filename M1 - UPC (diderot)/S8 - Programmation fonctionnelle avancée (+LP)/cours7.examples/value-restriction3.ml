let fastrev = function list ->
  let left = ref list
  and right = ref []
  in begin
    while !left <> [] do
      right := (List.hd (!left)) :: !right;
      left :=  List.tl (!left)
    done;
    !right
  end

(* OK ! *)
let _ = fastrev [1;2;3;4]
let _ = fastrev [true;true;false;false]

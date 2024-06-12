let size = 1000
let a = Weak.create size
let byebye v = print_string "killing: ";
  print_int (List.hd v); print_newline ()
let _ = for i=0 to size-1 do
          let v = [i;i+1;i+2;i+3;i+4] in
          Weak.set a i (Some v);
          Gc.finalise byebye v
done
let _ = Gc.compact ()

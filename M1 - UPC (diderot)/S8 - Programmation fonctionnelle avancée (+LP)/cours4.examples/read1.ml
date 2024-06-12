(* Wrong: relies on evaluation order *)

let rec read ic =
  try
    (input_line ic) ^ (read ic)
  with
    End_of_file -> ""
;;

print_string (read (open_in "myfile"))

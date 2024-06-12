(* Function read corrected *)

let rec read ic =
  try
    let thisline = input_line ic
    in thisline ^ (read ic)
  with
    End_of_file -> ""
;;

print_string (read (open_in "myfile"))

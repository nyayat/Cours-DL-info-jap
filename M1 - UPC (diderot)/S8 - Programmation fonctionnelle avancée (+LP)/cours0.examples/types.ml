let index = input_line (open_in "data");;

let dict =  [1,"one"; 2,"two"];;

List.assoc index dict;;

List.assoc (int_of_string index) dict;;


let rec interactive_loop () =
  welcome_message ();
  let rec loop () =
    begin try
      read () |> eval |> print
    with exn ->
      Printf.printf "Error: %s\n%!" (Printexc.to_string exn)
    end;
    loop ()
  in
  loop ()

and welcome_message () =
  Printf.printf "
  ====================================================\n
   Welcome to the incredible Marthe interactive loop! \n
  ====================================================\n
"

and read () =
  invite (); input_line stdin |> parse

and invite () =
  Printf.printf "> %!"

and parse input =
  let lexbuf = Lexing.from_string input in
  Parser.phrase Lexer.token lexbuf

and print e =
  Printf.printf ":- %s\n%!" (Printer.string_of_exp e)

and eval e =
  e

let main = interactive_loop ()

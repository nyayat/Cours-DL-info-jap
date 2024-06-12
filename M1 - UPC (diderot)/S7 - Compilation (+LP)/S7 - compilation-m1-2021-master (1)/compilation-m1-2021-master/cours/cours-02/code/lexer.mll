{
  (* Prelude *)
  open Parser
}

rule token = parse
| ['0'-'9']+ as x { INT (int_of_string x) }
| '+' { PLUS }
| '*' { STAR }
| ' ' { token lexbuf }
| eof { EOF }

{
  (* Postlude *)
}

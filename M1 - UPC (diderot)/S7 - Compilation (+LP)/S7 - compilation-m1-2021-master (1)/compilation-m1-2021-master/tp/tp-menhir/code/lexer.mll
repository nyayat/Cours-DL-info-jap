{ (* Emacs, open this file with -*- tuareg -*- *)
  open Parser
}

let layout = ' ' | '\t' | '\n'
let number = ['0'-'9']+
let identifier = ['a'-'z']['A'-'Z' '0'-'9' 'a'-'z' '_']*

rule token = parse
| eof             { EOF }
| layout          { token lexbuf }
| number as i     { INT (int_of_string i) }
| identifier as s { ID s }
| "+"             { PLUS }
| _ as c {
  failwith (Printf.sprintf "Invalid character: %c\n" c)
}

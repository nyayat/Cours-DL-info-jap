(** The (subset of) x86-64 assembly language. *)

module AST = X86_64_AST

let name = "x86-64"

type ast = AST.t

let no_parser () =
  Error.global_error
    "during source analysis"
    "There is no parser for X86-64 in flap."

let parse _ _ =
  no_parser ()

let parse_filename _ =
  no_parser ()

let extension =
  ".s"

let executable_format =
  false

let parse_string _ =
  no_parser ()

let print_ast ast =
  X86_64_PrettyPrinter.(to_string program ast)

include X86_64_Typechecker
include X86_64_Interpreter

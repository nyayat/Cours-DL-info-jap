(** There is no typechecker for x86-64 programs in flap. *)

type typing_environment = unit

let initial_typing_environment () = ()

let typecheck () _ = ()

let print_typing_environment () = ""

module List = struct
  let push r x = r := x :: !r

  let last l = List.(hd (rev l))

  let fold_map f init xs =
    let rec aux accu ys = function
      | [] ->
         (accu, List.rev ys)
      | x :: xs ->
         let accu, y = f accu x in
         aux accu (y :: ys) xs
    in
    aux init [] xs

  module Monad : sig

    type 'a nondeterministic
    type 'a t = 'a nondeterministic

    val return : 'a -> 'a t
    val fail : 'a t
    val ( >>= ) : 'a t -> ('a -> 'b t) -> 'b t
    val pick : 'a list -> 'a t
    val run : 'a t -> 'a list

    val map : ('a -> 'b t) -> 'a list -> 'b list t

  end = struct

    type 'a nondeterministic = 'a list
    type 'a t = 'a nondeterministic

    let return x = [x]
    let fail = []

    let ( >>= ) x f =
      List.(flatten (map f x))

    let pick x = x

    let run x = x

    let map f xs =
      let rec aux = function
        | [] ->
           return []
        | x :: xs ->
           f x >>= fun y ->
           aux xs >>= fun ys ->
           return (y :: ys)
      in
      aux xs

  end

end

module Unix = struct

  let line_of_command cmd =
    let cin = Unix.open_process_in cmd in
    let line = input_line cin in
    let status = Unix.close_process_in cin in
    (status, line)

end

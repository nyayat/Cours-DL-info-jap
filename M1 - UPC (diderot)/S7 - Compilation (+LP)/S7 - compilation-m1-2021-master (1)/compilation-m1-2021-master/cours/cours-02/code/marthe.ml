open Ast

let parse lexbuf = Parser.phrase Lexer.token lexbuf

let interpret : e -> int =
  (* Le paramètre [env] est une liste associative contenant la valeur associée
     aux indices de sommation.

     La fonction d'évaluation est définie par cas sur la forme de l'arbre. *)
  let rec aux env = function
    (* Pour évaluer une expression de la forme "e1 + e2", on évalue [e1] en un
       entier, on évalue [e2] en un autre entier, puis on fait la somme des deux
       entiers. *)
    | EPlus (e1, e2) -> aux env e1 + aux env e2

    (* Même raisonnement pour la multiplication. *)
    | EMult (e1, e2) -> aux env e1 * aux env e2

    (* Une expression qui est un entier s'évalue en cet entier. *)
    | EInt x -> x

    (* Pour évaluer une expression de la forme "sum (x, start, stop, body)". *)
    | ESum (x, start, stop, body) ->
      (* On évalue [start]. *)
      let vstart = aux env start
      (* On évalue [stop]. *)
      and vstop = aux env stop
      in
      (* On itère sur toutes les valeurs [i] de [start] à [stop] et on accumule
         les sommes intermédiaires dans la variable [accu]. *)
      let rec iter i accu =
        if i > vstop then
          accu
        else
          (* L'évaluation de [body] se fait dans un environnement où l'indice
             [x] est associé à la valeur [i]. *)
          iter (i + 1) (accu + aux ((x, i) :: env) body)
      in
      iter vstart 0

    (* Une expression qui est variable s'évalue en la valeur associée à cette
       variable dans l'environnement. *)
    | EVar x ->
      List.assoc x env
  in
  aux []

let process filename =
  let cin = open_in filename in
  let ast = parse (Lexing.from_channel cin) in
  let x = interpret ast in
  Printf.printf "Result: %d\n%!" x

let main =
  Array.iteri (fun i filename -> if i >= 1 then process filename) Sys.argv

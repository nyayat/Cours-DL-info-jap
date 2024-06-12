type 'a ty =
  | Int : int ty
  | String : string ty
  | Pair : 'a ty * 'b ty -> ('a * 'b) ty

let rec printf : type a. a ty -> a -> unit =
  function
  | Int -> print_int (* ici a est int *)
  | String -> print_string (* ici a est string *)
  | Pair (b, c) -> (* ici a est une paire *)
     fun (vb,vc) -> printf b vb; printf c vc

let ( ++ ) = fun x y -> Pair (x,y)

let _ = printf ((Int++String)++Int) ((4," then "),5)

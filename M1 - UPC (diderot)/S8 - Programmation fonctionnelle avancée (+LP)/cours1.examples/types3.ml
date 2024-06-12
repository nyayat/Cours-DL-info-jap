module type T =
  sig
    type t (* abstrait *)
    val c: t
  end;;

module M1 : T =
  struct
    type t = int
    let c = 42
  end;;

module M2 : T =
  struct
    type t = int
    let c = 17
  end;;

M1.c = M2.c;; (* types differents *)

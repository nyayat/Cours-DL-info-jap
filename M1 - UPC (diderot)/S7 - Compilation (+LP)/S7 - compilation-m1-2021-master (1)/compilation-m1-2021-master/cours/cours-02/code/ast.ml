type e =
  | EInt  of int                 (** Ex: "42", "31"              *)
  | EVar  of string              (** Ex: "x", "y", "foo"         *)
  | EPlus of e * e               (** Ex: "1 + 2", "2 * 3 + 4"    *)
  | EMult of e * e               (** Ex: "1 * 2", "(1 + 2) * 3"  *)
  | ESum  of string * e * e * e  (** Ex: "sum (x, 1, 10, x * x)" *)

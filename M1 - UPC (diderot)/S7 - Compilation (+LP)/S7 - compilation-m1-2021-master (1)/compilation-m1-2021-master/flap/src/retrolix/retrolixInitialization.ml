open Optimizers

(** Register some compilers that process Retrolix programs. *)
let initialize () =
  Languages.register (module Retrolix);
  Compilers.(register (optimizing_compiler (module Identity (Retrolix))));
  Compilers.(register (optimizing_compiler (module FopixToRetrolix)))

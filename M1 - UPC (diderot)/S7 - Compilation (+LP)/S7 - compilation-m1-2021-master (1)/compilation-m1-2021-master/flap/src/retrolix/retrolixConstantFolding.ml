open RetrolixAST
open RetrolixUtils

let activated = ref false

module Source = Retrolix

let shortname = "cf"

let longname = "constant folding"

(** {2 The Analysis Itself} *)

module ConstantDomain =
  struct
    let global_variables = ref []

    module D =
      struct
        type t =
          | Bot
          | Const of RetrolixAST.literal
          | Top

        let print x =
          match x with
          | Bot ->
             PPrint.string "Bot"
          | Const l ->
             RetrolixPrettyPrinter.literal l
          | Top ->
             PPrint.string "Top"

        let equal =
          Stdlib.(=)

        let compare =
          Stdlib.compare

        let le x y =
             failwith "Student! This is your job!"

        let bot =
             failwith "Student! This is your job!"

        let lub x y =
             failwith "Student! This is your job!"
      end

    module DV = RetrolixDataflowUtils.PerLValueProperty(D)
    include DV

    (* This function sets to [Top] every lvalue that may have been modified by
       an opaque function call: caller-saved registers, global variables. *)
    let clobber_registers_and_globals x =
         failwith "Student! This is your job!"


    let transfer (lab, insn) x =
       failwith "Student! This is your job!"
  end

module ConstantAnalysis = RetrolixDataflowEngines.Default(ConstantDomain)

(** {2 Putting Everything Together} *)

let error lab msg =
  Printf.eprintf "%sundefined behavior (%s)\n"
    (ExtPPrint.to_string (RetrolixPrettyPrinter.label 0) lab)
    msg;
  exit 1

let analyze ((locals, _) as block) =
     failwith "Student! This is your job!"


let rewrite sol (lab, insn) =
  let _, r = sol lab in
     failwith "Students! This is your job!"

let translate p =
  ConstantDomain.global_variables := RetrolixUtils.global_variables p;
  RetrolixUtils.transform_blocks analyze rewrite p

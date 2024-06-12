open RetrolixAST
open RetrolixUtils

let activated = ref false

module Source = Retrolix

let shortname = "dce"

let longname = "dead-code elimination"

(** {2 The Analysis Itself} *)

module LivenessDomain =
  struct
    type t = LValueSet.t

    let print = LValueSet.print

    let equal = LValueSet.equal

    let compare = LValueSet.compare

    let bot =
         failwith "Students! This is your job!"

    let le =
         failwith "Students! This is your job!"

    let lub =
         failwith "Students! This is your job!"

    let global_variables =
      ref bot



    let gen insn =
      failwith "Students! This is your job!"

    let kill insn =
      failwith "Students! This is your job!"

    let transfer (_, insn) liveout =
      failwith "Students! This is your job!"
  end

module LivenessAnalysis = RetrolixDataflowEngines.Default(LivenessDomain)

(** {2 Putting Everything Together} *)

let analyze block =
  failwith "Students! This is your job!"

let rewrite sol (lab, insn) =
     failwith "Students! This is your job!"

let translate p =
  LivenessDomain.global_variables :=
    LValueSet.of_list
    @@ List.map (fun v -> `Variable v)
    @@ RetrolixUtils.global_variables p;
  RetrolixUtils.transform_blocks analyze rewrite p

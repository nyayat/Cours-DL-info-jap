module type HTML = sig
  type +'a elt
  val pcdata : string -> [> `Pcdata ] elt
  val span : [< `Span | `Pcdata  ] elt list
             -> [> `Span ] elt
  val div : [< `Div | `Span | `Pcdata ] elt list
            -> [> `Div ] elt
end
module Html : HTML = struct
  type raw =
    | Node of string * raw list
    | Pcdata of string
  type 'a elt = raw
  let pcdata s = Pcdata s
  let div l = Node ("div",l)
  let span l = Node ("span",l)
end


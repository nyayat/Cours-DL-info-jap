(** Exploring phantom types *)

(* this does not work: the equality 'a plist = ilist
   allows to unify e plist with ne plist, and if we
   suppress the equality, then we can no longer do pattern matching *)

type e
type ne

module AList : sig
  type ilist = private Nil | Cons of int * ilist
  type 'a plist = ilist
  val nil : e plist
  val cons : int -> 'a plist -> ne plist
end = struct
  type ilist = Nil | Cons of int * ilist
  type 'a plist = ilist
  let nil = Nil
  let cons x xs = Cons (x,xs)
end

let hd : ne AList.plist -> int = function
  | AList.Nil -> assert false
  | AList.Cons (x,_) -> x;;

hd AList.nil;;

(* returns Exception: Assert_failure ("", 141, 17). *)

(** A working example from https://ocaml.janestreet.com/?q=node/11 *)

type readonly
type readwrite

module PRef : sig
  type 'a t
  val create : int -> readwrite t
  val set : readwrite t -> int -> unit
  val get : 'a t -> int
  val readonly : 'a t -> readonly t
end
  =
struct
  type 'a t = Ref.t
  let create = Ref.create
  let set = Ref.set
  let get = Ref.get
  let readonly x = x
end

type readonly
type readwrite
type immutable

module Ref : sig
  type t
  val create : int -> t
  val set : t -> int -> unit
  val get : t -> int
end
  =
struct
  type t = int ref
  let create x = ref x
  let set t x = t := x
  let get t = !t
end

module IRef : sig
  type 'a t
  val create_immutable : int -> immutable t
  val create_readwrite : int -> readwrite t
  val readonly : 'a t -> readonly t
  val set : readwrite t -> int -> unit
  val get : 'a t -> int
end
  =
struct
  type 'a t = Ref.t
  let create_immutable = Ref.create
  let create_readwrite = Ref.create
  let readonly x = x
  let set = Ref.set
  let get = Ref.get
end

let sumrefs reflist =
  List.fold_left (+) 0 (List.map PRef.get reflist)

let increfs reflist =
  List.iter (fun r -> PRef.set r (PRef.get r + 1)) reflist


(** explanation of the issues from Richard Jones, libvirt
    http://camltastic.blogspot.com/2008/05/phantom-types.html
 *)


type 'a t = float


What does it do, and how can you create anything of this type? The answer is
that any float has this type, as you can prove very easily in the OCaml
toplevel:


# (3.0 : unit t);;
- : unit t = 3.
# (10.4 : string t);;
- : string t = 10.4


Because the 'a isn't needed, it can be set to any type (unit and string in the
examples above).

This isn't yet the "phantom" type though. It's tempting to think we could write
a function which only works on string t (I'll call them "stringies"):


# let add_stringies (a : string t) (b : string t) = (a +. b : string t) ;;
val add_stringies : string t -> string t -> string t = <fun>


But in fact this function does not work!


# add_stringies (3.0 : unit t) 5.0 ;;
- : string t = 8.


This is because unit t and string t can be freely unified with each other
because the compiler knows that both are really just floats:


# ((3.0 : unit t) : string t) ;;
- : string t = 3.



To prevent this "rogue" unification and allow us to write a function like
add_stringies correctly, we have to hide the real type of t inside a module,
like this:


module T : sig
  type 'a t
  val add_stringies : string t -> string t -> string t
  val mkstringie : float -> string t
  val mkunit : float -> unit t
end = struct
  type 'a t = float
  let mkstringie f = f
  let mkunit f = f
  let add_stringies a b = a +. b
end


(** from Vincent ... *)

module Liste = (struct
    type vide
    type nonvide
    type ('a, 'b) liste = 'b list
    let listevide : (vide, 'b) liste = []
    let cons (v : 'b) (l : ('a, 'b) liste) : (nonvide, 'b) liste = v::l
    let head (l : (nonvide, 'b) liste) = match l with
      | [] -> assert false
      | a::_ -> a
  end : sig
    type vide
    type nonvide
    type ('a, 'b) liste
    val listevide : (vide, 'b) liste
    val cons : 'b -> ('a, 'b) liste -> (nonvide, 'b) liste
    val head : (nonvide, 'b) liste -> 'b
  end);;

open Liste;;

listevide;;
cons 3 listevide;;
head (cons 3 listevide);;
head listevide;;


module Liste = (struct
  type ('a, 'b) liste = 'b list
  let listevide : ([ `Zero ], 'b) liste = []
  let cons (v : 'b) (l : ('a, 'b) liste) : ([ `Succ of 'a ], 'b) liste = v::l
  let head (l : ([ `Succ of 'a ], 'b) liste) : 'b = match l with
    | [] -> assert false
    | a::_ -> a
  let tail (l : ([ `Succ of 'a ], 'b) liste) : ('a, 'b) liste = match l with
    | [] -> assert false
    | _::l -> l
end : sig
  type ('a, 'b) liste
  val listevide : ([ `Zero ], 'b) liste
  val cons : 'b -> ('a, 'b) liste -> ([`Succ of 'a], 'b) liste
  val head : ([`Succ of 'a], 'b) liste -> 'b
  val tail : ([`Succ of 'a], 'b) liste -> ('a, 'b) liste
end)

type zero
type 'a succ

type ('a, 'b) liste = 'b list;;

let vide : (zero,'a) liste = [] ;;
let cons : 'a -> ('b,'a) liste -> ('b succ, 'a) liste = fun x xs -> x::xs;;

module Listeadt = (struct
  type ('a, 'b) liste = 'b list
  type zero
  type 'a succ
  let listevide : (zero, 'b) liste = []
  let cons (v : 'b) (l : ('a, 'b) liste) : ('a succ, 'b) liste = v::l
  let head (l : ('a succ, 'b) liste) : 'b = match l with
    | [] -> assert false
    | a::_ -> a
  let tail (l : ('a succ, 'b) liste) : ('a, 'b) liste = match l with
    | [] -> assert false
    | _::l -> l
end : sig
  type ('a, 'b) liste
  type zero
  type 'a succ
  val listevide : (zero, 'b) liste
  val cons : 'b -> ('a, 'b) liste -> ('a succ, 'b) liste
  val head : ('a succ, 'b) liste -> 'b
  val tail : ('a succ, 'b) liste -> ('a, 'b) liste
end)

module Length : sig
  type 'a t
  val meters : float -> [`Meters] t
  val feet : float -> [`Feet] t
  val (+.) : 'a t -> 'a t -> 'a t
  val to_float : 'a t -> float
end = struct
  type 'a t = float
  (*
  external meters : float -> [`Meters] t = "%identity"
  external feet : float -> [`Feet] t = "%identity"
  let (+.) = (+.)
  external to_float : 'a t -> float = "%identity"
  *)
  let meters f = f
  let feet f = f
  let (+.) = (+.)
  let to_float f = f
end

open Length
open Printf

let () =
  let m1 = meters 10. in
  let m2 = meters 20. in
  printf "10m + 20m = %g\n" (to_float (m1 +. m2));
  let f1 = feet 40. in
  let f2 = feet 50. in
  printf "40ft + 50ft = %g\n" (to_float (f1 +. f2));
  (*printf "10m + 50ft = %g\n" (to_float (m1 +. f2)) (* error *) *)

(* typed embedded DSL *)

module Term = (struct
  type raw = Z | S of raw | P of raw | IsZ of raw | If of raw * raw * raw
  type 'a t = raw
  let rec eval = function
      Z -> 0 | S e -> (eval e)+1
    | P e -> (eval e)-1 | IsZ e -> (eval e)=0
    | If (b,e1,e2) -> if eval b then eval e1 else eval e2
  end : sig
    type 'a t
    val eval : 'a t -> 'a
end)

(* -------------------------------- *)

(* listes vides/non vides avec variants *)

type 'a vlist = [ `E | `C of 'a * 'a vlist];;

let tl = function `C (_,r) -> r;;

let emptyv = `E;;

let nemptyv = `C (3, `E);;

tl nemptyv;;

tl emptyv;;

(* --------------------------------------------- *)

(* variants polymorphes pour HTML, Pierre Chambart *)

module HTML : sig
  type +'a elt
  val pcdata : string -> [> `Pcdata of int ] elt
  val span : [< `Span | `Pcdata of int ] elt list-> [> `Span ] elt
  val div : [< `Div | `Span | `Pcdata of int ] elt list-> [> `Div ] elt
end = struct

  type raw =
    | Node of string * raw list
    | Pcdata of string

  type 'a elt = raw

  let pcdata s : [> `Pcdata of int ] elt = Pcdata s
  let div l = Node ("div",l)
  let span l = Node ("span",l)

end

open HTML
let x = div [ pcdata "" ]
let x = span [ div [] ]
let x = span [ span [] ]
let f s = div [ s; pcdata ""]
let f' s = div [ s; span []]
let f'' s = div [ s; span []; pcdata "" ]


(* example base sur le message de Jacques Garrigue
   http://caml.inria.fr/pub/ml-archives/caml-list/2001/09/2915ad47e671450ac5acefe4d8bd76fb.en.html
 *)

module A =
(struct
   type (-'a) gtkobj = int
   type widget = [`widget]
   type container = [`widget|`container]
   type box = [`widget|`container|`box]
   let mkbox : box gtkobj = 1
   let mkcontainer : container gtkobj = 2
   let mkwidget : widget gtkobj = 3
  end :
  sig
   type (-'a) gtkobj
   type widget = [`widget]
   type container = [`widget|`container]
   type box = [`widget|`container|`box]
   val mkbox : box gtkobj
   val mkcontainer : container gtkobj
   val mkwidget : widget gtkobj
  end
);;

open A;;
(mkbox : box gtkobj :> container gtkobj);;

(mkcontainer : container gtkobj :> box gtkobj);; (* fails *)

(* test des GADT dans la 3.13 *)

(* les combinateurs pour le parallelisme *)

type ('a,'b) net =
  | Seq: ('a -> 'b) -> ('a,'b) net
  | Pipe: ('a,'b) net * ('b,'c) net -> ('a,'c) net
;;

(* a simple recursive operation on the structure *)

let rec flatten : type a b. (a,b) net -> (a,b) net = fun n ->
  match n with
  | Seq f -> Seq f
  | Pipe (n1,n2) ->
      match (flatten n1, flatten n2) with
	(Seq f, Seq g) -> Seq(fun x -> g(f x))
      | n1,n2 -> Pipe(n1,n2)
;;

let p = Pipe (Seq (fun x -> float x), Seq (fun f -> truncate f));;

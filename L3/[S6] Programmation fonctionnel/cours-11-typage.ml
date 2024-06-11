(** Le typage d'OCaml : Exemples de la séance du 26/11/2020 *)

(** Ceci est à regarder en complément du fichier cours-11-typage.md *)

(** I) Des exemples de définitions de types *)

(** Remarque sur les abréviations de type : OCaml ne s'en sert dans
    l'affichage des types que si une indication lui a été donnée
    au départ. Mais l'abréviation reste bien synonyme de son contenu. *)

type truc = int
let x = 3 (* type affiché : int, et non truc *)
let y : truc = 3 (* type affiché : truc *)
let _ = x+y (* il s'agit bien de deux manières de parler d'entiers *)
let _ = (fun (t:truc) -> t) 3 (* et un int servir de truc *)

(** Si on veut forcer un affichage personnalisé des types,
    on peut aussi définir un type algébrique "enrobant" un autre type: *)

type chose = Chose of int
let une_chose = Chose 3

(** Même si chose est similaire à int, il n'en est plus synonyme,
    il faut convertir explicitement *)

let chose_of_int n = Chose n  (* int -> chose *)
let int_of_chose = function Chose n -> n (* chose -> int *)


(** Suite à une question, quelques mots de plus sur l'exemple
    du type result. Ce type, qui a deux paramètres de types, est
    défini dans la bibliothèque standard OCaml, et ressemble à ceci : *)

type ('a, 'e) result =
| Ok of 'a
| Error of 'e

(** Un exemple d'utilisation (via une abréviation de type) *)

type monresultat = (int -> int, string) result

let exemple_ok : monresultat = Ok (fun x -> x+1)
let exemple_ko : monresultat = Error "message"

(** Autre exemple, une définition d'arbres avec des données aux noeuds
    et aux feuilles (chacunes de types potentiellement différentes) *)

type ('a,'b) tree =
| Node of 'a * ('a,'b) tree * ('a,'b) tree (* constructeur Node à 3 args *)
| Leaf of 'b

let exemple = Node ("bla",Leaf 3, Leaf 7)   (* : (string,int) tree *)

(** Remarque : cette définition générale d'arbre peut redonner des
    cas plus particuliers si on met des () (unique constructeur du
    type unit) aux endroits où on ne veut pas de données finalement.
    C'est un peu lourd comme méthode, donc on préfère souvent définir
    un type algébrique "sur mesure" plutôt que "boucher des trous" avec
    unit. *)

let exemple = Node ((),Leaf 3, Leaf 5)  (* : (unit,int) tree *)
let exemplebis = Node (true,Leaf (), Leaf ()) (* : (bool,unit) tree *)


(** II) Variables de types généralisées ou non *)

(** Si un let commence syntaxiquement par une fonction, les variables
    de son type sont généralisées, et donc "universelles". Bref, on
    pourra se servir de cette fonction de manière polymorphe par la
    suite. *)

let f = fun x -> x in (f 3, f true)
(** ici f : 'a -> 'a est utilisé ensuite comme int->int puis comme bool->bool *)

(** Par contre on ne généralise pas les variables d'un let quand il
    commence par une application. Ces variables non-généralisées,
    dites faibles ou existentielles, sont notées '_a '_b etc. *)

let f = List.map (fun x -> x)  (* '_a list -> '_a list *)

(** On ne peut alors se servir de f sur un seul type, après cela reste
    figé : *)

let _ = f [1;2;3] (* ok, mais maintenant... *)
let _ = f         (* f est uniquement un int list -> int list *)
let _ = f [true;false] (* et donc ceci échoue *)

(** Pour éviter cela, écrire tous les arguments explicitement, plutôt
    que l'écriture précédente utilisant une application partielle. *)

let f l = List.map (fun x -> x) l  (* 'a list -> 'a list *)
(* ou de manière équivalente : *)
let f = fun l -> List.map (fun x -> x) l (* idem *)

(** En effet, ce dernier f est bien syntaxiquement une fonction, donc
    on aura bien généralisation de ses variables de type. *)

(** Pourquoi cette restriction ? Dans le cas de structures impératives,
    en généralisant tous les let on mettrait en danger la sureté de
    l'exécution, par exemple on ne serait plus sûr que les listes
    contiennent partout des éléments du même type, ce qui mènerait
    rapidement à des plantages du type `Segmentation Fault`. *)

let r = ref [] ;;  (* r : '_a list ref  *)

r := 1 :: !r ;; (* Ok, mais fige alors le type de r à int list ref *)
r := true :: !r ;;  (* Refusé car r est ici un int list ref *)
List.nth !r 0 + List.nth !r 1 (* Si on acceptait la ligne précédente,
                        on aura une liste d'entiers et booléens ??!! *)



(** III) inférence de type et unification *)

(** Par exemple, unifions les deux types suivants :

'a -> int
=?
bool -> 'b

Oui c'est unifiable , à condition de forcer 'a := bool et 'b := int
Le résultat commun est donc bool -> int. *)

(** Exemple complet, typons l'expression suivante : *)

fun f x y -> f y x;;

(** Dans sa forme primitive, c'est en fait trois fonctions imbriquées
    contenant deux applications : *)

fun f -> fun x -> fun y -> (f y) x;;

(** En suivant l'algorithme W donné dans le cours cette semaine,
    on type progressivement les fonctions en constituant l'environnment
    de typage suivant, puis on type le corps de ces fonctions

E=(f:α);(x:β);(y:γ)

typons (f y) x
 typons f y
  typons f : on regarde l'env E, pour l'instant f : α
  typons y : γ (aussi en regardant E)
  f est appliqué à y, on unifie donc le type de f avec γ->δ
  (ou δ est une nouvelle variable).
  Cette unification mène à dire que α := γ->δ
  Et le type de f y : δ

 typons x : β
 (f y) est appliqué à x, on unifie le type de (f y) avec β->ε
 (où ε est une nouvelle variable).
 Cette unification mène à δ := β->ε
 Et le type de (f y) x : ε

Pour le type du tout, on reconstitue le type des trois fonctions:

fun f -> fun x -> fun y -> (f y) x;;
    α ->     β ->     γ ->    ε

Sauf que certaines variables de types sont maintenant définies, donc
le type précédent est en fait :

    (γ->(β->ε)) -> β -> γ -> ε
Ce que OCaml écrit finalement :

    ('a -> 'b -> 'c) -> 'b -> 'a -> 'c
*)

(** IV) Exercise 1 de l'examen 2019-2020 *)

(** Donner les types des expressions suivantes : *)
fun x -> x + 1 ;;
fun x -> (x, x) ;;
fun x y -> Some (x, y);;
(fun x -> (x, x)) 0 ;;
(fun x y -> (x, y)) 0 1;;
(fun x y -> (x, y)) 0;;
fun f x y -> f y x;;
fun f -> fun x -> fun y -> (f y) x;;

(** Solution : demandez à OCaml ! *)

(** Donner des expressions dans le type int * int -> int * int *)

(** Réponse : une expression générale comme l'identité (fun x -> x)
    a bien int * int -> int * int parmi ses types possibles ... *)
let _ : (int -> int) -> (int -> int) = fun x -> x;;

(** Mais autant essayer de donner une expression avec *exactement*
    ce type. Par exemple: *)

let _ = fun f x -> 1+f (x+1)  (* les deux +1 forcent le type int partout *)
let _ = fun f x -> x + f 3 (* une des multiples autres solutions possibles *)

(** Même question pour ('a -> 'b) -> ('b -> 'c) -> ('a -> 'c) *)

(** Réponse: *)

let _ = fun f g x -> g (f x)

(** D'où cela sort ? en lisant le type ci-dessus, on remarque
    deux fonctions en arguments, de types respectifs ('a->'b) et
    ('b->'c). Puis un troisème argument, de type 'a, et enfin
    un résultat de type 'c. Si on nomme ces arguements, on s'oriente
    donc vers une expression de la forme fun f g x -> ... .
    Reste à voir comment obtenir du 'c avec tout ça, et l'expression
    (g (f x)) s'impose rapidement vu les types de chacuns.
    C'est en fait l'écriture de la composition de fonctions g∘f.
    Usuellement on l'écrit dans l'autre ordre, mais le type est
    alors moins élégant à lire (moins "transitif").
*)

let compose f g = fun x -> f (g x)
(* ('a -> 'b) -> ('c -> 'a) -> 'c -> 'b *)

(** V) Remarque complémentaire : type plus large après réduction

Astuce indiquée jeudi: d'abord évaluer la phrase avant de typer,
souvent le résultat du calcul est plus simple que l'expression
de départ.

Oui mais méfiance ...

La plupart du temps, lorsqu'on évalue une expression OCaml, le type
du résultat est exactement le même que le type de l'expression de départ.
La théorie nous dit même que le type de l'expression de départ est
encore un type valide du résultat. Mais la réciproque n'est pas
obligatoire! Il est possible (quoique rare) que le type du résultat
soit plus général que celui du départ. L'évaluation peut en effet faire
sauter quelques contraintes. Par exemple : *)

let _ = List.hd [(fun x -> x); (fun x -> x+1)];;

(** Type initial : int -> int, à cause du second élément de la liste
Mais l'évaluation mène à (fun x -> x) qui a un type plus large 'a->'a *)

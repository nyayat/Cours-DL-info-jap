Cas spéciaux en OCaml : options et exceptions
==============================================

[Lien Sketch](https://sketch.sh/s/LNYzzbJLVpKgRIEYW5l2pM)

**Avertissement:** les exemples déclenchant des exceptions ont le mauvais goût de bloquer la suite de la séance Sketch. Ils sont donc en commentaires ci-dessous, mais n'hésitez pas à les essayer en les décommentant temporairement.

Reprenons un exercice comme le calcul du minimum d'une liste. Que répondre dans le cas d'une liste vide ?
C'est assez régulier en programmation d'avoir ainsi des cas spéciaux à gérer.

Pour une liste d'entiers, on peut ici faire des "bricolages", comme utiliser `min_int` (le plus petit entier possible) ou même `-1` (si l'on sait qu'on ne rencontrera que des entiers positifs). Mais ce genre de bidouille est à proscrire autant que possible : comment savoir ensuite si la réponse était bien dans la liste d'origine, ou si c'est une réponse par défaut ? Plus mathématiquement on invalide la propriété pourtant souhaitable `∀l, minlist l ∈ l`.

De plus, il n'y a pas que les entiers dans la vie, on aura parfois des types sans plus petit élément. Pire, si on souhaite faire une fonction `minlist` polymorphe, on ne connaît alors *aucune* valeur particulière du type `'a` au moment de la définition.

Une solution est alors d'utiliser la fonction `failwith`, qui déclenche une exception avec un message d'erreur:
```ocaml
let rec minlist = function
| [] -> failwith "no minimum in an empty list"
| [x] -> x
| x::l -> min x (minlist l)

let _ = minlist [1;2;3]
(* let _ = minlist [] *)
```
En OCaml, cette notion d'exception est bien plus générale que cette seule fonction `failwith`, nous allons détailler cela plus bas. Mais auparavant, nous allons voir que l'on peut aussi utiliser des types algébriques pour gérer ces situations, et en particulier le type `option`.

## Le type option

Le type `option` fourni par la bibliothèque standard OCaml est un type non-récursif, polymorphe, à deux constructeurs:
```ocaml
type 'a option =
 | Some of 'a
 | None
```
Utiliser `Some x` permet de fournir une réponse `x` quand elle existe bien, et l'autre constructeur `None` (sans argument) est utilisable quand il n'y a pas de réponse adéquate. Ce type `option` est donc similaire à une liste qui serait toujours de taille 0 ou 1. D'ailleurs, en Haskell, le nom du
type similaire est très parlant, il s'agit du type `Maybe` : peut-être qu'il y a (une réponse véritable), peut-être pas.

Voici l'exemple `minlist` codé à l'aide du type `option` :
```ocaml
let rec minlist = function
| [] -> None
| x::l ->
    match minlist l with
    | None -> Some x
    | Some r -> Some (min x r)
    
let _ = minlist [1;2;3]
let _ = minlist []
```
Le type de `minlist` n'est plus l'impossible `'a list -> 'a` mais maintenant `'a list -> 'a option`. Autre intérêt, on n'a pas eu à étendre le langage avec des nouveautés comme les exceptions : un type algébrique polymorphe suffit, avec le filtrage de motifs (pattern matching) associé. Par contre, la gestion de ces `option` peut vite devenir pesante. Imaginons une division qui gérerait via `option` le cas de la division par zéro.

```ocaml
let safe_div x y = if y=0 then None else Some (x/y)

(* let _ = 5 + safe_div 8 2 (* erreur : types incompatibles *) *)

let safe_add x y = match x, y with
 | Some x', Some y' -> Some (x'+y')
 | _, _ -> None

let _ = safe_add (Some 5) (safe_div 8 2)
```
Bref, on risque de se retrouver rapidement avec des `option` partout, ce qui est lourd à écrire, et moins efficace (allocations des blocs `Some`). Un style particulier dit *monadique* et quelques facilités syntaxiques peuvent éventuellement aider, mais cela reste complexe, et donc déconseillé sauf sur de courtes portions de code.

Remarque : OCaml propose maintenant un type nommé `result`, qui une variante du type `option` permettant de documenter pourquoi il n'y a pas eu de réponse normale. Ce type est encore peu utilisé. 
```ocaml
type ('a, 'b) result = 
| Ok of 'a
| Error of 'b
```
## Les exceptions en OCaml

  - Arrêt violent du programme si l'exception n'est pas "rattrapée".
  - Similaire à Java et ses `throw` et `try ... catch ...`
  - Syntaxes OCaml : `raise` et `try ... with ...`
  - Le type d'une fonction OCaml n'indique pas les exceptions qu'elle est susceptible de lancer (pas de déclaration `throws ...` comme en Java). Donc usage beaucoup plus léger qu'en Java, pratique au début, mais souvent très délicat à gérer dans les gros programmes.
  - Beaucoup mieux compilé qu'en Java (mais attention à l'efficacité si usage de `js_of_ocaml`)
  - Du coup, sert souvent d'idiome généraliste de programmation en OCaml, pas seulement en cas d'erreurs fatales.

### Quelques exceptions OCaml prédéfinies
```ocaml
(* 1/0;; *)
(* failwith "oups";; *)
(* List.hd [];; *)
(* List.tl [];; *)
(* String.get "hello" 17;; *) (* autre syntaxe : "hello".[17] *)
```
Autre exemple typique, la recherche dans une table d'association : `List.assoc : 'a -> ('a * 'b) list -> 'b`.
```ocaml
List.assoc "b" [("a", 5); ("b", 11)];;
(* List.assoc "z" [("a", 5); ("b", 11)];; *)
```
### Le type exn

Les exception OCaml peuvent se manipuler comme les autres valeurs, elles ont en particulier un type, nommé `exn`:
```ocaml
Not_found;;
Division_by_zero;;
(* Failure;; *)(* erreur: ce constructeur attend un argument *) 
Failure "toto";;
(* Quelques autres exceptions "système" *)
Stack_overflow;;
Out_of_memory;;
Sys.Break;;
```
Les exceptions sont des constructeurs d'un type `exn`. Ces constructeurs peuvent avoir un argument. Ce type `exn` est donc comme un type somme, avec une seule différence importante : `exn` est *extensible* : on peut définir de nouvelles exceptions (les vrais types sommes qui ne sont pas extensibles).

### Déclarer de nouvelles exceptions

  On utilise le mot-clé `exception` pour ajouter ses propres exceptions. Les noms des exceptions doivent commencer sur une majuscule (comme les constructeurs). Les exceptions peuvent avoir un argument (ou pas), mais  *pas* de polymorphes par contre.
```ocaml
exception Echec;;
exception Erreur_fichier of string;;
exception E1 of int list;;
(*exception E2 of 'a list;;*)  (* erreur *)
```

### Lever une exception

  Une exception se lève (on dit aussi se déclenche, ou se lance) via la fonction `raise : exn -> 'a`. Attention : le fait qu'une exception est levée ne se voit pas dans le type, `raise` peut être utilisé à tout endroit.
```ocaml
exception E of int;;

let f x = if x < 0 then raise (E x) else x+42;;

exception E of float;; (* cache la première définition de E, mais f utilise bien la première *)

(* f (-17);; *)
```
Voici quelques définitions de fonctions de la bibliothèque standard:
```ocaml
let failwith msg = raise (Failure msg)

(** Pour List.assoc : *)
let rec assoc x = function
| [] -> raise Not_found
| (a,b) :: l -> if a = x then b else assoc x l
```
### Rattraper des exceptions

C'est une nouvelle syntaxe : `try ... with ...`. Après le `try`, une expression quelconque, pouvant déclencher (ou non) une exception. Après le `with`, un filtrage par motifs (comme pour un `match`), avec :

   - un ou plusieurs motifs (patterns) du type `exn`.
   - pour chaque motif, une expression correspondante après `->`, devant être du même type que le milieu du `try`.

Par exemple :
```ocaml
let f x y =
  try x/y with Division_by_zero -> -1

let _ = f 17 0
```
### Exécution de `try e with ...`

Si l'évaluation de `e` donne une valeur `v` : la valeur du tout est `v`. Si par contre l'évaluation de `e` lève une exception `E` :

  - On essaie alors de filtrer `E` avec les motifs qui suivent le `with`.
  - Au premier motif qui s'applique, l'expression correspondante après `->` est évaluée (on dit que `E` est rattrapée).
  - Si aucun des motifs ne s'applique à `E` : l'exception n'est pas rattrapée.

Un `try` peut donc tout à fait s'évaluer en la levée d'une exception :

  - soit car l'exception n'était pas concernée par le filtrage dans ce `try`
  - soit car le rattrapage a eu lieu mais l'expression déclenchée après le `->` peut aussi avoir levé une nouvelle exception.

Une exception levée se propage à travers les différents `try` en cours jusqu'à en trouver un qui la rattrape. S'il n'y en a pas, il s'agit alors d'une exception *non-rattrapée à toplevel*, qui terminera violemment un programme OCaml autonome (plantage), ou qui rendra la main à l'utilisateur si on est dans une boucle interactive OCaml (toplevel).

```ocaml
let dico = [("a", "un"); ("wonderful", "formidable"); ("is", "est")];;

let translate_mot m =
  try List.assoc m dico
  with Not_found -> m;;

let rec translate_phrase  = List.map translate_mot;;

translate_phrase ["Caml";"is";"wonderful"];;
```
Au fait, les exceptions OCaml ne sont pas forcément des cas d'erreur, on peut réellement programmer avec.
```ocaml
exception E of int

let f x =
  if x=0 then raise (E 0)
  else if x<10 then raise (E 1)
  else if x < 20 then raise (E 2)
  else x

let g x =
  try string_of_int (f x) with
    | E 0 -> "zero"
    | E 1 -> "petit"

let _ = g 0
let _ = g 5
(*let _ = g 17*) (* exception pas rattrape *)
let _ = g 42
```
### Exemple : un arbre binaire est-il parfait ?

Reprenons l'exercice sur les arbres binaires parfaits.

```ocaml
(* Pour changer, même pas d'éléments ici, on ne garde que le squelette *)
type arbre = Feuille | Noeud of arbre * arbre;;

(* Solution avec une fonction hauteur séparée, multiple passage dans l'arbre *)

let rec hauteur = function
| Feuille -> 0 
| Noeud(g,d) -> 1 + max (hauteur g) (hauteur d);;

let rec parfait = function 
| Feuille -> true
| Noeud(g,d) -> parfait g && parfait d && hauteur g = hauteur d

(* Solution avec exceptions et un seul parcours de l'arbre *)

exception Imparfait

let rec hauteur_exn = function
| Feuille -> 0
| Noeud(g,d) ->
  let hg = hauteur_exn g
  and hd = hauteur_exn d in
  if hg = hd then 1 + hg else raise Imparfait

let parfait t =
 try
  let _ = hauteur_exn t in true
 with Imparfait -> false
 
(* Pour comparer, solution avec le type option (aussi en un seul parcours) *)

let rec hauteur_option = function
| Feuille -> Some 0
| Noeud(g,d) ->
  match hauteur_option g, hauteur_option d with
  | Some hg, Some hd when hg = hd -> Some (1+hg)
  | _ -> None

let parfait t =
  match hauteur_option t with
  | Some _ -> true
  | None -> false
```
Ici la solution à base d'exception est un peu plus efficace, pas d'allocation de structures intermédiaires comme avec les `option`. Par contre le type de `hauteur_exn` n'indique pas qu'une exception peut se produire : n'oubliez de gérer ce cas ensuite !

Au fait, dans `hauteur_option`, il vaudrait mieux faire les appels récursifs l'un après l'autre, pas dans le même `match` double mais plutôt dans deux `match` imbriqués, pour s'arrêter au plus tôt en cas d'arbre non-parfait. Mais le code devient plus long à écrire...

### Alors, option ou exception, que choisir ?

Jusqu'à assez récemment, l'usage d'exceptions était fortement conseillé, et utilisé partout dans la bibliothèque standard OCaml (cf `List.assoc` ou d'autres fonctions de recherche comme `Hashtbl.find`). Il est vrai que manipuler des valeurs dans le type `option` peut s'avérer lourd et moins efficace (en particulier dans les fonctions récursives). Par contre le type indique alors clairement qu'une réponse défavorable est possible, pas de risque d'oublier de gérer ce cas. En revanche, les bugs provenant de programmes OCaml n'ayant pas rattrapé un `Not_found` ou autre sont des grands classiques (voir par exemple <https://github.com/coq/coq/issues?q=is%3Aissue+Not_found>). Et dans ce cas, le typage OCaml n'aide pas. La tendance actuelle est donc au renouveau du type `option`, au moins dans les interfaces des bibliothèques : `List.assoc_opt` existe depuis OCaml 4.05, idem pour `Hashtbl.find_opt` et plusieurs autres. Le même conseil vaut pour vos programmes : une fonction privée à un module peut tout à fait être programmée via des exceptions, il sera facile de voir si ses usages locaux sont bien corrects. Par contre pour une fonction visible dans une interface de module, et donc utilisable au loin, favorisez si possible des types expressifs (et en particulier l'usage du type `option`).

### Comment localiser une exception qui "fuite" dans un programme ?

Si une exécution particulière d'un programme OCaml échoue avec une "uncaught exception" à toplevel, il est facile maintenant de localiser la source du souci en demandant l'affichage d'une *backtrace* de cette exception, via le réglage de la variable shell suivante: `export OCAMLRUNPARAM=b`. Il peut être utile d'utiliser le compilateur "bytecode" avec l'option `-g` si votre environnement de compilation ne le fait pas déjà. Voir également le module [Printexc](http://caml.inria.fr/pub/docs/manual-ocaml/libref/Printexc.html).

### Une syntaxe commode : exception dans un motif de match

On peut maintenant écrire quelque chose comme :

```ocaml
match e with
| ... -> ...
| ... -> ...
| exception E -> ...
```

C'est un raccourci fort pratique pour la combinaison d'un `try` et d'un `match` :

```ocaml
 try
  (match e with
  | ... -> ...
  | ... -> ...)
 with
 | E -> ...
```

En fait c'est même plus complexe que cela car le véritable `try` se fait plutôt autour de `e`.

Plus de détails: 

  - <https://caml.inria.fr/pub/docs/manual-ocaml/patterns.html#sss:exception-match>
  - <https://blog.janestreet.com/pattern-matching-and-exception-handling-unite>


### Assertions

Le mot-clé `assert`, suivi d'une expression booléenne, permet de déclencher ce calcul booléen lors de l'exécution. S'il donne `true`, rien à signaler. Si par contre il donne `false`, l'exception `Assert_failure` est levé, avec nom du fichier, numéro de ligne et colonne. C'est très utile pour expliciter des invariants et les tester à l'exécution. Si on est à la milliseconde près, on peut désactiver les assertions pendant la compilation (`-noassert`).

Cette construction `assert ....` est de type `unit` (le type ayant une unique valeur nommée `()`). Une seule
exception à cette règle : `assert false` indique un cas impossible, il est connu par le compilateur comme étant de type quelconque `'a`.
```ocaml
let f x =
  assert (x>=0.);
  sqrt x
;;

f 2.;;
(* f (-5.);; *)

let rec f n =
  assert (n>0);
  if n=1 then 1 else n * f(n-1);;

f 3;;
(* f (-3);; *)
```
Au fait `expr1; expr2` signifie `let _ = expr1 in expr2`. On en reverra beaucoup dans le code OCaml impératif.

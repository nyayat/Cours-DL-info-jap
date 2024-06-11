Les types de données du premier ordre
=====================================

[Lien Sketch](https://sketch.sh/s/RjxDVUFPNMiZqKxDtzdezN)

Auteur initial : Yann Régis-Gianas.
Modifications par : Pierre Letouzey.

Cette partie du cours vise à vous montrer comment OCaml représente les valeurs des types de données que vous connaissez déjà. Attention, ce document s'appuie sur OCaml 4.08.0. Quelques (rares) fonctionnalités ne sont pas présentes dans les versions plus anciennes d'OCaml.

## Les booléens, le type `bool`

Les booléens et leurs opérations se comportent comme on en a l'habitude.
```ocaml
true;;
false;;
true || false;;
true && false;;
not true;;

```

La construction if-then-else est bien sûr présente en OCaml. Attention cependant : on ne peut en général pas éluder la branche "else" car il faut toujours qu'une expression renvoie une valeur. (Nous verrons un cas très particulier où le "else" peut être omis.) Il faut donc voir la construction if-then-else d'OCaml comme celle des expressions ternaires de C ou Java ``cond ? exp : exp``.
```ocaml
if true then false else true;;
if false then if true then true else false else false;;
```

Quelques équivalences logiques bien pratiques pour améliorer son code (si `b` et `b2` sont des expressions booléennes) :

  - `b = true` est la même chose que `b`
  - `b = false` est la même chose que `not b`
  - `if b then true else false` est la même chose que `b`
  - `if b then false else true` est la même chose que `not b`
  - `if b then true else b2` est la même chose que `b || b2`
  - `if b then b2 else false` est la même chose que `b && b2`

## Les entiers, le type `int`

Contrairement à ses booléens tout à fait standards, les entiers d'OCaml sont par contre un peu singuliers. En effet, les entiers machines sont généralement de la même taille que le [mot machine](https://fr.wikipedia.org/wiki/Mot_%28architecture_informatique%29) de l'architecture sur laquelle le programme est exécutée. Ce n'est pas le cas en OCaml : pour implémenter un [ramasse-miettes](https://fr.wikipedia.org/wiki/Ramasse-miettes_%28informatique%29) efficace, un bit est reservé sur tous les entiers machines de façon à distinguer très rapidement une valeur entière d'une adresse mémoire. Nous reviendrons sur ces aspects de l'implémentation d'OCaml un peu plus tard dans le cours.

Pour le reste, le langage fournit des opérations arithmétiques classiques : certaines sont dédiées aux calculs arithmétiques, d'autres à la manipulation de la représentation binaire.
```ocaml
1 + 2 * 3 / 4 mod 5;;
1 lsl 32;;
1 land 4;;
0b001010101101011;;
0xcafebabe;;
```

Comme en Java ou en C, OCaml fournit une sorte de "switch" qui étend le if-then-else en autorisant des branchements dont le nombre de cas est supérieur à 2. On l'appelle l'**analyse par motifs** (ou **pattern-matching** en anglais).
```ocaml
let f x = match x with
| 0 -> true
| 1 -> false
| 2 -> true
| _ -> false;;

f 3;;

let g x = match x with
| 0 -> -1
| y -> y;;
```

La règle d'évaluation de l'analyse par motifs est très simple : on commence par comparer le motif de la première branche avec la valeur analysée, si le motif *capture* la valeur alors on évalue le **corps de la branche** (l'expression qui suit la flèche), sinon on passe à la branche suivante.

Remarquez ce qui se passe si on oublie la dernière branche de cette analyse : le typeur d'OCaml nous fait une remontrance car notre calcul pourrait échouer sur certains cas. C'est vrai, si on évalue une telle analyse par motifs *non exhaustives* sur une valeur non capturée par les motifs de l'analyse, on s'expose à la levée d'une exception!
```ocaml
let f x = match x with
| 0 -> true
| 1 -> false
| 2 -> true;;

(* f 3;; *)
```

## Les flottants, le type `float`

Les nombres à virgules flottantes d'OCaml sont comme ses booléens : ils respectent la norme IEEE 754 comme ceux de C ou de Java. Il faut donc toujours les utiliser avec la plus grande [précaution](https://floating-point-gui.de/) car les accumulations d'erreurs d'arrondi à des résultats significativement éloignés de ceux attendus.

Une particularité d'OCaml est l'absence de surcharge des opérateurs arithmétiques. Il faut rajouter un "." à la fin de toutes les opérations, ce qui peut s'avérer assez lourd.
```ocaml
let x = 3. /. 2.;;
```

Dans d'autres langages comme Python, écrire `3` ou `3.` suffit à changer radicalement le résultat d'un programme, parfois sans aucun autre avertissement à l'utilisateur. La contrainte syntaxique d'OCaml, même si elle est lourde, est une façon d'éviter certains bugs liés à la conversion implicite de données.

Si on doit faire de nombreux calculs sur les flottants, on peut toujours redéfinir localement les opérateurs binaires pour alléger la syntaxe.
```ocaml
let ( * ) = ( *. ) in let ( / ) = ( /. ) in 1. * 2. / 3.;;

```

Si l'on souhaite mélanger des entiers et des flottants dans un calcul, on doit convertir explicitement les valeurs d'un type vers l'autre. Croyez-le ou non mais cette discipline élimine de nombreux bugs causés par les conversions implicites présents dans d'autres langages.
```ocaml
float_of_int 3;;
int_of_float 4.2;;
```

Il y a de nombreuses fonctions sur les flottants que vous trouverez décrites dans la documentation du module [Float](http://caml.inria.fr/pub/docs/manual-ocaml/libref/Float.html).

L'analyse par motifs s'applique aussi aux flottants (mais comme toujours attention aux codes espérant un flottant précis et échouant pour cause d'arrondi).
```ocaml
let f x =
  match x with
  | 0.3 -> 100
  | 0.5 -> 1000
  | 0.7 -> 10000
  | _ -> max_int;;
  
  
```

## Les caractères, le type `char`

Les caractères sont codés sur 8 bits. Les 128 premières valeurs correspondent à l'ensemble des caractères [ASCII](https://fr.wikipedia.org/wiki/American_Standard_Code_for_Information_Interchange). Pour le moment, les  caractères entre 128 et 255 codent des symboles de l'alphabet ISO 8859-1.
```ocaml
'a';;
'b';;
'\x64';;
```

Les caractères unicode doivent être écrits sous la forme d'échappements dans des chaînes de caractères. En effet, les codes sources OCaml doivent être écrits en ASCII donc on ne peut pas écrire de caractères unicode dans le code source. Le module [Uchar](https://caml.inria.fr/pub/docs/manual-ocaml/libref/Uchar.html) fournit néanmoins des fonctions de manipulation de caractères unicode.

L'analyse par motifs s'applique aussi aux caractères.
```ocaml
let f x = match x with 'o' -> 'a' | 'a' -> 'o' | x -> x;;
```

## Les chaînes de caractères

Les chaînes de caractères sont des séquences de caractères. Leur taille est limitée à 2^24 - 5 sur les architectures 32 bits et à 2^57 - 9 sur les architectures 64 bits. On peut accéder en temps constant aux i-èmes caractères d'une chaîne à l'aide de la notation `s.[i]` où `s` est une expression s'évaluant en une chaîne de caractères et `i` une expression qui s'évalue en un entier.
```ocaml
let s = "foo";;
s.[1];;
let s1 = s ^ "bar";;
```

Il y a de nombreuses fonctions sur les chaînes de caractères que vous trouverez décrites dans la documentation du module [String](http://caml.inria.fr/pub/docs/manual-ocaml/libref/String.html). L'analyse par motifs s'applique aussi aux chaînes de caractères.

```ocaml
let f x = match x with "foo" -> "bar" | "bar" -> "foo" | x -> x ^ x;;
let z = f "foo" ^ f "bar" ^ f "ooo";;

```

Depuis quelques versions d'OCaml, une chaîne de caractères ne peut plus être modifiée en place. Le type `string` est donc maintenant une donnée *immutable* comme beaucoup d'autres en OCaml. En cas de besoin, un  nouveau type nommé `bytes` se comporte comme les anciennes chaînes de caractères mutables d'OCaml.

## Les n-uplets

Comme en Python, on peut représenter en OCaml des valeurs qui sont composées de plusieurs autres valeurs, de types potentiellement différents. Les n-uplets sont particulièrement utiles lorsqu'une fonction doit renvoyer plusieurs résultats (à comparer avec la lourdeur d'une telle tâche en C et Java).
```ocaml
(1, "foo");;
(true, 42 + 3, "bar");;
(0, 1, 2, 3, true);;
```

Que peut-on faire de ces valeurs? Bien entendu la bibliothèque standard nous offre des fonctions pour les manipuler.
```ocaml
fst (0, 2);;
snd (true, false);;
```

Le langage offre en outre un mécanisme très puissant pour nommer les composants internes des valeurs composées : l'analyse par motifs que nous avons déjà rencontrées! Grâce au typage, une analyse par motifs d'un n-uplet _ne peut jamais échouer_ (à comparer avec l'absence de telles garanties dans les langages comme Python et Javascript).
```ocaml
let proj1_triple t =
   match t with (x, _, _) -> x;;
   
let proj3_triple t = match t with (_, _, z) -> z;;

(* proj3_triple (0, 1);; est rejeté pour de bonnes raisons! *)

let foo x =
  match x with (((_, x), (_, _, (y, z)))) -> x + y + z;;
```

On peut même faire cette analyse de motifs dès l'argument d'une fonction:
```ocaml
let sum_triple (x,y,z) = x+y+z;;
(* ce qui est une manière plus courte d'écrire : *)
let sum_triple t = match t with (x,y,z) -> x+y+z;;
```

## Les types définis par l'utilisateur

Comme le montre le dernier exemple, quand les valeurs sont composées, leurs types montrent la structure de cette composition. C'est souvent utile mais c'est aussi une source d'illisibilité. Pour cette raison, l'utilisateur peut définir des abbréviations de types.
```ocaml
type user = string * string * int * string;;

let login (u : user) (password : string) : bool =
   match u with (_, password', _, _) -> password = password';;
   
```

On peut aller un peu plus loin en introduisant un **constructeur de données** dans une déclaration de type. Il s'agit d'une étiquette que l'on adjoint aux valeurs de ce type pour leur donner un sens particulier. C'est utile lorsque deux valeurs ont la même structure mais représente des concepts différents.
```ocaml
type euro = Euro of int
type dollar = Dollar of int

(* let account_me : euro = max_int;; *)
let account_me : euro = Euro min_int;;
let account_bill : dollar = Dollar max_int;;

(* let robin_hood : euro = account_bill;; *) (* erreur de typage *)

let euro_from_dollar (d : dollar) : euro =
  match d with
  | Dollar quantity -> Euro (quantity * 2);;
let robin_hood : euro = euro_from_dollar account_bill;;
```

Comment extraire les valeurs qui se trouvent "derrière" une étiquette? Encore une fois, c'est l'analyse par motifs qui nous le permet!

Notez également que ces derniers exemples contiennent quelques *annotations* de typage (syntaxe `:`). Ces annotations sont facultatives, et servent juste à s'assurer qu'OCaml est bien d'accord avec le type que l'on pense pour un argument ou un résultat. 

## Les enregistrements

Tout comme en C, on peut créer des valeurs composées d'autres valeurs en associant un nom à chacune des composantes, que l'on appelle alors un **champ** ou un **attribut**. Ce type de valeur composée est appelé un **enregistrement**.

Contrairement aux n-uplets que l'on peut utiliser sans introduction préalable d'une définition de type, le typeur d'OCaml a besoin que vous lui explicitiez quels sont les attributs associés à un type d'enregistrement donné.
```ocaml
type user = {
  firstname : string;
  surname : string;
  power : int;
};;
let luke = { firstname = "luke"; surname = "skywalker"; power = 33 };;
let darth = { surname = "vador"; firstname = "darth"; power = 99 };;
```

Un enregistrement peut être vu comme un n-uplet dont on aurait nommé les composantes pour y faire référence plutôt que d'utiliser leur position dans le n-uplet. Bien entendu, définir une valeur d'enregistrement est syntaxiquement plus lourd que de définir un n-uplet. Par contre, les enregistrements ont l'avantage d'autoriser les composantes à être fournies dans un ordre arbitraire.

Ils permettent aussi d'utiliser un *sucre syntaxique* très pratique pour construire un enregistrement à partir d'un autre en ne précisant que la valeur des attributs que les différencient.
```ocaml
let darth_my_father = { darth with surname = "skywalker"};;

```

Enfin, et surtout, les enregistrements fournissent automatiquement des **projecteurs** d'attributs qui s'appuient sur le nom d'un attribut pour l'extraire d'un enregistrement. Notez, encore une fois, que ces sélections d'attribut ne peuvent jamais échouer grâce au typage (comparez avec la situation en Python et en Javascript).
```ocaml
let my_favorite = darth.firstname;;
```

Pour finir, les enregistrements peuvent aussi être analysés par motifs.
```ocaml
let f r =
  match r with
  | { surname = x; firstname = y; power = 33 } -> x ^ " " ^ y ^ " is weak."
  | { surname = x; firstname = y; power = _ } -> x ^ " " ^ y ^ " is powerful.";;
  f luke;;
  f darth;;
```

Quelques sucres syntaxiques permettent d'écrire des motifs plus concis.
```ocaml
let f r =
  match r with
  | { surname; firstname; power } -> surname ^ " " ^ firstname ^ " : " ^ string_of_int power;;
  (* equivaut à :
  | { surname = surname; firstname = firstname; power = power } -> ...
  *)
  
let g r =
  match r with
  | { surname; _ } -> surname
  (* equivaut à :
  | { surname = surname; firstname = _; power = _ } -> ...
  *);;
```

## Des énumérations aux types algébriques

### Les énumérations

OCaml permet de définir des types pour des ensembles finis. Dans le langage C et Java, on les appelle des types d'énumération.
```ocaml
type color = Red | Black | White
```

Une nouvelle fois, l'analyse par motifs s'avère particulièrement agréable à utiliser pour représenter des calculs sur des valeurs énumérées.
```ocaml
let change_color x =
  match x with Red -> Black | Black -> Red | White -> White;;
```

### Les types sommes

Remarquez que l'on retrouve les constructeurs de données croisés un peu plus tôt : une valeur d'un type d'énumération n'est rien d'autre qu'une étiquette que l'on associe à cette valeur. Mais alors, on peut faire comme plus tôt et placer des valeurs "derrière" chaque étiquette!
```ocaml
type number = Float of float | Int of int
```

En faisant cela, le type que nous venons de définir n'est plus un ensemble fini de valeurs : il faut le voir maintenant comme une *union disjointe* de plusieurs types. On nomme parfois cette union disjointe comme une somme et c'est pourquoi les programmeurs OCaml nomment souvent ces types des **types sommes**.

Ce mécanisme nous permet donc de réunir des types incompatibles dans un même type. C'est très utile pour étendre le domaine d'une fonction par exemple.

Comment manipuler les valeurs d'un type somme? Avec de l'analyse par motifs, bien entendu!
```ocaml
let add n m =
  match (n, m) with
  | Int n, Int m -> Int (n + m)
  | Float n, Int m -> Float (n +. float_of_int m)
  | Float n, Float m -> Float (n +. m)
  | Int n, Float m -> Float (float_of_int n +. m);;
```

Deux types sommes sont couramment utilisés dans les programmes OCaml : le type option et le type result.
```ocaml
type 'a option = (* Le type des valeurs potentiellement absente. *)
| None (* La valeur est absent. *)
| Some of 'a (* "Some v" représente la valeur présente "v". *)

let predecessor n = (* The predecessor of natural number. *)
 if n = 0 then None else Some (n - 1);; 

type ('a, 'b) result (* Le type des résultats des fonctions partielles, i.e. qui peuvent échouer. *) =
| Ok of 'a    (* "Ok v" : le calcul s'est évalué normalement en "v". *)
| Error of 'b (* "Error e" : le calcul a échoué avec l'erreur "e". *)

let total_division a b =
  if b = 0 then Error "Division by zero" else Ok (a / b);;
```

Ces types `option` et `result` sont prédéfinis en OCaml, pas besoin en pratique de taper les définitions de types précédentes dans vos exemples, vous pouvez directement programmer avec.

On remarque que ces définitions sont paramétrées par un type `'a` pour `option`, ou même deux pour `result`. C'est une forme de généralité très utile pour éviter de dupliquer du code. En effet, le type des valeurs entières potentiellement absentes est structuré de la même façon que le type des valeurs flottantes potentiellement absentes : soit la valeur est là, soit elle ne l'est pas. Certaines fonctions sur les valeurs potentiellement absentes ne dépendent pas du type de ces valeurs : on veut les écrire une fois pour toute et les réutiliser pour tous les types possibles de valeurs potentiellement absentes.
```ocaml
let option_of_result r =
  match r with Ok x -> Some x | Error _ -> None;;
```

Remarquez que le type de la fonction est lui aussi paramétrique : on dit que la fonction est **polymorphe**. Nous reviendrons sur le polymorphisme dans un prochain cours. (Spoiler : cette forme de polymorphisme paramétrique n'est pas de même nature que le polymorphisme induit par l'héritage en Java.)

### Les types algébriques

Comme vous le savez, certaines valeurs sont naturellement récursives. Deux exemples viennent rapidement en tête : les listes et les arbres. On peut les définir naturellement en introduisant un type somme récursif, aussi appelé **type algébrique**.

```ocaml
type my_list_int =
| Empty
| Element of int * my_list_int

let my_list = Element (1, Element (2, Element (3, Empty)));;

let rec length l =
  match l with
  | Empty -> 0
  | Element (_, l) -> 1 + length l
  
type int_binary_tree =
| EmptyTree
| Node of int_binary_tree * int * int_binary_tree

let leaf x = Node (EmptyTree, x, EmptyTree)

(*
   L'arbre suivant :

              5
             / \
            3   4
           / \ / \
          1
         / \

   est représenté par :
*)

let beautiful_tree = Node (Node (leaf 1, 3, EmptyTree), 5, leaf 4)

let rec size t = match t with
| EmptyTree -> 0
| Node (g,x,d) -> 1 + size g + size d


type 'a  autre_arbre =
 | Feuille of 'a
 | Noeud of 'a autre_arbre * 'a autre_arbre

let autre_exemple = Noeud (Feuille 1, Noeud (Feuille 2, Feuille 3))
```

Il n'y a rien à dire de plus : l'analyse par motifs permet de les traiter exactement comme des types sommes non récursifs. Ces analyses de motifs apparaîtront souvent dans des fonctions récursives. D'ailleurs, un schéma de programmation très commun en programmation fonctionne consiste à définir une fonction *récursive* ainsi :

```ocaml
let rec f x y z = 
   match z with
   | pattern_1 -> exp_1
   | pattern_2 -> exp_2
   | ...
```

C'est pour cette raison qu'OCaml fournit un sucre syntaxique pour condenser ces définitions comme suit :

```ocaml
let rec f x y = function
  | pattern_1 -> exp_1
  | pattern_2 -> exp_2
  | ...
```

En résumé, `function ...` signifie `fun x -> match x with ....`. Attention, avec `function` on n'a même pas donné de nom à ce que reçoit cette fonction (mais les "pattern" peuvent le faire ensuite) ! 
```ocaml
let rec length = function
| Empty -> 0
| Element (_, l) -> 1 + length l

```

## Les listes

### Des notations pour manipuler les listes

Les listes sont omniprésentes en programmation inductive. Pour faciliter la définition de listes et de fonctions sur les listes OCaml proposent des listes primitives dans le langage. Elles se comportent exactement comme le type `my_list_int` vu un peu plus haut, mais avec des syntaxes plus compactes (`[]` au lieu de `Empty` et `::` au lieu de `Element`), et sont *polymorphes* (on n'est pas limités aux entiers commes élements). Par contre ces listes sont toujours *homogènes* en OCaml : tous les éléments d'une même liste auront le même type.
```ocaml
[];;  (* liste vide *)
[1;2;3;4];; (* sucre syntaxique pour quatres ajouts sur la liste vide *)
1 :: [2;3;4];; (* une autre écriture possible pour la même liste [1;2;3;4] *)
1 :: 2 :: 3 :: 4 :: [];; (* encore une autre *)

let rec length l =
  match l with
  | [] -> 0
  | _ :: l -> 1 + length l;;

length [1;2;3;5];;

let l = [1;2;3];;
let l' = [4;5;6];;
let l'' = l @ l';; (* la concaténation de listes fournie par OCaml *)

let rec nth i l = (* une façon d'écrire l'accès au n-ieme d'une liste *)
  match l with
  | [] -> None
  | x :: _ when i = 0 -> Some x
  | _ :: xs -> nth (i - 1) xs

let n = nth 2 l

let m =
  match nth 2 l, nth 1 l with
  | Some x, Some y -> Some (x + y)
  | _, _ -> None
```

Il y a de nombreuses fonctions sur les listes que vous trouverez décrites dans la documentation du module [List](http://caml.inria.fr/pub/docs/manual-ocaml/libref/List.html).

Ces listes OCaml sont une structure très flexibles, qui rendent de précieux services au programmeur. Attention, accéder à un élément à gauche de la liste est immédiat, mais ce n'est pas le cas pour les éléments de droite : il faudra traverser toute la structure avant d'y arriver, ce qui entraîne un coût proportionnel à la longueur de la liste. Sur des listes de taille modeste, ce n'est souvent pas un problème, mais une liste est rarement une structure adaptée pour stocker des dizaines de milliers de choses ou plus (on verra d'autres solutions alors).

### Comparaison avec des listes impératives

Les listes impératives sont souvent présentées comme des *listes simplement chaînées* modifiables. Dans la plupart des situations de développement applicatif, les listes fonctionnelles passent mieux à l'échelle que ces listes "impératives".

1. Un problème de modularité.

L'usage des listes impératives est souvent délicat dans les logiciels (penser à la suppression d'un *item* dans une *itemList* d'un widget). La raison fondamentale, c'est qu'une même cellule peut être partagée par plusieurs composants du programme en même temps *sans que l'on s'en rende compte*. Du coup, mettre à jour une cellule peut avoir des conséquences non locales, difficiles à prévoir si l'on a pas une vue complète et parfaite de l'ensemble du code source. Vous comprenez bien qu'il est impossible pour un développeur de connaître tout le code source d'un logiciel réaliste formé de centaines de milliers, voire millions de lignes de code. L'approche impérative passe difficilement à l'échelle!

Pour approfondir cet argument, vous pouvez consulter :

- <https://softwareengineering.stackexchange.com/questions/133330/whats-wrong-with-mutability-and-can-it-be-desirable>
- <https://blog.inf.ed.ac.uk/sapm/2014/03/06/enemy-of-the-state-or-why-oop-is-not-suited-to-large-scale-software/>
- <https://doc.rust-lang.org/1.30.0/book/2018-edition/ch03-01-variables-and-mutability.html>

2. L'élégance des solutions inductives

Comme l'explique très bien le [site suivant](https://codeforwin.org/2015/09/c-program-to-reverse-singly-linked-list.html), il n'est pas si simple de "renverser une liste impérative", c'est-à-dire calculer la liste qui contient les éléments d'une autre liste dans l'ordre inverse. Voici le pseudo-code donné par ce site :

```
Algorithm to reverse a Singly Linked List
%%Input : head node of the linked list
Begin:
    If (head != NULL) then
        prevNode ← head
        head ← head.next
        curNode ← head
        prevNode.next ← NULL
        While (head != NULL) do
            head ← head.next
            curNode.next ← prevNode
            prevNode ← curNode
            curNode ← head
        End while
        head ← prevNode
    End if
End

```

Comprenez-vous en un coup d'oeil ce que fait ce programme? Êtes-vous convaincu qu'il fonctionne correctement?

Posez vous les mêmes questions sur la version OCaml suivante.
```ocaml
let rec rev l =
  match l with
  | [] -> []
  | x :: l -> rev l @ [x];;
```

Bien entendu, le pseudo code impératif précédent ne construit pas une nouvelle liste mais modifie la liste donnée en entrée. Cette propriété consomme moins d'espace mémoire... mais nous ramène au problème de modularité précédent : que se passe-t-il si cette liste était "tenue" par un autre composant du logiciel sans  que nous le sachions? Par ailleurs, avec une gestion rusée de la mémoire, les performances d'un programme fonctionnel pur moderne n'ont plus à rougir devant les performances de leurs cousins impératifs.

Par ailleurs la fonction `rev` précédente a une mauvaise complexité en temps (voyez-vous laquelle et pourquoi ? Quel est le coût d'une concaténation `@` ?) mais il sera possible d'améliorer ce code pour en faire une version en temps *linéaire*.

## Les tableaux

Nous finissons cette présentation des types de données du premier ordre par les tableaux. Ils sont tout-à-fait standard et s'apparente aux `Array` de Java dans le sens où ils sont eux-aussi paramétrés par le type de leurs éléments. La syntaxe des accès aux éléments d'un tableau est un peu exotique : on écrit `t.(i)` pour accéder au i-ème élément du tableau `i` en temps constant.
```ocaml
let t = [| 1; 2; 3; |];;
t.(2);;
Array.length t;;
```

Les tableaux d'OCaml sont modifiables... mais chut! ce sera pour un prochain cours sur la programmation impure en OCaml.

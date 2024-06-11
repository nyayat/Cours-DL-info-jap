Programmation modulaire
=======================

Adapté d'un ancien [Sketch](https://sketch.sh/s/bU2dh9pkztj2TJrrCN5z15) de Y. Régis-Gianas.


## Modules : signatures et implémentations

OCaml permet de "programmer à grande échelle", c'est-à-dire de définir, spécifier et composer des composants logiciels. C'est une forme de programmation différente de la "programmation à petite échelle" qui se concentre essentiellement sur l'implémentation d'algorithme et sur la composition de fonctions.

En OCaml, les composants logiciels que l'on intègre ensemble pour former des logiciels sont appelés des **modules**. Un module est un composant qui peut contenir des définitions de types, de valeurs et éventuellement d'autres modules. L'ensemble des définitions forment l'**implémentation du module**. Il existe deux façons de créer un module en OCaml. Soit on crée un fichier `foo.ml` qui contient alors les définitions du module `Foo`. Soit on définit ce module dans un autre module en utilisant la syntaxe suivante :


```ocaml
module Foo = struct
  let some_value = 42
  type some_type = { x : bool }
end
```

Si ce code est écrit dans le fichier `bar.ml` alors ce fragment de code définit le module `Bar.Foo`.

L'une des problématiques de la programmation à grande échelle est de construire un logiciel *robuste aux changements*. Pour parvenir à cette forme de robustesse, on cherche à construire le logiciel dont chaque composant :

- a une forte cohésion, ce qui signifie que ses composants s'appellent les uns les autres pour résoudre un problème bien défini (et idéalement concentré sur ce module) ;

- a un faible couplage, c'est-à-dire que ses composants ne dépendent pas ou peu d'autres composants définis à l'extérieur du module.

Un composant qui vérifie ces deux propriétés est dit **modulaire**. Pour tester si un composant A est modulaire, il faut imaginer qu'on le retire du logiciel et qu'on le remplace par un composant B équivalent : Est-ce que ce remplacement n'a nécessité aucun changement sur le reste du logiciel? Etait-il facile de définir le composant B et de se convaincre qu'il est équivalent à A? Si on peut répondre à ces deux questions à l'affirmative, le composant A est probablement modulaire!

Pour s'assurer que le couplage entre l'implémentation d'un module et le reste du logiciel est minimal, il est nécessaire de **cacher les détails d'implémentation du module** qui n'ont pas d'utilité pour le reste du logiciel.

Prenons l'exemple d'un module prenant en charge le format CSV :

```ocaml
module CSV = struct
   type row = string list
   
   type content = row list
   
   type t = {
      filename : string;
      content  : content;   
   }
   
   let cut_in_rows s = (* The code is omitted. *) []
   
   let parse filename = (* The code is omitted. *) { filename; content = [] }
   
   let get_cell c i j = (* The code is omitted. *) ""

end
```

Si on souhaite pouvoir faire évoluer cette implémentation dans le futur, il faut que ses clients n'en utilisent que la partie qui leur est utile.

Dans notre exemple, la fonction `cut_in_rows` sert à découper le contenu du fichier CSV au niveau des retours chariots pour obtenir les lignes de données. Cette fonction est utile pour implémenter `parse` mais elle n'est pas utile pour l'utilisateur du module `CSV`. Si jamais un client de ce module se met à utiliser cette fonction, les prochaines versions du module `CSV` devront continuer de la fournir même si `parse` ne l'utilise plus!

Dans notre exemple, le client sait que le contenu du fichier est représenté par une liste de listes. Il peut donc utiliser le retour de `parse` en exploitant cette connaisance : par exemple, pour itérer sur les lignes, il pourra utiliser `List.iter`. Encore une fois, dévoiler cette information au client contraint les versions suivantes de `CSV` : on ne pourra plus changer la représentation interne du contenu d'un fichier `CSV`! Il y a pourtant des situations où cela serait utile : pour que la fonction `get_cell` soit efficace, on pourrait avoir envie de définir `content` comme une matrice ; pour être capable de traiter des fichiers .csv qui ne tiennent pas en mémoire vive, on pourrait pouvoir que `content` soit chargé partiellement et seulement si nécessaire...

Une **signature** sert à restreindre ce que le client peut utiliser quand il utilise un module (on parle aussi **d'interface**). Quand ce module correspond à un fichier `.ml` tout entier, cette signature peut être mise dans un fichier `.mli` correspondant. Pour un module interne à un fichier (syntaxe `struct ... end`) , la signature sera dans une zone `sig...end` avant le module. Par exemple:

```ocaml
module CSV :
sig
   type t
   val parse : string -> t
   val get_cell : t -> int -> int -> string
end
= struct
   type row = string list
   
   type content = row list
   
   type t = {
      filename : string;
      content  : content;   
   }
   
   let cut_in_rows s = (* The code is omitted. *) []
   
   let parse filename = (* The code is omitted. *) { filename; content = [] }
   
   let get_cell c i j = (* The code is omitted. *) ""

end
```

La signature est introduit par le mot-clé `sig` : elle déclare ce qui est exporté par le module. Ici, on voit que la module introduit un type `t` (`CSV.t` donc) ainsi que deux fonctions `parse` et `get_cell`. Comme `cut_in_rows` n'est pas dans cette signature, elle n'est plus accessible pour le client : il n'y a aucune chance qu'un client en dépende désormais. Voici notre premier problème réglé!

Une remarque similaire s'applique aux définitions de type : seule celle de `t` est exportée donc `content` et `row` ne sont plus accessibles aux clients du module `CSV`. On a aussi caché la définition même du type `t` : un client ne peut plus exploiter l'égalité `t = { filename . string; content : row list list; }`. On dit que `t` est un **type abstrait**.

```ocaml
(* La définition suivante est mal typée :

let t : CSV.t = []

car depuis l'extérieur du module `CSV.t` est distinct de tous les autres types.
*)
```

## La syntaxe des signatures

Dans une signature (soit un fichier `.mli`, soit une zone `sig..end` comme dans l'exemple précédent), on doit utiliser une syntaxe particulière. On peut y trouver:

- Des déclarations de types, soit exactement identique à la déclaration de type correspondante côté implémentation (déclaration d'une abréviation, d'un type algébrique ou d'enregistrement), soit une déclaration tronquée pour obtenir un type abstrait (`type t` et c'est tout, avec éventuellement des paramètres devant `t`).

- Des déclarations d'exceptions : `exception Oups of string` par exemple, là encore cette déclaration doit être identique entre signature et implémentation

- Des déclarations de constantes ou fonctions : `val x : type_x` rend visible globalement le nom `x` avec le type `type_x`. Evidemment, la définition de `x` doit être présente dans l'implémentation (`let` ou `let rec`), et `type_x` doit être un des types possibles pour `x`.

- Des déclarations de modules internes (nous ne détaillerons pas ici).

Note : `ocamlc -i fichier.ml` peut être une manière rapide d'obtenir une première ébauche de signature, à retravailler ensuite vu que tout y est publique et sans commentaires explicatifs.

## Modules paramétrés

OCaml permet également de concevoir des modules paramétrés par d'autres modules, ce qu'on appelle des **foncteurs**. Cela permet d'obtenir de la réutilisation de code à plus grande échelle que le polymorphisme d'une fonction. Plus à ce sujet lors du cours de Master 1 "Programmation fonctionnelle avancée". Juste un exemple utilisant le module paramétré `Set.Make` fourni par OCaml (sa [documentation](https://caml.inria.fr/pub/docs/manual-ocaml/libref/Set.Make.html) pour les curieux) :

```ocaml
(* Module regroupant un type d'entier et une comparaison.
   Ce module va servir de paramètre à Set.Make ci-dessous.
   Avec un ocaml récent, utiliser directment le module prédéfini Int.
 *)
module Ints = struct
  type t = int
  let compare = Pervasives.compare
end

(* Module proposant des ensembles finis d'entiers.
   Ce module satisfait la signature Set.S *)
module IntSet = Set.Make(Ints)

(* Un petit exemple d'utilisation. En particulier le test d'appartenance
   IntSet.mem est efficace (complexité logarithmique en le cardinal de
   l'ensemble *)

let rec make_set start stop step =
  if start > stop then IntSet.empty
  else IntSet.add start (make_set (start+step) stop step)

let set2 = make_set 0 1000 2
let set3 = make_set 0 1000 3
let set5 = make_set 0 1000 5

(* les entiers <= 1000 qui sont pairs et multiples de 3, mais pas de 5 *)
let set235 = IntSet.diff (IntSet.union set2 set3) set5

let _ = IntSet.cardinal set235 (* il y a 534 éléments dans cet ensemble *)
let _ = IntSet.mem 60 set235 (* et 60 n'y est pas, évidemment *)
```

Un tour d'OCaml
===============

[Lien Sketch](https://sketch.sh/s/H3xyXu6P3YdaHMqOVYXq6b)

Auteur initial : Yann Régis-Gianas.
Modifications par : Pierre Letouzey.

Ce fichier est fait pour vous aider à mener vos premières expérimentations avec des programmes OCaml. 

En changeant un peu les exemples de code, vous pourrez expérimenter et vérifier que vous avez compris.

Si vous souhaitez sauvegarder vos changements, connectez-vous avec un compte github puis faites "fork".

## Des expressions typées! Au revoir les commandes!

En OCaml, les calculs sont définis par des expressions. On peut évaluer une expression dans la boucle interactive OCaml (appelée aussi *toplevel*) en terminant par deux point-virgules `;;`. On verra plus tard que ces `;;` ne sont pas toujours obligatoires, mais pour l'instant mettons-les.

Voici une expression qui calcule un entier. Vous pouvez l'évaluer en cliquant sur "Run" tout en haut (ou en tapant Ctrl+Enter).
```ocaml
(1 + 2 * 3 - 100) / 3;;
```

Le retour de l'évaluateur d'OCaml produit deux choses importantes : un type et une valeur. On ne s'en rend pas compte ici mais l'évaluateur a procédé en deux étapes : 1. il a déterminé le type de l'expression sans l'évaluer (on dit aussi qu'il a *inféré* ce type) 2. il a calculé effectivement le résultat de l'évaluation de l'expression.

On voit mieux ces deux étapes lorsque l'on fait une erreur de typage comme dans l'exemple suivant.

Enlever les marqueurs de commentaires `(*` et `*)` et évaluer pour voir l'erreur. 
```ocaml
(* (1 + true);; *)
```

Avant d'évaluer l'expression, le typeur a remarqué qu'évaluer cette expression n'a pas de sens puisque l'addition + n'est défini que sur les entiers. Le calcul n'est donc même pas commencé.

Remettez bien les commentaires `(*` et `*)` autour de l'exemple précédent avant de continuer (ou corrigez l'erreur de type !), sinon Sketch n'évaluera pas les exemples suivants.

Bien sûr, on retrouve en OCaml tous les types de données primitifs classiques : entiers, booléens, flottants, caractères, chaînes de caractères et leurs opérateurs standards associés.
```ocaml
true || false && (not true);;
0.5 +. 3.2 *. 2. /. 0.03 -. 0.1;; 
"foo" ^ "bar";;
'a';;
```

## Définition de valeurs

En OCaml, on peut donner un nom au résultat d'un calcul. La valeur correspondante est alors identifiée et on peut y faire référence dans les calculs suivants.
```ocaml
let x = 2 * 3 + 3
```

Remarquez le changement dans le retour de l'évaluateur , il y a maintenant un `val` au début au lieu d'un tiret. En effet, une troisième action a été effectuée par l'interpréteur : ce dernier a introduit un *nom* `x`. Ainsi, on peut se resservir du résultat et ne plus avoir à taper et à calculer de nouveau l'expression `2 * 3 + 3`. Ainsi :
```ocaml
x + x
```

Cette expression est équivalente à :
```ocaml
2 * 3 + 3 + 2 * 3 + 3
```

mais `x + x` est plus court, et fait moins de calcul! Au lieu de 2 multiplications et 3 additions, `x + x`  ne fait qu'une seule addition, et la définition de `x` avait nécessité auparavant une multiplication et une addition. Le gain est marginal ici, mais la *factorisation* du code et des calculs redondants est une technique essentielle (qui peut même devenir indispensable en cas de programmation récursive).

Remarquez que `x` n'est pas une variable au sens des langages de programmation impérative. En effet, en Java ou en C, une variable est un espace mémoire modifiable et nommé. Ici, on ne fait que nommer le résultat d'un calcul, et ce résultat ne peut être modifié ensuite. Selon les optimisations internes d'OCaml, on n'alloue peut-être même pas d'espace en mémoire pour lui. Bref, en toute rigueur `x` n'est pas une variable ... car elle ne peut pas varier. On devrait parler d'abbréviation `x`, même si le mot *variable* a la vie dure en pratique.

Attention, on peut toujours introduire une nouvelle définition pour le même nom `x` mais elle ne modifie pas la définition précédente. Cette dernière n'est plus "visible" et donc utilisable directement mais elle peut parfois intervenir indirectement, comme le montre l'exemple suivant. Avant d'appuyez sur "run", demandez vous quel est la valeur de `mystery`.
```ocaml
let x = 2
let y = x * x
let x = 0
let mystery = y
```

Pour terminer avec les définitions, il faut savoir que l'on peut introduire des noms qui n'ont qu'une portée locale grâce au mot-clé `in`.
```ocaml
let x = 0;;
let y =
  let x = 3 in
  x + x;;
x;;
```

## Les fonctions, sérieusement

En plus des types primitifs présentés dans la section précédente, OCaml a un type de donnée pour les fonctions. Les fonctions sont des valeurs de première classe, c'est-à-dire qu'on peut calculer et transmettre des fonctions de la même façon que l'on transmet des entiers ou des chaînes de caractères.
```ocaml
fun x -> 2 * x;; (* La fonction qui a un entier `x` associe son double. *)
let double = fun x -> 2 * x;; (* J'appelle `double` le résultat de ce calcul. *)
let double x = 2 * x;; (* Une autre écriture possible de cette même fonction `double` *)
```

On peut utiliser une fonction en *l'appliquant*. La syntaxe de l'application perturbe toujours un peu les programmeurs habitués à d'autres langages parce que l'on n'utilise pas de parenthèses comme dans la notation ``f(x)`` des mathématiques. Pour appliquer une fonction, on sépare la fonction de son argument par un simple espace.
```ocaml
double 21;;
```

Bien entendu, si l'argument est une expression plus complexe, des parenthèses sont nécessaires autour de cet argument. Ainsi, quel est le résultat du calcul suivant ? A qui ajoute-on 1 ?
```ocaml
double (double 10 + 1);;
```

Attention aussi aux constantes négatives: écrire `double (-10)` et non pas `double -10` sinon OCaml comprendra une soustraction entre `double` et `10`, ce qui sera mal typé.

Les fonctions sont des valeurs au même titre que les entiers : on peut les calculer, les transmettre, les renvoyer, ... Voici quelques exemples :
```ocaml
let risky =
  if Random.int 2 = 0 then
    fun x -> 2. /. x
  else
    fun x -> 2. *. x

(* La fonction `risky` est l'une ou l'autre des deux fonctions: cela dépend d'une valeur aléatoire! *)
let win_or_lose_it_depends =
  if risky 100. <> 200. then
    "GAME OVER"
 else
    "JACKPOT";;

let twice = fun f x -> f (f x);;

(* La fonction `twice` compose la fonction `f` avec elle-même. *)
let cool = twice (fun x -> 2 * x) 10;;

(* L'expression entre parenthèses s'évalue en une fonction, que l'on évalue à son tour en 10 *)
```

Ces calculs de fonctions peuvent peut-être vous sembler compliqués et abstrait. Vous pouvez peut-être aussi ne pas comprendre quels problèmes on cherche à résoudre ici. C'est normal! L'utilisation des fonctions comme des objets de première classe sera l'objet de nombreuses séquences de cours.

## Récursivité

La programmation fonctionnelle fait un usage intensif des fonctions récursives. Voici l'exemple très classique de la fonction factorielle écrite en OCaml :
```ocaml
let rec fact = fun n ->   (** ou bien `let rec fact n = ` comme vous préférez *)
  if n = 0 then
    1
  else
    n * fact (n - 1)
  
let factorial_of_6 = fact 6
```

La récursion mutuelle est très rapidement utile quand on programme fonctionnellement. Voici un couple de fonctions qui jouent au ping-pong :
```ocaml
let rec ping = fun n ->
  if n = 0 then
    "ping!"
  else
    "ping-" ^ pong (n - 1)

and pong n =
  if n = 0 then
    "pong!"
  else
    "pong-" ^ ping(n - 1);;

let battle = ping 7;;
```

## Une analyse par motifs

Bien souvent, un algorithme est défini par un ensemble de cas à traiter. On analyse l'entrée du problème et on effectue un calcul en fonction de la forme de cette entrée.

Les langages comme Java ou C proposent la construction "switch" pour exprimer une telle décomposition par cas. En OCaml, on retrouve une construction similaire que l'on appelle "analyse par motifs" (pattern-matching en anglais). Par exemple, on peut décomposer un problème en fonction de la valeur d'un entier comme suit.
```ocaml
let classify_number = fun n ->
  match n with
  | 0 -> "neutre de l'addition"
  | 1 -> "neutre de la multiplication"
  | 2 -> "premier nombre premier"
  | 3 -> "aurait aimé être le premier nombre premier"
  | 42 -> "réponse à la question sur la vie, l'Univers et le reste"
  | 73 -> "premier et dont la transposition 37 est aussi premier"
  | _ -> "je ne sais pas"
 
let what_is_special_with_42 = classify_number 42
let what_is_special_with_99 = classify_number 99
```

Nous verrons que l'analyse par motifs d'OCaml est beaucoup plus riche et expressive que le switch de C ou Java. En particulier, l'analyse de motif est crucial pour programmer aisément avec les listes OCaml.

On reparlera abondamment de ces listes OCaml par la suite, mais succinctement il s'agit d'un type récursif construit à partir de la liste vide `[]` et de l'ajout à gauche (noté `x::l`) d'un élément `x` à une autre liste `l`. Une liste concrète comme `[1;2;3]` est en fait trois ajouts `1::2::3::[]`.

Voici par exemple (avec un peu d'avance, on y reviendra) un algorithme qui analyse une liste et supprime tous les couples d'éléments consécutifs égaux de cette liste.
```ocaml
let rec remove_repetition = fun l ->
  match l with
  | x :: (y :: _ as l') when x = y -> remove_repetition l' (* deux mêmes éléments au debut de l *)
  | x :: l' -> x :: remove_repetition l' (* sinon on garde le début de l *)
  | [] -> [] (* liste vide : rien à changer *)
    
let test1 = remove_repetition [1; 2; 3; 3; 2; 5; 7; 7]
let test2 = remove_repetition [1; 2; 3; 2; 5; 5; 5; 5; 5; 7]
```

Le compilateur OCaml vérifie que vos analyses de cas sont *exhaustives*, que vous n'avez pas oublié de cas. Les *warnings* sont ici une véritable aide, ne les ignorez pas!
```ocaml
let rec remove_repetition = fun l ->
 match l with
  | [] -> [] (* Le cas de la liste vide. La liste vide est le bon résultat. *)
  | x :: (y :: _ as l') -> (* une liste avec au moins deux éléments *)
    let l'' = remove_repetition l' in
    if x = y then
      l''
    else
      x :: l''
```

L'avertissement est clair : vous avez oublié dans cette définition de traiter le cas d'un élément suivi d'une liste vide, c'est-à-dire de la liste à un seul élément.

## Pour plus tard : un système de modules

OCaml est aussi équipé d'un système de modules pour favoriser le développement de logiciel de grandes tailles par une équipe de développeurs. Ce système de module est très puissant : il permet de s'assurer que les grands principes du génie logiciel (encapsulation, réutilisation, modularité) sont respectés par les programmeurs.
```ocaml
module Stack: sig
  (* Le module "Stack" a une interface (ou signature) introduite par le mot-clé "sig". *)
  type t (*
    Le type "t" est un nouveau type introduit par le module.
    Le typeur nous force à supposer qu'il est différent de tous les autres types du programme.
  *)

  val empty: t
  
  val push : int -> t -> t
  
  exception EmptyStack
  (* OCaml fournit un mécanisme d'exceptions comme C++ ou Python. *)
  
  val pop: t -> t
  
  val top: t -> int
end = struct (* Maintenant on implémente la signature désirée *)
  type t = int list (*
    Voici l'implémentation réelle du type t.
    Cette implémentation est interne, i.e. accessible seulement entre les mots-clés struct et end.
  *)

  let empty = []
  
  let push = fun x s -> x :: s
  
  exception EmptyStack
  
  let pop = fun l ->
    match l with
    | [] -> raise EmptyStack
    | _ :: t -> t
 
  let top = fun l ->
    match l with
    | [] -> raise EmptyStack
    | x :: _ -> x
end (* Fin de la définition de notre module Stack *)
(* Maintenant nous ne sommes que des utilisateurs, ou clients, de ce module *)
let s = Stack.empty
let s = Stack.push 42 s
let s = Stack.push 73 s
let x = Stack.top s
let s = Stack.pop s
let y = Stack.top s
let s = Stack.pop s
let z = Stack.top s
(* Le typeur rejette le programme suivant :
   let is_empty s = match s with [] -> true | _ -> false;;
*)
```

La dernière définition est rejetée même si une pile est bien implémentée par une liste. Pourquoi? Pour forcer les clients du module à ne pas faire de supposition sur l'implémentation interne d'une structure de donnée et s'autoriser à modifier cette implémentation dans le futur sans avoir à mettre à jour les clients du module `Stack`.

Nous étudierons aussi le système de modules d'OCaml et pourquoi il répond d'une façon très pertinente à des problématiques de développement logiciel que nous rencontrons très couramment.

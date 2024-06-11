Fonctions de première classe et programmation d'ordre supérieur
===============================================================

[Lien Sketch](https://sketch.sh/s/XjV2RE6tIUAJkvdfQ1rgFN),
[lien Sketch](https://sketch.sh/s/tDqsDWq7jwLNCLPX3mzky7).

Auteur initial : Yann Régis-Gianas.
Modifications par : Pierre Letouzey.

## Des fonctions de première classe

En programmation, une valeur *de première classe* peut être
utilisée sans restriction. On peut ainsi par exemple :

  - la lier à un identificateur (ou variable)
  - la passer en argument à une fonction (ou procédure)
  - la retourner comme résultat d'une fonction
  - la mettre dans des structures de données (p.ex. faire des listes de ces valeurs)
  - la comparer avec une autre valeur (du même type)

En OCaml, les fonctions sont des valeurs de première classe.

```ocaml
let bool_unary_funs = [(fun b -> b); not; (fun _ -> true); (fun _ -> false)];;
let some_bool_binary_funs = [(&&); (||); (fun b b' -> b <> b')];;
```

Notez que mettre des `( )` autour d'un opérateur comme `&&` ou `+` en fait une
fonction OCaml comme une autre, dite *préfixe*.

```ocaml
(&&);;
(+);;
(+) 1 2;;
( * );;  (* attention aux blancs ici, sinon OCaml croit voir un commentaire ! *)
```

Seule la comparaison de fonction est limitée :

 - Le test d'égalité `=` sur les fonctions peut s'écrire, mais échoue à l'exécution
 - la comparaison physique `==` marche mais est peu utile en pratique
 
On ne peut pas non plus *sérialiser* les fonctions OCaml (cf. module `Marshal`).


## Programmation d’ordre supérieur

Un programme est *d’ordre supérieur* s’il calcule avec des valeurs qui contiennent du code exécutable.

Oui, la POO est d’ordre supérieur : un objet, c'est un état interne + des méthodes agissant sur cet état.

La programmation fonctionnelle est aussi d’ordre supérieur (mais sans état).

Ici cet ordre supérieur va permettre d'être *modulaire* et *expressif*

## Modularité

Un composant logiciel est modulaire s’il est:

 - *cohérent* : on peut résumer ce qu’il fait en une phrase simple.
 - *faiblement couplé* : il a un nombre limité de dépendances.
 
Une architecture logiciel est modulaire si on peut substituer n’importe lequel de ses
composants par un composant équivalent sans que cela n’impacte le bon fonctionnement du logiciel.

Propriété de la programmation d’ordre supérieur :
Le fait de pouvoir paramétrer un objet par d’autres objets, de pouvoir paramétrer
une fonction par d’autres fonctions, permet de réduire le couplage.

La *cohésion* d’un composant est haute si ce composant est petit et général.

## Le style fonctionnel de la programmation

Il encourage:

 - l’écriture de fonctions courtes, générales et cohérentes ;
 - construites par applications de fonctions générales et d’ordre supérieur.

Le style fonctionnel de la programmation s’appuie sur le principe de
*compositionalité*. Cela signifie que l’on doit pouvoir donner un sens à un fragment
de programme uniquement à partir du sens donné à ses composants.
Cela favorise la localité du raisonnement, nécessaire à toute forme de modularité.


## Comment construit-on des fonctions ici ?

- par *définition*: c’est-à-dire en donnant leur code 
- par *composition*: en chaînant les calculs réalisés par d’autres fonctions.
- par *spécialisation*: en fixant l’un des paramètres d’une fonction.
- par *induction*: en définissant une fonction par cas sur une structure récursive
  et en prenant le point fixe de cette définition.

On y reviendra ci-dessous


## Le noyau fonctionnel d'OCaml et son expressivité

### Trois constructions fondamentales

Oublions pendant un court instant tout ce que nous avons appris jusqu'à maintenant sur OCaml, et supposons que nous avons uniquement en main les trois constructions suivantes :

  - l'usage d'une variable : `x`, `y,` ... ;
  - la construction d'une fonction : `fun x -> a` où `a` est une expression ;
  - l'application d'une fonction : `a b` où `a` et `b` sont des expressions.

En informatique théorique, ces trois constructions forment le [lambda-calcul](https://fr.wikipedia.org/wiki/Lambda-calcul).

Jusqu'où peut-on aller avec ces seules constructions? Avant de répondre à cette question, cherchons d'abord à bien les comprendre.


### Revoyons les premières fonctions disponibles, et leur types

Voici la fonction la plus simple que nous pouvons écrire à l'aide des deux premières constructions :
```ocaml
fun x -> x;;
```
Comprenez-vous comment le typeur a inféré son type? Saurez-vous faire le même exercice sur la fonction suivante :
```ocaml
fun x -> fun y -> x
```
Le type de cette fonction peut se lire comme suit : "J'attends une valeur de type 'a puis une valeur de type 'b pour produire une valeur de type 'c".

Remarquez cependant que dans les types, la flèche *associe à droite*. Cela signifie qu'il faut comprendre `a -> b -> c` comme si on avait écrit les parenthèses ainsi `a -> (b -> c)`. On peut donc lire ce type d'une autre façon, moins conventionnelle mais tout aussi juste : "J'attends une valeur de type 'a pour produire une fonction qui attend une valeur de type 'b pour à son tour produire une valeur de type 'c".

Considérons maintenant le programme suivant :
```ocaml
fun f -> fun x -> f x
```
Essayez de deviner son type... Avez-vous remarqué que le typeur a inséré des parenthèses dans l'affichage du type? Elles sont nécessaires car sans elles, le type `'a -> 'b -> 'a -> 'b` ne correspond pas à cette fonction : en effet, ce type ne témoigne par exemple pas du fait que `f` est une fonction dont le type d'entrée doit être compatible avec celui de `x`. Cette fonction attend une fonction en argument : on dit que c'est une **fonction d'ordre supérieur**. (Pour les curieux, l'ordre d'une fonction est le niveau d'imbrication des flèches situées à gauche d'une flèche dans son type.)

Avec la précédente fonction, nous savons prendre une fonction et l'appliquer une fois. Bien entendu, nous pourrions appliquer cette fonction deux fois :
```ocaml
fun f -> (fun x -> f (f x))
(* On peut factoriser les "fun" comme suit: *)
(* fun f x -> f (f x)) *)
```
De nouveau essayez de deviner le type de cette fonction. Désormais, vous devez avoir compris intuitivement le principe de fonctionnement de l'algorithme d'inférence de type : ce dernier récolte des *contraintes de type* en parcourant l'expression et calcule la *solution la plus générale* pouvant satisfaire ces contraintes. On dit que le type inféré par OCaml est **principal**. 

Pourquoi est-ce si important d'être principal? C'est la garantie que le typeur n'a pas fait de choix arbitraire entre tous les types possibles pour notre expression : le typage peut être fait une fois pour toute au moment de la définition de la fonction et non pas à chaque appel de celle-ci.

Contrairement aux langages où les fonctions sont de seconde classe (comme le C par exemple), l'application d'une fonction peut utiliser une expression arbitrairement complexe pour calculer la fonction à appeler. Voici quelques exemples (essayez d'en deviner les types) :

```ocaml
(fun x -> x) (fun y -> y);;
fun f x -> (f x) x;;
fun f -> fun x y -> f y x;;
(fun f g -> fun x -> f (g x)) (fun x -> x) (fun x -> x);;
(fun x y -> x) (fun y x -> y);;
```
Le dernier exemple doit vous sembler quelque peu étrange : la fonction appliquée semble attendre deux arguments et pourtant elle semble se satisfaire si on ne lui en passe qu'un seul! On appelle ce type d'application des **applications partielles** et nous verrons bientôt leur utilité.

En ce point, vous avez normalement compris les règles de typage de ce langage fonctionnel noyau et le sens de ces trois constructions fondamentales. Nous allons maintenant expérimenter avec ce langage noyau pour commencer à toucher du doigt l'expressivité des fonctions de première classe.

### Codage du let à l'aide de ces trois constructions

La construction `let x = a in b` peut être vu comme un *sucre syntaxique* pour `(fun x -> b) a`. Dans les deux cas, on calculera `a` puis on nommera `x` la valeur issue de ce premier calcul, avant de poursuivre avec le calcul de `b` (soit car il est après le `in`, soit car c'est le corps de la fonction que l'on applique). Le `let` est donc une façon d'écrire plus lisiblement un calcul qui n'est rien d'autre qu'une application de fonction.

Ainsi l'avant-dernier exemple se comprend beaucoup plus facilement si on l'écrit avec des `let` (mais c'est exactement le même calcul qui est représenté!)
```ocaml
let compose = fun f g -> fun x -> f (g x) in
let id = fun x -> x in
compose id id
```
Nous pouvons donc utiliser des `let` sans sortir de notre petit noyau fonctionnel.

### Codage des entiers à l'aide de ces trois constructions

```ocaml
let zero = fun z s -> z in
let succ = fun n -> fun z s -> s (n z s) in
let two = (succ (succ zero)) in
let three = succ two in
let add = fun n m -> fun z s -> m (n z s) s in
let five = add three two in (* On vient de calculer 3 + 2 = 5! *)

(* Vous n'y croyez pas? Voici un convertisseur de ces entiers vers int ! *)
let to_int = fun n -> n 0 (fun x -> x + 1) in
(* ... et voici le cinq promis ! *)
to_int five;;
```

Dans ce codage, un entier est représenté par une fonction qui prend en argument deux fonctions :

- `z` représente le calcul que l'on souhaite faire si l'entier est zéro ;
- `s` représente le calcul que l'on souhaite faire si l'entier est le successeur d'un autre entier.

C'est à l'entier de choisir en fonction de sa valeur laquelle de ces deux fonctions appeler : la fonction qui représente le zéro appelle `z` tandis que la fonction qui représente le successeur de n passe à la fonction `s` le résultat du calcul produit par l'appel à n.

Les entiers codés sous cette forme sont appelés [Entiers de Church](https://en.wikipedia.org/wiki/Church_encoding). En pratique, on leur préfère les entiers machines, beaucoup plus efficaces, mais ils ont un intérêt théorique car ils montrent que notre petit noyau fonctionnel est capable de représenter des objets mathématiques intéressants. Avec le même type de codage, on peut représenter des booléens, des listes, des arbres, etc.

Ce codage peut vous sembler très abstrait et d'intérêt purement théorique. Détrompez-vous! Quand vous étudierez le [patron de conception de Visiteur](https://en.wikipedia.org/wiki/Visitor_pattern) en programmation objet, vous le rencontrerez à nouveau!

## A quoi sert l'ordre supérieur ? Généralité, généralité et généralité

Le style de programmation fonctionnelle répond au problème de la robustesse au changement en favorisant la *généralité* des composants logiciels (plutôt que l'extensibilité du logiciel comme en POO). Alors généralisons !

En TP, vous avez programmé une fonction `is_sorted` sur les listes d'entiers. Par exemple:
```ocaml
let rec is_sorted l = match l with
 | [] | [_] -> true
 | x :: ((y :: q) as l') -> x <= y && is_sorted l'
 
let _ = is_sorted [1;5;6]
```

Deux nouvelles possibilités des `match`, au passage :

- On peut *regrouper* des motifs successifs comme `[]` et `[_]` en un seul cas
  si ces motifs déclenchent le même code (et qu'ils ont exactement les mêmes variables).
- On peut aussi nommer des portions supplémentaires d'un motif via le mot-clé `as`.
  Ceci évite de *refabriquer* un début de liste `y::q` en mémoire juste pour
  lancer l'appel récursif `is_sorted (y::q)`.

En tout cas, notez que le type inféré par OCaml est plus général que `int list -> bool`.
En fait, `is_sorted` est considéré comme *polymorphe*, et accepte toutes sortes de listes,
tant qu'on peut comparer leurs éléments avec `(<=)`. Maintenant, allons un cran plus loin :
comment utiliser cette fonction avec un ordre strict, ou un ordre décroissant ? Il faut alors
la généraliser, et en faire une fonction d'ordre supérieure recevant en plus une comparaison
booléenne :

```ocaml
let rec is_sorted_gen cmp l = match l with
 | [] | [_] -> true
 | x :: ((y :: q) as l') -> cmp x y && is_sorted_gen cmp l'
```

Notez bien le type obtenu pour `is_sorted_gen`. 

Maintenant on peut utiliser directement la fonction généralisée : 

```ocaml
let _ = is_sorted_gen (<=) [1;5;5;6]
let _ = is_sorted_gen (<) [1;5;6]
let _ = is_sorted_gen (>) [7;3;1]
```

Ou bien encore préparer une *version spécialisée* si elle revient fréquemment, souvent juste par *application partielle*:

```ocaml
let is_sorted_is_back = is_sorted_gen (<=)
```

Exercice : Au fait, que ferait ici `is_sorted_gen (<>)` ? Rappel: `<>` est le test de différence, c'est-à-dire la négation du test d'égalité `=`.
N'y aurait-il pas un lieu avec la fonction `remove_repetition` du premier cours ?


## Comment calculer des fonctions?

### Par définition

La façon la plus simple de calculer une fonction est d'écrire le calcul qu'elle réalise.
```ocaml
let f = fun x -> 2 * x;;
let f x = 2 * x;; (* Equivalent à la définition précédente. *)
```
### Par spécialisation

Une fois vos fonctions correctement généralisées, vous pouvez retrouver des cas particuliers par *spécialisation*, ce qui
peut souvent se faire par une simple applicaton partielle.

On a vu auparavant le cas de `is_sorted`. OCaml fournit une fonction de tri des listes sur ce modèle : `List.sort`. Au lieu d'une comparaison booléenne, il faut ici une comparaison ternaire.

```ocaml
List.sort;;
type medal = Gold | Silver | Bronze
type finalist = { name : string; medal : medal }
let compare_finalists f1 f2 =
  match f1.medal, f2.medal with
  | Gold, (Silver|Bronze) -> -1
  | (Silver|Bronze), Gold -> 1
  | Silver, Bronze -> -1
  | Bronze, Silver -> 1
  | _, _ -> 0;; 
let make_podium = List.sort compare_finalists (* Ici, on a spécialisé List.sort. *)
let podium = make_podium [ 
    { name = "Jacques" ; medal = Bronze }; 
    { name = "Paul"    ; medal = Gold   };
    { name = "Nicolas" ; medal = Bronze };
    { name = "Luke"    ; medal = Silver }
 ]
```
Dans cet exemple, la fonction `make_podium` est obtenue par spécialisation d'une fonction plus générale `List.sort` sur la relation d'ordre réalisée par `compare_finalists`. Au passage il y a plus simple pour écrire une comparaison sur le type `medal` (p.ex. utiliser la comparaison standard `Pervasives.compare`).

### Par composition

La composition de fonction est une opération fondamentale en programmation fonctionnelle. 

Dans l'exemple suivant, on cherche à résoudre un problème combinatoire : quels sont toutes les façons de sommer deux nombres distincts d'une liste pour obtenir un nombre donné.
```ocaml
open List

let distinct_pair x y = if x = y then [] else [(x, y)]

let all_pairs l1 l2 = map (fun x -> map (distinct_pair x) l1) l2 |> flatten |> flatten

let equal_sum s (x, y) = (x + y = s)

let answer s l = all_pairs l l |> filter (equal_sum s)

let _ = answer 10 [1; 3; 4; 7; 9; 6]
```

L'opérateur `|>` (écrit | puis > en ASCII) est maintenant disponible par défaut en OCaml. Il est défini comme suit :
```ocaml
let ( |> ) x f = f x
```
Il est associatif à gauche. Ainsi, quand on écrit `x |> f |> g |> h`, il faut lire comme `h (g (f x))`. Il s'agit donc d'une forme de composition, qui doit vous rappeler le *pipe* des commandes shells.

Vous constatez sur cet exemple que la généralité des fonctions utilisées dans ce programme permet de répondre en seulement une ligne à la question posée. La déclarativité de la réponse donnée nous permet de nous convaincre de sa correction. Vous ne savez peut-être pas à quoi correspondent `map` et `filter` : ce n'est pas grave car nous allons les implémenter dans la section suivante.

### Par induction

Très souvent, il suffit de suivre la structure inductive d'une valeur pour définir une fonction. Pour les listes, il suffit de préciser le comportement sur la liste vide et la liste formée d'un élément et d'une liste pour obtenir une fonction qui travaille sur toutes les listes possibles. La même méthode fonctionne sur les arbres : il suffit de dire ce que l'on souhaite faire sur l'arbre vide et sur les arbres formés d'un noeud et de deux sous-arbres pour obtenir une fonction qui travaille sur tous les arbres.
```ocaml
let rec map f l =
match l with
| [] -> []
| x :: xs -> f x :: map f xs

let rec filter p = function
| [] -> []
| x :: xs when p x -> x :: filter p xs
| _ :: xs -> filter p xs

let l = [1; 2; 3]

let l' = filter (fun x -> x mod 2 = 0) l

```

## Briller en société grâce aux morphismes et aux catamorphismes

Quand on se donne une structure de données, il y a un certain nombre de schémas de calcul qui s'appliquent naturellement. Ces schémas de calcul suivent la structure des données et c'est pour cela que l'on parle de "morphismes".

### Les morphismes

Un morphisme préserve la structure de la valeur sur laquelle il s'applique. L'exemple typique est la fonction `List.map`.
```ocaml
let rec map f l =
  match l with
  | [] -> []
  | x :: xs -> f x :: map f xs
```
Cette fonction d'ordre supérieur applique la même fonction `f` sur tous les éléments d'une liste pour construire une nouvelle liste. Cette même idée peut s'appliquer sur une paire de listes de même longueur.
```ocaml
let rec map2 f l1 l2 =
  match l1, l2 with
  | [], [] -> []
  | x :: xs, y :: ys -> (f x y) :: map2 f xs ys
  | _, _ -> assert false
  
let sum2 l1 l2 = map2 (+) l1 l2
(* ou bien : *)
let sum2 = map2 (+)

let l3 = sum2 [1;2;3;4] [3;4;5;6]
```

On peut ensuite combiner tout cela dans des exemples de plus grande ampleur. Par exemple:

```ocaml
type matrix = int list list

let matrix_add = map2 (map2 (+))

let m1 = [[1;2];[3;4]]
let m2 = [[1;0];[0;1]]

let _ = matrix_add m1 m2
```


Exercice : sauriez-vous programmer `map` sur les arbres binaires?

```ocaml

```

On peut également raisonner sur ces fonctions par induction sur les listes.
Par exemple, si `∘` est la fonction `compose` vue précédemment :

```
(map f) ∘ (map g) = map (f ∘ g)

Autrement dit, pour toute liste l, map g (map f l) = map (g ∘ f) l

Preuve:
- Cas de base (l = []) :
   map g (map f []) = map g [] = []
   map (g ∘ f) [] = []

- Cas d'induction (l = x :: xs) :
    On suppose que "map g (map f xs) = map (g ∘ f) xs" (HI).
    On veut prouver que "map g (map f (x :: xs))) = map (g ∘ f) (x :: xs)"
    
    map g (map f (x :: xs)) = map g (f x :: map f xs) = (g (f x)) :: map g (map f xs)
    Par (HI):
    map g (map f (x :: xs)) = (g (f x)) :: map (g ∘ f) xs = map (g ∘ f) (x :: xs)

```

### Les catamorphismes (fold)

Un schéma de calcul typique consiste à itérer un calcul en parcourant une structure de donnée.
Pour les listes, voici quelques instances de ce schéma :

```ocaml
let rec sum = function
  | [] -> 0
  | x :: xs -> x + sum xs
  
let rec product = function
  | [] -> 1
  | x :: xs -> x * product xs

let rec length = function
  | [] -> 0
  | x :: xs -> 1 + length xs
```

En observant ces fonctions, on remarque qu'elles renvoient une valeur pour la liste vide et que pour une liste non vide de la forme `x :: xs`, on *combine* l'élément `x` avec le résultat de l'appel récursif sur `xs` (pour une certaine notion de combinaison).

Plutôt que de répéter encore et encore ce schéma dans nos programmes, essayons plutôt d'en extraire une fonction généralisant cette situation.
```ocaml
let rec fold f accu = function
  | [] -> accu
  | x :: xs -> f x (fold f accu xs)
```

On retrouve alors les fonctions précédentes en une seule ligne :

```ocaml
let sum = fold ( + ) 0
let product = fold ( * ) 1
let length = fold (fun _ len -> len+1) 0
(* ou même : *)
let length = fold (fun _ -> succ) 0
```

Exercice : En utilisant `fold`, définir une fonction `max_list` calculant le maximum d'une liste d'entiers. On supposera ici que `max_list []` vaut `min_int` (le plus petit entier disponible). Oui je sais, ce n'est pas idéal, mais bon...

```ocaml

```

Ce `fold` existe déjà dans la bibliothèque OCaml, il s'agit de `List.fold_right` à l'ordre près de ses deux derniers arguments.
Il existe également une autre variante, nommée `List.fold_left`, dont nous parlerons la prochaine fois.

Une instance intéressante de `fold` est le `reduce`. On suppose que la fonction est associative, que le type de l'accumulateur est le même que celui des éléments et on suppose aussi que la liste donnée est non vide. On a alors :

```
reduce f [a1; a2; ...; aN] = f a1 (f a2 (f ... aN)))
```

Exercice : définir `reduce` directement et ensuite avec l'aide de `fold`.

```ocaml

```

L'intérêt de ce `reduce` est de pouvoir ensuite réorganiser l'ordre des calculs (par associativité) puis les distribuer sur plusieurs processeurs ou plusieurs machines. Voir *map/reduce* propularisé par Google ou des bibliothèques OCaml comme `parmap` ou `functory`.

Exercice : sauriez-vous définir la fonction `fold` sur les arbres?

```ocaml

```

## Encore quelques autres usages de l'ordre supérieur

### Le style par passage de continuation

Le style de programmation dit "par passage de continuations" (en anglais _Continuation Passing Style_ que l'on écrit CPS) consiste à toujours
recevoir une fonction, et toujours la lancer à la fin des travaux locaux. Cela peut sembler étrange, mais cela apporte des réponses intéressantes
concernant la programmation *récursive terminale* (dont nous parlerons la semaine prochaine) et aussi concernant la programmation *réactive*
(*hooks* et *callbacks* sont liés à ces notions de continuations). Vous reverrez ce style CPS en Master, ici voici juste un petit exemple:

```ocaml
let rec fact_aux (continuation : int -> int) n =
  if n = 0 then continuation 1 else fact_aux (fun v -> continuation (v * n)) (n - 1)
  
let fact n = fact_aux (fun x -> x) n
```

Comment lire cette drôle de version de `fact`? 

D'abord, il faut comprendre ce que représente `continuation`. Il s'agit d'une fonction qui "représente ce que l'on doit faire du résultat du calcul en cours". 

Le cas de base est assez clair : en ce point, on a fini de faire calculer `fact_aux` et on peut donc donner la main à `continuation` en lui passant le résultat du calcul pour `n = 0`, c'est-à-dire `1`. 

Dans le cas récursif, on doit mettre à jour la continuation puisqu'après l'appel récursif, il faudra multiplier le résultat `v` obtenu pour le cas `n - 1` et ainsi pouvoir obtenir le résultat du calcul pour `n`. Ce résultat `v * n` doit à son tour être passé à `continuation` pour poursuivre ce qui était prévu au moment de l'appel à `fact_aux`.

La fonction `fact` se contente de passer à `fact_aux` une continuation qui représente le fait de renvoyer immédiatement son argument.

Pour mieux comprendre ce qui se passe, essayons de représenter une trace d'exécution pour le calcul `fact 2`:

```
fact 2 =
fact_aux (fun x -> x) 2 =
fact_aux (fun v -> (fun x -> x) (v * 2)) 1 =
fact_aux (fun v -> ((fun v -> (fun x -> x) (v * 2)) (v * 1))) 0 =
(fun v -> ((fun v -> (fun x -> x) (v * 2)) (v * 1))) 1 =
(fun v -> (fun x -> x) (v * 2)) (1 * 1) =
(fun v -> (fun x -> x) (v * 2)) 1 =
(fun x -> x) (1 * 2) =
(fun x -> x) 2 =
2
```

### Programmer avec des combinateurs

La programmation par combinateurs consiste à utiliser des opérations sur les fonctions pour construire des fonctions complexes à partir de fonctions très élémentaires. Pour illustrer ce style de programmation, nous allons nous focaliser sur l'exemple des **combinateurs d'analyseurs lexicaux**.

```ocaml
type 'a lexing_result = Failed | Success of 'a * char list
(* En cas de succès, on renvoie le reste des caractères et une valeur. *)

type 'a lexer = char list -> 'a lexing_result

let char c : char lexer = function 
  | c' :: cs when c = c' -> Success (c, cs)
  | _ -> Failed

let either (c1 : 'a lexer) (c2 : 'a lexer) : 'a lexer =
  fun cs -> match c1 cs with
    | Failed -> c2 cs
    | s -> s
     
let a_or_b = either (char 'a') (char 'b')

let seq (c1 : 'a lexer) (c2 : 'b lexer) : ('a *'b) lexer =
  fun cs ->
     match c1 cs with
     | Success (x, cs') ->
       (match c2 cs' with
         | Success (y, cs'') -> Success ((x, y), cs'')
         | Failed -> Failed)
     | Failed -> Failed

let rec many c =
  fun cs -> match seq c (many c) cs with
    | Failed -> Success ([], cs)
    | Success ((x,xs), cs') -> Success (x::xs, cs')

let binary : char list lexer = many (either (char '0') (char '1'))

let _ = binary ['1';'0';'1';'0';'z']

```

### Représenter des valeurs "potentielles"

Un autre usage des fonctions est la représentation de valeurs "infinies". Bien entendu, la mémoire des ordinateurs étant finie, on ne peut pas représenter _explicitement_ une valeur infinie. Par contre, des fonctions peuvent servir à la représentation _implicite_ d'une valeur infinie, en donnant une méthode permettant d'en calculer une portion aussi large que l'on veut (ou peut, selon le temps et la mémoire disponible).

```ocaml
type 'a stream = Stream of 'a * (unit -> 'a stream)

let rec from n = Stream (n, fun () -> from (n+1))

(* Voici une valeur infinie! Tous les entiers naturels sont là! *)
let naturals = from 0

let head = function Stream (a, next) -> a

let tail = function Stream (_, next) -> next ()

let two = head (tail (tail naturals))

let rec nth n s =
  if n = 0 then head s else nth (n-1) (tail s)

let million = nth 1000000 naturals

let rec everyother s =
  Stream (head s, fun () -> everyother (tail (tail s)))

let even_naturals = everyother naturals

let twomillion = nth 1000000 even_naturals
```

Il s'agit d'un début de programmation *paresseuse*. OCaml fournit des primitives dédiées
à ce style de programmation (voir le module `Lazy`), mais l'exemple ci-dessus montre qu'on
peut déjà faire un certain nombre de choses avec de simples fonctions.

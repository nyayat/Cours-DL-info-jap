Évaluation d'un programme OCaml (Sémantique)
============================================

Quelques notes correspondant grosso modo à la séance du 19/11/2020.

Cela vient en complément des [transparents](slides/2019/cours-06.pdf) de Y. Régis-Gianas de 2019, à lire donc en premier.

**Rappel de l'objectif** : donner un sens précis (i.e. une *sémantique*) aux calculs informels que l'on a fait jusqu'à maintenant lors des exécutions "à la main" de phrases OCaml.

## Et les paires et n-uplets ?

Vous aurez peut-être remarqué que les [transparents](slides/2019/cours-06.pdf) ne mentionnent jamais le cas des paires `(e_1,e_2)` et plus généralement des n-uplets `(e_1,...,e_n)`. C'est simplement que ce cas y est assimilé au cas des constructeurs de type algébriques (notés `C (e_1,...,e_n)`). Et effectivement le comportement des paires et n-uplets va être totalement similaire à celui des constructeurs.

Pour être précis, page 6/15, il faut donc ajouter parmi les **valeurs** possibles les n-uplets de valeurs `(v_1,...,v_n)`. Et comme pour l'évaluation d'un constructeur, l'évaluation d'une expression `(e_1,...e_n)` va se faire en évaluant chacun des `e_i` vers la valeur correspondante. On peut également trouver une paire ou un n-uplet en position de motif dans un `match`, mais le comportement est alors sans surprise particulière, voir la page 9/15.

D'ailleurs, on peut noter que si les paires et n-uplets sont pratiques en OCaml (syntaxe légère, type prédéfini `*` disponible dès le lancement), elles peuvent se simuler avec des types algébriques. Par exemple:

```ocaml
(* Un remplacement possible de 'a * 'b via un type algébrique : *)
type ('a,'b) mapaire = MaPaire of 'a * 'b

(* Et maintenant, à la place d'une paire (1,2) : *)
let exemple = MaPaire (1,2)

(* Et pour programmer avec cet ersatz de paires, par exemple : *)
let monfst p =
 match p with
 | MaPaire (a,b) -> a
```

Attention, le `*` dans la définition de `mapaire` n'est pas une paire primitive OCaml, mais la syntaxe OCaml indiquant le type des arguments du constructeur `MaPaire`. Et notez bien que ce `mapaire` se comporte *comme* une paire primitive OCaml, mais n'est pas égal avec : il faut un petit effort de traduction pour passer de l'un à l'autre (même si en fait OCaml les représentera exactement de la même façon en mémoire).

## Limites de la méthode d'évaluation par substitution

La méthode d'évaluation "haut-niveau" par substitution présenté au début du document est commode pour un humain. Mais comme il est dit page 12/15 elle est difficile à implémenter efficacement. Et de plus **elle n'est correcte que pour le fragment purement fonctionnel d'OCaml**.

Pour la partie impérative, et en particulier dans le cas de constructions modifiables "en place", il faut passer à une méthode "bas-niveau" pour évaluer correctement un programme OCaml, et faire intervenir explicitement une certaine notion de mémoire (bref, des *pointeurs*).

Voici l'exemple typique de code impératif dont l'évaluation "par substitution" va mal se passer :

```ocaml
let r = ref 0 in r := 1; !r
```

Rappel, le `;` n'est qu'un `let` anonyme. La phrase précédente signifie donc:

```ocaml
let r = ref 0 in
let _ = r := 1 in
!r
```

Autre rappel, le type `ref` est défini ainsi:

```ocaml
type 'a ref = { mutable contents : 'a }
```

Et les opérations sur ce type `ref` sont juste des opérations sur l'enregistrement en question, aussi bien la création (fonction `ref : 'a -> 'a ref`), l'accès (fonction `(!) : 'a ref -> 'a)` que la modification (fonction `(:=) : 'a ref -> 'a -> unit`). Bref l'exemple précédent correspond également au code :

```ocaml
let r = { contents = 0 } in
let _ = r.contents <- 1 in
r.contents
```

Si on essaie d'évaluer ce code par substitution, `{ contents = 0 }` est déjà une valeur, la règle pour le `let` (cf page 7/15) nous indique ensuite de substituer cette valeur partout ensuite où il y a du `r`, ce qui donne:

```ocaml
let _ = { contents = 0 }.contents <- 1 in
{ contents = 0 }.contents
```

Problème : plus rien ne relie maintenant les deux occurrences des `{ contents = 0 }` dans les deux lignes restantes. En particulier on peut en modifier l'une sans changer l'autre. Un aspect crucial, à savoir le *partage mémoire* (ou *aliasing*) a été perdu, et c'est justement le souci avec la sémantique à substitution en présence de données mutables.

Pour finir cette évaluation, `{ contents = 0 }.contents <- 1` donne un résultat `()` (seule valeur dans le type `unit`), après avoir modifié *cette* copie de `{ contents = 0 }` (qui est perdue juste après).

```ocaml
let _ = () in
{ contents = 0 }.contents
```

Ensuite un tel `let` anonyme occasionne le remplacement de `()` ... nulle part, ce qui donne juste `{ contents = 0 }.contents`, qui lui se calcule en `0`, alors que la valeur correcte attendue pour cette évaluation est `1`. En effet écriture puis lecture de `r` se font au même emplacement mémoire.

## Qu'est-ce qu'une valeur cyclique ?

La notion de valeur cyclique est mentionnée page 10/15, pour évaluer un `let rec`. Il s'agit d'une valeur OCaml `v` comme décrite page 6/15, mais dont on s'arrange pour qu'une sous-partie de cette valeur soit égale à `v` elle-même.

Déjà, il y a plusieurs cas où une valeur OCaml peut faire intervenir d'autres valeurs en interne :

 - Par exemple, pour une valeur fonctionnelle `v = fun x -> e`, l'expression `e` du corps peut mentionner des valeurs, vu que les valeurs sont des cas particuliers de valeurs. 
 
 - Autre exemple, une valeur d'un type algébrique `v = C (v_1,...v_n)`.

Dans les deux cas, on peut former un cycle en mentionnant `v` lui-même, soit dans le corps `e` d'une fonction (valeur d'une fonction récursive), soit dans une sous-partie `v_i` (ou même une sous-sous-partie...) d'une structure de données. Niveau implémentation, pensez à un pointeur ramenant de l'intérieur d'une structure (qui est un bloc mémoire) vers le début de ce bloc.

Notez en effet qu'OCaml accepte dans certaines conditions des cycles sur les données : le `let rec` n'est pas réservé exclusivement aux fonctions ! Par exemple:

```ocaml
let rec liste_cyclique = 1 :: liste_cyclique
```

Cette définition semble former une liste infinie contenant uniquement des 1. Mais cela n'utilise heureusement pas une mémoire infini, juste un bloc de taille 2 contenant `[ 1 | ptr ]`, où `ptr` est l'adresse de ce bloc lui-même (pointeur "retour"). Tout accès au début de cette liste donnera des éléments 1, par exemple `List.nth liste_cyclique 1000 = 1`. Par contre, toute tentative d'aller jusqu'au bout de la liste se soldera par un calcul infini, même un simple `List.length liste_cyclique`.

Un autre exemple :

```ocaml
let rec flip_flop = 1 :: 2 :: flip_flop
```

Ici cette liste se comporte comme une alternance infinie de 1 et de 2. En mémoire, il s'agit de deux blocs d'adresses `ptr1` et `ptr2` contenant respectivement `[ 1 | ptr2 ]` et `[ 2 | ptr1 ]` (cycles "croisées").

De telles données cycliques sont à éviter en temps normal, car leur usage est très délicat. Par contre elles peuvent parfois être commodes, comme par exemple pour parler de valeurs cycliques dans notre mini-évaluateur, voir le traitement du `RecFunction` dans [mycaml.ml](cours-10/mycaml.ml).


## Qu'est-ce donc qu'une clôture ?

Le terme de **clôture** est un autre nom possible pour les **fermetures** décrites page 14/15, c'est-à-dire un groupement d'un pointeur de code et d'un environnement (ensemble de valeurs qui seront utilisées par ce code).
En anglais, on parle de **closure**.

C'est maintenant une notion extrêmement répandue en informatique, bien au delà d'OCaml et des langages fonctionnels. Plus de détails sur la [page Wikipédia](https://fr.wikipedia.org/wiki/Fermeture_%28informatique%29).

## Et les exceptions ?

On se contentera ici d'une vision intuitive du comportement des exceptions et des opérations les concernant, `raise` et `try ... with ...`, comme décrites dans le cours 6. Mais en tout cas, l'évaluation d'une expression  OCaml réaliste ayant passé avec succès l'étape de typage a en tout trois issues possibles :

 - un calcul infini (par exemple dû à un `let rec` sans cas de base).
 - un arrêt causé par la levée d'une exception non rattrapée par un `try` englobant.
 - un résultat "normal", c'est-à-dire une *valeur* dans le même type que l'expression qu'on évalue.


## Quelques mots sur le petit évaluateur de code

Le programme donné dans [cours-10] permet de lire un fichier mini-OCaml (qui est une version simplifiée d'OCaml inventée ici, avec seulement quelques constructions essentielles), puis représenter ces phrases mini-OCaml dans un format de données facile à manipuler ensuite, puis évaluer ces phrases. On parle également *d'interpréteur* pour un tel programme, et il peut aussi être vu comme une forme *d'introspection*. Vous continuerez ce genre d'étude en Master 1 dans le cours de Compilation.

Fichiers principaux:

- [AST.ml](cours-10/AST.ml) : une description des constructions d'un mini-OCaml, via des types algébriques s'utilisant les uns les autres (définitions *mutuelles*). Les noms des types et des constructeurs devraient être assez parlants, à part peut-être `KApp` qui va servir à représenter un constructeur mini-OCaml et ses arguments : `C (e_1,...,e_n)` sera encodé comme `KApp(CId "C", [ee1;...;een])` si `eei` est l'encodage de l'expression mini-OCaml `e_i`.

- [lexer.mll](cours-10/lexer.mll) et [parser.mly](cours-10/parser.mly) sont des fichiers décrivant le format attendu d'un programme mini-OCaml. Ces fichiers `.mll` et `.mly` sont respectivement destinés aux utilitaires `ocamllex` et `menhir` (successeur de `ocamlyacc`), qui vous nous fabriquer ici des lecteurs de fichiers mini-OCaml pour notre évaluateur. Les fichiers `.mll` et `.mly` ne sont pas écrit en OCaml, mais dans des dialectes dédiés qu'il est normal de trouver illisible (pour l'instant, là encore rendez-vous en M1 Compilation).

- [mycaml.ml](cours-10/mycaml.ml) est le coeur de notre mini-évaluateur. Par rapport à l'année dernière, j'y ai ajouté le code pour les fonctions récursives et celui pour le traitement du `match`. Il y avait aussi un petit bug dans le code de 2019 traitant d'une application `App (a,b)`, le calcul de la valeur de l'argument `b` se faisait dans un environnement `env` qui n'était pas le bon (celui dans la fonction issue de `a` au lieu de l'ancien `env`). Autre ajout : un début de traitement des opérateurs arithmétiques, histoire de pouvoir faire au moins quelques additions. Attention, pour garder le code simple, ces opérations sont à écrire en mini-OCaml en "infixe", par exemple `(+) 1 2` au lieu de `1+2`, et `( * ) 1 2` au lieu de `1*2` (les espaces dans ce dernier cas sont pour éviter toute confusion avec les commentaires).

- [TEST.sh](cours-10/TEST.sh) et [TEST.utop](cours-10/TEST.utop) : quelques exemples d'utilisation de ce petit évaluateur, soit depuis la ligne de commande, soit à l'intérieur d'un toplevel ocaml amélioré (utop). Dans les deux cas, commencer par compiler le code avec `make` et/ou `make top`.

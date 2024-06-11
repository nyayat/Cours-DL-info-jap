La récursivité terminale
==========================

[Lien Sketch](https://sketch.sh/s/6k9ft6DS3nA6xQjVNa4v1g)

Pierre Letouzey, d'après Yann Régis-Gianas.

Après avoir déjà évoquée parfois cette notion, voici le moment d'étudier précisément la *récursivité terminale* (*tail recursion* ou *tailrec* en anglais).

Note : solution des exercices [ici](https://sketch.sh/s/xqq4NfSAyoChnDhjTGemTS)

## Un premier exemple : la factorielle

Reprenons l'archétype de la fonction récursive sur les entiers :
```ocaml
let rec fact n = 
  if n = 0 then 1
  else n * fact (n-1);;

let _ = fact 10
```
Comme on l'avait déjà vu ensemble, l'évaluation d'un calcul comme `fact 10` implique de *mettre en attente* la  multiplication par 10 tant que son argument de droite, l'appel de fonction `fact 9`, n'a pas fini de se calculer. Idem ensuite pour les multiplications suivantes. Cela oblige à stocker les détails de ces multiplications (ici leurs arguments de gauche, à savoir l'entier `n` du moment). Ce stockage se fait dans une *pile des appels* (Call Stack en anglais). Par défaut, cette pile n'est pas illimitée, et risque de déborder assez vite, avec l'exception `Stack_overflow`. Pour un système comme Sketch.sh (qui utilise une compilation vers javascript), cela peut venir rapidement, parfois dès 5000 tours de récursion. Avec de l'OCaml compilé nativement vers une machine 64-bit, on a un peu plus de marge (environ 30000 à 100000 tours).

Note : au fait, pour `fact`, on atteint ici bien avant cela des soucis de débortement de capacité des entiers machines, cf les exemples ci-dessous. Mais ce n'est pas tellement la question ici.
```ocaml
let _ = fact 17;; (* négatif car le vrai résultat est juste > à max_int sur cette plate-forme 32-bit ! *)
let _ = fact 34;; (* 0 sur cette plate-forme 32-bit ! (multiplication d'assez de nombres paires) *)
let _ = try fact 4000 with Stack_overflow -> -1;;  (* Jusqu'ici tout va bien, réponse 0 comme prévue *)
let _ = try fact 30000 with Stack_overflow -> -1;; (* Boum *)
```
Programmer dans le style récursif terminal, c'est justement éviter toute mise en attente de calculs dans cette pile des appels, en faisant ces calculs *avant* l'appel récursif (p.ex. dans la préparation de ses arguments). Et on s'arrange pour que la réponse de cet appel récursif soit directement la réponse globale attendue. Ici on peut faire cela avec juste un argument suppémentaire, nommé *accumulateur*. Cela oblige à passer par une fonction auxiliaire avec cet argument en plus. Cette fonction auxiliaire est à définir soit avant, soit dans la fonction principale (fonctions imbriquées), et la fonction principale se contente alors de lancer la fonction auxiliaire avec les bons arguments initiaux.
```ocaml
let rec fact_loop n acc =
  if n = 0 then acc
  else fact_loop (n-1) (n*acc)

let fact n = fact_loop n 1

(* ou bien via une fonction imbriquée: *)

let fact n =
  let rec loop n acc =
    if n = 0 then acc
    else loop (n-1) (n*acc)
  in loop n 1
  
let _ = fact 10;;
let _ = fact 17;; (* Toujours négatif sur cette plateforme 32-bit *)
let _ = fact 34;; (* Toujours zero sur cette plateforme 32-bit *)
let _ = fact 1000000;; (* Plus de Stack Overflow *)
```
Conseil : éviter les noms `aux` pour les fonctions auxiliaires, ça n'est pas informatif (ni joli)...

Notez que l'ordre des calculs a changé au passage. Avant on avait `fact 3 = 3*(2*(1*1))`.
Maintenant:
```
fact 3 = fact_loop 3 1
       = fact_loop 2 (3*1)
       = fact_loop 1 (2*(3*1))
       = fact_loop 0 (1*(2*(3*1)))
       = 1*(2*(3*1))
```
Evidemment, sur cet exemple, ça n'a pas de conséquences vu que la multiplication sur les entiers est associative (on peut bouger les parenthèses) et commutative (on peut inverser l'ordre des arguments).
Mais ce genre de renversement est un effet habituel du passage au style par accumulateur, voir plus bas sur les listes. Ici, si on le veut vraiment, on peut retrouver l'ordre d'origine des calculs, en changeant la gestion de l'argument `n`, mais il n'y aura pas d'astuce équivalente dans la suite quand on manipulera des listes au lieu d'entiers.
```ocaml
let fact_incr n =
  let rec loop k acc =
     if k > n then acc
     else loop (k+1) (k*acc)
  in loop 1 1
   
let _ = fact_incr 10
```
Exercice : evaluer à la main `fact_incr 10` et vérifier l'ordre des calculs obtenus.

## Définition : qu'est-ce qu'un appel terminal ?

Dans un code donné, un appel de fonction est dit **en position terminal** si le résultat de cet appel de fonction donne directement le résultat du code considéré, tel quel. Je répète, aucun calcul n'est possible sur
le résultat de cet appel avant qu'il ne serve de résultat du code considéré, pas d'arithmétique, pas d'analyse par `if` ou par `match`, pas de sous-appel de fonction, pas d'usage de constructeur de données, rien.

Normalement, la zone de code que l'on prend comme référence est toujours le corps d'une fonction. Dans ce cas, un appel de fonction est en position terminale dans ce corps si le résultat de cet appel est le résultat la fonction appelante. Voici un exemple :
```ocaml
let neg x = -x
let abs x = if x < 0 then neg x else x
```
L'appel de `neg` dans (le corps de) `abs` est terminal : la valeur calculée par `abs (-42)`, c'est exactement ce que renvoie `neg (-42)`. Il y a certes des choses *autour* de `neg x`, comme un test ou son `else`, mais ces opérations sont faites soit avant l'appel qui nous occupe, soit jamais en même temps (branche `else`).
Par contre, dans l'exemple suivant, il n'y a aucun appel en position terminale :
```ocaml
let mul_abs x y = abs x * abs y
```
En effet, il faut faire la multiplication entre les deux retours des appels à la fonction `abs`.

Notez bien qu'il s'agit d'une notion propre à chaque appel de fonction présent dans une définition donnée.
Une même fonction peut avoir certains de ses appels qui sont en position terminale, et d'autres qui ne le sont pas.

Exercice : dans le corps de `make_even` ci-dessous, deux des appels à `succ` sont en position terminale, mais pas le troisième. Voyez-vous lequel et pourquoi ?
```ocaml
let make_even n = if n mod 2 = 0 then succ (succ n) else succ n
```
## Definition : fonction récursive terminale

Maintenant, une fonction récursive est dite **terminale** si dans son corps, tous les appels récursifs sont en position terminale.

Cela se généralise également au cas des fonctions récursives mutuelles : elles seront terminales si toutes les fonctions participant à ce bloc mutuel sont toujours appelées en position terminale dans toutes les définitions de ce bloc mutuel.
## Exemples : les deux variantes de folds sur les listes

La dernière fois, nous avons vue une fonction `fold` sur les listes, qui généralise un grand nombre de situations communes sur les listes. Il s'agit en fait d'une variante de la fonction `List.fold_right` de la bibliothèque standard d'OCaml, que voici :
```ocaml
let rec fold_right f l accu = match l with
  | [] -> accu
  | x :: xs -> f x (fold_right f xs accu)
```
(Seule différence entre `fold` et `fold_right` : l'ordre des arguments `l` et `accu`).

Mais alors à quoi correspond alors la fonction `List.fold_left`? En voici sa définition :
```ocaml
let rec fold_left f accu = function
| [] -> accu
| x :: xs -> fold_left f (f accu x) xs
```
Notez le moment où la fonction `f` est utilisée :
  - dans `fold_right`, `f` est lancée sur le *résultat* de l'appel récursif (donc *après*).
  - dans `fold_left`, `f` est lancée *avant*, pour préparer l'argument de l'appel récursif qui va suivre.

Les equations correspondantes:
  - `fold_right f [x0;x1;x2] a = f x0 (f x1 (f x2 a))`
  - `fold_left f a [x0;x1;x2] = f (f (f a x0) x1) x2`

Quand on regarde l'équation de `fold_right`, on a l'impression que la tête de la liste est traitée en premier, mais ce n'est qu'une apparence : à cause des calculs mis en attente, c'est le fond de la liste qui est traité
par `f` en premier. C'est l'inverse pour `fold_left`.

Au fait, une remarque mnémotechnique, pour s'y retrouver entre ces deux variantes de `fold`: droite ou gauche désigne la place de l'accumulateur dans les arguments de `f` (par rapport à l'élément de liste à traiter), et aussi la place de l'accumulateur initial (par rapport à la liste à traiter) dans les arguments du `fold`.
Ainsi pour `fold_right` sur une `'a list`, la fonction `f` aura le type `'a->'b->'b` où `'b` est le type de l'accumulateur. D'où le type complet : `('a->'b->'b) -> 'a list -> 'b -> 'b`. Par contre accumulateur à gauche pour `fold_left`, nommé `'a` par OCaml, d'où le type `('a->'b->'a) -> 'a -> 'b list -> 'a`.

Quand cela s'y prette bien, il y aura un avantage à utiliser plutôt `fold_left`, qui est récursive terminale alors que `fold_right` ne l'est pas. On pourra donc utiliser `fold_left` sans crainte de `Stack Overflow`. Mais attention à l'effet de "renversement" déjà évoqué précédemment. Illustrons-le avec une simple reconstruction de la liste d'origine via nos `fold`: 
```ocaml
let rebuild_right l = List.fold_right (fun x acc -> x :: acc) l []
let rebuild_left l = List.fold_left (fun acc x -> x :: acc) [] l

let _ = rebuild_right [0;1;2]
let _ = rebuild_left [0;1;2]
```
## Quelques rappels sur le fonctionnement des appels de fonction

Pour comprendre pourquoi les appels en position terminale peuvent être optimisés, il faut se plonger dans l'implémentation en machine des appels de fonction. Dans les langages qui fournissent aux programmeurs la possibilité d'appeler une fonction, on trouve généralement une pile qui sert à se souvenir du contexte d'appel des fonctions pour pouvoir le restaurer lorsque l'appel de fonction se termine et que le contrôle retourne donc au point d'appel. Prenons un exemple en C :

```
int f (int x) {
   return 42 * x;
}

int g () {
   int x = f (3);
   int y = f (4);
   return f (x + y);
}

int main (int argc, char **argv) {
  g ();
}

```

Lorsque l'on exécute un appel à `g`, la première instruction requiert l'appel à la fonction `f`. Avant de faire cet appel, il faut pousser une __continuation__ : il s'agit d'une information qui indique que l'on souhaitera revenir à l'affectation de la variable `x` lorsque `f` aura fini son calcul. Ainsi, quand le code de `f` atteint l'instruction `return`, on sait où poursuivre le calcul en allant chercher la continuation dans la pile. 

Lors du second appel à `f`, c'est continuation est différente puisqu'elle doit représenter le code qui initialise la variable `y` : on doit encore la pousser sur la pile pour `f` puisse redonner correctement la main à `g`. 

Le dernier appel est particulier car sa continuation n'est pas une instruction de `g` mais la continuation de l'appelant de `g` : effectivement, il n'y a plus rien à faire dans `g`, si ce n'est redonner la main à son propre appelant (qui a lui aussi poussé une continuation sur la pile). Il ne sert à rien de pousser une continuation égale vers une instruction qui consiste à aller chercher la prochaine continuation sur la pile : il suffit de directement chercher cette continuation! Ainsi, le compilateur peut optimiser le dernier appel à `f` en évitant de pousser une quelconque continuation car `f` peut parfaitement utiliser comme la continuation poussée par l'appelant de `g` (ici `main`) pour poursuivre le calcul!

Pour être exact, il faut savoir qu'il n'y a pas que des continuations sur la pile, on y trouve aussi de l'espace mémoire pour y stocker les variables locales à la fonction et aussi la valeur de ses arguments. Ainsi, l'optimisation d'un appel en position terminale consiste à désallouer l'espace en pile utilisée par la fonction courante avant d'exécuter sa dernière instruction. Ce n'est pas un problème si cette dernière instruction est un appel de fonction car cela signifie en somme que l'on change l'appelant de cette fonction en l'appelant de son appelant.

## Optimisation de la récursion terminale

Pour résumer la section précédente : un appel terminal peut être optimisé par le compilateur car le compilateur peut insérer le code de désallocation de la pile d'appel _avant l'appel en position terminale_. Grâce à cette optimisation, la récursion ne consomme plus d'espace dans la pile et on peut donc faire des récursions de profondeur arbitraire sans aucune appréhension! Le compilateur va même jusqu'à transformer un appel récursif en position terminale en un saut inconditionnelle, c'est-à-dire une boucle!
```ocaml
let rec makelist acc n =
  if n = 0 then acc
  else makelist (n :: acc) (n - 1)

let large_list = makelist [] 100000
  
let sum xs = List.fold_right ( + ) xs 0
  
let oups = try sum large_list with Stack_overflow -> -1
  
let sum' xs = List.fold_left ( + ) 0 xs

let success = sum' large_list
```
Avec le compilateur OCaml principal (que ça soit en natif ou en bytecode), les fonctions récursives terminales sont ainsi optimisées, de même que les fonctions mutuelles récursives terminales. Lors d'une compilation d'OCaml vers javascript (comme ici pour Sketch.sh), l'optimisation des fonctions mutuelles récursives terminales n'est pas si évidente (les curieux pourront consulter <https://ocsigen.org/js_of_ocaml/3.7.0/manual/tailcall>), mais cela ne devrait pas trop mal se passer en pratique.

## Peut-on toujours écrire en récursif terminal ? Le doit-on ?

Tout d'abord, soyons clair. Sauf dans quelques rare cas, du code écrit en style terminal est très souvent plus complexe à écrire et à relire. Par exemple, présence d'arguments supplémentaires, les fameux "accumulateurs", ou les "continuations", qui reviennent grosso modo à faire "à la main" le travail de la pile d'appel du système. Et on s'éloigne souvent sensiblement des équations récursives "naturelles" de nos problèmes. Tout ceci rend ces codes récursifs terminaux nettement plus susceptibles de contenir des bugs. Vous êtes donc fortement encouragé à toujours commencer par écrire au plus simple une première version de vos fonctions récursives, même si elles ne sont pas terminales au début. Au minimum, cela pourra vous servir de point de comparaison pour la suite.

Tout dépend ensuite de la profondeur des appels récursifs sur les données typiques que vous aurez à manipuler. 
En fait, il est assez fréquent que cette profondeur reste modeste, auquel cas il n'est pas forcément indispensable de programmer en récursif terminal. Par exemple, avec des arbres bien équilibrés, une profondeur de 1000 vous permet de traiter des arbres ayant plus d'éléments qu'il n'y a d'atomes dans l'univers ! Et si vous devez chercher dans un annuaire contenant des millions d'entrées, une liste ne sera de toute façon pas le plus adaptée (mais des arbres, ou encore des tables de hachages, des tableaux, ...).

Par contre, si vous devez réellement effectuer des appels récursifs à profondeur importantes (> 10000 disons), il faut réécrire vos fonctions récursives pour les rendre terminales. Heureusement, on peut toujours le faire!

Pour cela, il y a essentiellement deux techniques : l'une est assez simple à comprendre mais ne s'applique pas toujours bien; l'autre s'applique toujours mais est nettement plus difficile à maîtriser.


### Le style par passage d'accumulateur

Retour à `fact` :
```ocaml
let rec fact n =
  if n = 0 then 1
  else n * fact (n - 1)
```
L'appel récursif `fact (n - 1)` n'est pas un appel en position terminale. Effectivement, une fois l'appel récursif à effectuer, il reste encore à le multiplier par `n`. Par chance, la multiplication est une opération commutative et associative : comme nous calculer une séquence de multiplications, on peut réécrire le programme pour effectuer cette multiplication tout de suite! Ainsi, nous n'allons pas calculer `1 * 2 * .. * n` mais `n * ... * 2 * 1` en suivant le même schéma récursif de calcul qui nous fait parcourir les valeurs entières de `n` à `1`. Pour réussir ce changement, il faut introduire un nouveau paramètre qui sert d'accumulateur.
```ocaml
let rec fact_loop n accu =
  if n = 0 then accu
  else fact_loop (n-1) (n * accu)
  
let fact n = fact_loop n 1 

```
Remarquez que notre nouvelle fonction `fact` est une fonction écrite avec une récursion Remarquez que notre nouvelle fonction `fact` est une fonction écrite avec une récursion terminale. Cette fonction sera d'ailleurs compilée comme le programme C suivant :
```
int fact (int n) {
    int accu = 1;
loop:
    if (n == 0) 
      goto exit_loop; 
    else {
      accu = n * accu;
      n = n - 1;
      goto loop;
    }
exit_loop:
    return accu;
}
```

Cette transformation est simple mais elle s'applique uniquement si on arrive à trouver un "accumulateur" à introduire pour effectuer le calcul de façon itérative (ou plusieurs accumulateurs).
Exercice : Sauriez-vous le faire pour les deux fonctions suivantes?
```ocaml
type 'a tree = Empty | Node of 'a tree * 'a * 'a tree

let rec elements = function
| Empty -> []
| Node (lhs, x, rhs) -> elements lhs @ x :: elements rhs

let rec height = function
| Empty -> 0
| Node (lhs, x, rhs) -> 1 + max (height lhs) (height rhs)
```
### Le style par passage de continuation

L'autre approche possible est le style par passage de continuation (CPS) déjà évoqué la semaine dernière.
Plus complexe, et l'utilisation de fonctions d'ordre supérieur a aussi un certain coût (on reparlera par la suite de l'implémentation par des "clôtures"). Mais au moins ce style permet de systématiquement obtenir un code récursif terminal. A suivre en Master. N'hésitez pas à retourner dans le précédent [sketch](https://sketch.sh/s/tDqsDWq7jwLNCLPX3mzky7/) et vérifiez que l'exemple de factorielle donnée alors est bien récursif terminal.

Exercice : Essayons maintenant d'écrire nos deux exemples sur les arbres binaires dans une forme CPS.


```ocaml
let rec height t =
  match t with
  | Empty -> 0
  | Node (lhs, _, rhs) -> 1 + max (height lhs) (height rhs)

```
## D'autres intérêts du récursif terminal

Outre les questions de Stack Overflow et de compilation rapide, le style terminal peut aussi présenter d'autres intérêts. Ce style de stocker naturellement certaines données intermédiaires sous forme d'arguments, et/ou de reporter certains traitement à la fin de la partie récursive. Cela peut être très propice à résoudre efficacement certains problèmes déjà vus ensemble, sans utiliser des structures supplémentaires commes des paires.

#### Fibonacci sans explosition combinatoire

Dans l'exemple bien connu des nombres de Fibonacci, pour éviter de se retrouver rapidement à calculer un nombre exponentiel de sous-appels récursifs, l'idée clé est de calculer *deux* nombres de Fibonacci successifs. Cela peut se faire dans un style direct à condition d'utiliser des paires comme résultats:

```ocaml
let rec fibpair n =
 if n = 0 then (1,1)
 else
   let (a,b) = fibpair (n-1)
   in (b,a+b)
   
let fib n = fst (fibpair n)

let _ = fib 10
```
Même chose, mais maintenant dans un style terminal, avec deux accumulateurs pour les deux derniers nombres de Fibonacci vu jusqu'à maintenant. Notez que l'entier `k` est ici le nombre de tours à finir, pas l'indice des nombres de Fibonacci actuels.
```ocaml
let rec fibtail k a b =
  if k = 0 then a
  else fibtail (k-1) b (a+b)
 
let fib n = fibtail n 1 1
 
let _ = fib 10 
```
On peut d'ailleurs noter un lien évident entre les accumulateurs en récursif terminal et variables mutables dans une boucle d'un programme impérative.

#### Average en un unique passage

Reprenons la fonction `average` sur des listes d'entiers. En TP il était demandé comment écrire cette fonction en faisant un seul passage dans la liste. Une première réponse, à base de paires en réponse:

```ocaml
let rec sum_and_length = function
  | [] -> 0,0
  | x::l ->
    let (sum,len) = sum_and_length l in
    (x+sum,1+len)

let average = function
  | [] -> failwith "vide"
  | l -> let (sum,len) = sum_and_length l in sum/len    
  
```
Version récursive terminale, sans paires :
```ocaml
let rec average_loop sum len = function
   | [] -> sum/len
   | x::l -> average_loop (x+sum) (1+len) l
   
let average = function
  | [] -> failwith "vide"
  | l -> average_loop 0 0 l
  
```

Les mécanismes impurs d'OCaml
==============================

[Lien Sketch](https://sketch.sh/s/odCwxaMbe7e5NxgNAboW9e)

Pierre Letouzey, d'après Yann Régis-Gianas.

Après le cours sur les entrées/sorties et le graphisme, nous continuons avec les structures permettant la programmation "impérative" en OCaml (on dit également "déclarative"). Ces structures sont dites "impures" car on s'éloigne ici fortement de la programmation fonctionnelle pure. Comme la semaine dernière, on va procéder par "effet de bord", cette fois-ci en modifiant "en place" certaines zones mémoires. Certaines propriétés fondamentales des structures fonctionnelles pures ne sont plus garanties. Par exemple:

  - Une liste OCaml est "immutable" (une fois créée, son contenu ne change plus), un tableau OCaml n'est certainement pas immutable.
  - Une concaténation `@` sur les listes partage en mémoire la liste de droite, un `Array.append` ne pourra pas faire de même.
  - Une fonction pure recevant les mêmes données pures donnera toujours le même résultat.

Evidemment, dans ce cours de programmation fonctionnelle, nous vous incitons à utiliser les structures impératives avec parcimonie, car elles peuvent mener à de nombreux effets (de bord) pervers. Mais elles sont à connaître également, et peuvent parfois être la bonne réponse à certains problèmes (persistance d'un état, efficacité parfois, ...).

## Références

Une référence d'OCaml est un espace mémoire modifiable vers une valeur d'un type donné. On peut voir les références comme des tableaux de taille 1. Cela permet aussi de retrouver la notion de variable modifiable
disponible dans d'autres langages de programmation. 

Typage : si `u` est un type OCaml, une référence contenant une valeur de type `u` aura le type `u ref`.

Ces références sont naturellement munies de trois opérations :

- L'**allocation** `ref e`. Ici `e` est une expression qui donne la valeur initiale de l'espace mémoire initialisé. Si `e` a le type `u`, alors `ref e` nous donne un `u ref`. Autement dit : `ref : 'a -> 'a ref`.

- La **lecture** `!e`. Si `e` est une expression de type `u ref`, alors `!e` donne le contenu de cette référence, donc une valeur de type `u`. En bref : `(!) : 'a ref -> 'a`.
 
- l'**écriture** `e1 := e2`. Ici `e1` est une expression de type `u ref` et `e2` une expression de type `u` dont la valeur remplace la valeur courante de la référence obtenue en évaluant `e1`. Cette écriture est une action (type final `unit`). Bref, `(:=) : 'a ref -> 'a -> unit`.

Voici quelques exemples.
```ocaml
(* Les types des operations *)
ref;;
(!);;
(:=);;

(* Un exemple *)
let x = ref 0;;  (* notez l'affichage avec des {contents= ..}, on y reviendra *)

!x;;

x := 37;;

!x;;

(* Une fonction avec effet de bord dans la mémoire *)
let plusun () = x := !x+1; !x;;

(* Rappel `a;b` signifie `let _ = a in b` bref faire le calcul de a
   puis oublier son résultat (sans doute ()) puis calculer b *)

(* Cette fonction n'est clairement pas purement fonctionnelle : 
   Deux usages successifs donnent deux réponses différentes *)

let _ = plusun ();;
let _ = plusun ();;

(* Une expression riche devant un := *)
let luke = ref 0;;
let yoda = ref 1;;

(if Random.int 2 = 0 then luke else yoda) := -1;;

luke;;

!luke;;

(* deux fonctions prédéfinies sur les références d'entiers *)

incr luke;;
!luke;;
decr luke;;
!luke;;
```
Notez bien la différence entre la valeur d'une référence (essentiellement, son emplacement en mémoire) et la valeur de ce qu'elle contient.


A partir de l'exemple précédent de `plusun`, on peut obtenir l'exemple classique d'une fonction générant un entier différent à chaque fois (on appelle cela habituellement un "gensym", pour générateur de symbole). 
```ocaml
let gensym =
  let r = ref 0 in   (* en mettant la reférence ici, on la rend inaccessible de l'exterieur *)
  fun () -> r := !r+1; !r;;
  
gensym ();;
gensym ();;
gensym ();;

(* Idem, mais avec une possibilité de remise-à-zero *)
let gensym, reset =
  let r = ref 0 in
  (fun () -> r := !r+1; !r), (fun () -> r := 0);;

gensym ();;
gensym ();;
reset ();;
gensym ();;
gensym ();;

```
## Champs modifiables

Les enregistrements ("records") peuvent avoir des champs modifiables. Les références sont un cas particulier de tels enregistrements : ce sont des enregistrements avec un unique champ `contents` modifiable, voir la façon donc le toplevel OCaml affiche une référence.

Pour déclarer un champ modifiable, il suffit de préfixer sa déclaration par le mot-clé `mutable`.

La création d'enregistrements se fait toujours selon la même syntaxe. Et l'accès à un champ modifiable se fait comme pour les autres champs : `e.label`.

Pour modifier un tel champ, on utilise la syntaxe `e1.label <- e2` où `e1` s'évalue en un enregistrement et `e2` s'évalue en une valeur du même type que le champ `label`.

D'ailleurs, on peut ainsi retrouver la définition de `(:=)`, à savoir `let (:=) r e = r.contents <- e`. 

Exemple d'utilisation : des structures "à pointeurs" modifiables en place.
```ocaml
type 'a node = {
           value : 'a;
   mutable next  : 'a node option;
}

let node x = { value = x; next = None };;

let connect n1 n2 = n1.next <- Some n2 ;;

let n1 = node "foo";;

let n2 = node "bar";;

connect n1 n2;;

n1;;
```
## Tableaux

Bien entendu, OCaml propose des tableaux standards similaires à ceux que l'on peut trouver en Java : on peut allouer un tableau de n valeurs d'un type donné, lire une case de ce tableau et modifier la valeur d'une case d'un tableau. Si on lit ou écrit en dehors des bornes d'un tableau alors une exception est levée. On peut voir
ces tableaux comme une batterie de références regroupées ensemble.

Type : `'a array`. Comme pour les listes, toutes les valeurs d'un tableau partagent le même type.

Syntaxe concrète des tableaux : `[| x ; y ; ... |]`. Comme pour les listes, attention à bien utiliser des points-virgules et non des virgules, sinon vous obtenez un tableau de taille 1 contenant des n-uplets : `[|x,y|]` c'est `[|(x,y)|]`. 

Pour créer des tableaux, utiliser soit la syntaxe concrète précédente pour des petits tableaux, soit sinon la fonction `Array.make n e` où `n` est la taille souhaitée et `e` donnera la valeur initialement placée dans toutes les cases du tableau. Voir également `Array.init : int -> (int -> 'a) -> 'a array` pour tabuler une fonction : `Array.init n f = [| f 0; f 1; ... f (n-1) |]`.

Pour lire une case de tableau : `e1.(e2)` ou bien la forme longue `Array.get e1 e2`. Ici `e1` est un tableau et `e2` un entier. Autrement dit : `Array.get : 'a array -> int -> 'a`.

Pour écrire dans une case de tableau : `e1.(e2) <- e3` ou bien la forme longue `Array.set e1 e2 e3`. Ici `e1` est un tableau, `e2` un entier et `e3` une nouvelle valeur à écrire dans `e1` à l'emplacement `e2`. Autrement dit : `Array.set : 'a array -> int -> 'a -> unit`.

Pas d'évolutivité de la taille des tableaux : si l'on souhaite raccourcir ou ralonger, on recrée un tableau et on recopie tout dans le nouveau tableau.

Fonctions pratiques sur les tableaux : `Array.map`, `Array.iter`, `Array.iteri` et quelques autres, voir la documentation du module `Array`. 

```ocaml
let t = [| 1; 2; 3 |];;
t.(0);;
t.(1) <- 3;;
t;;

Array.iter (Printf.printf "%d\n") t;;

(* Attention aux matrices (tableaux de tableaux) définies de façon "aliasée" *)
let t = Array.make 4 (Array.make 4 0);;
t.(0).(1) <- 1;;
t;; (* des 1 dans toute la colonnes ! *)

(* Solution : création d'un nouveau tableau différent à chaque ligne, p.ex : *)
let t = Array.init 4 (fun _ -> Array.make 4 0);;
t.(0).(1) <- 1;;
t;;
(* Ou directement via la fonction dédiée à ce cas *)
let t = Array.make_matrix 4 4 0;;
t.(0).(1) <- 1;;
t;;


(* Exemple d'opérateurs plus complexes *)
let sum = Array.fold_left (fun e x -> if x mod 2 = 0 then e + x else e) 0
let s = sum [| 1;2;3;4 |];;

let myfold f init a =
  let rec loop accu i =
    if i < Array.length a then
       loop (f accu a.(i)) (i + 1)
    else
       accu
  in
  loop init 0
```
## Structure de données modifiables

Bien entendu, les mécanismes présentés plus haut permettent de représenter des structures de données à la manière des langages impératifs standards (listes chaînées, arbres modifiables en place, etc).

Dans cette section, on décrit par exemple une structure de données contenant une valeur fonctionnelle équipée d'un cache modifiable. Cette structure de données permet de réaliser une forme de *calcul paresseux*, c'est-à-dire un calcul qui ne se déclenche que lorsque la valeur qu'il produit est vraiment nécessaire et qui se souvient de cette valeur pour éviter de la recalculer plus tard.
```ocaml
type 'a paresse =
{
  mutable value : 'a option;
  computation : unit -> 'a;
}

let make_lazy computation = { value = None; computation }

let eval paresse =
   match paresse.value with
   | None ->
     let v = paresse.computation () in
     paresse.value <- Some v;
     v
   | Some v ->
     v
     
let rec fibo n =
   if n <= 1 then 1
   else fibo (n - 1) + fibo (n - 2);;
(*
let fibo42 = make_lazy (fun () -> fibo 42);; (* un calcul long, mais pour plus tard *)
let r = eval fibo42;; (* maintenant on le lance *)
let r' = eval fibo42;; (* ce deuxième accès est gratuit *)
*)
```
## Structures de contrôle de la programmation impérative

OCaml propose les deux structures de contrôle standard de la programmation itérative : les boucles bornées et les boucles potentiellement non bornées:

  - `for i = a to b do ... done`
  - `while ... do ... done`
   
En voici quelques exemples.
```ocaml
let fact n =
  let r = ref 1 in
  let i = ref 1 in 
  while !i <= n do
    r := !r * !i;
    i := !i - 1;
  done;
  !r
  
let fact_for n =
  let r = ref 1 in
  for i = 1 to n do
    r := i * !r;
  done;
  !r;;
  

```
## Code impératif et conditionnelles

Nous avons déjà vu ensemble les conditionnelles `if`, a priori rien de nouveau. Mais en présence de code impératif, attention à deux points délicats.

Jusqu'à maintenant, les conditionnelles `if` s'utilisaient toujours de façon complètes : `if ... then ... else ...`. Ne pas mettre d'alternative `else` était une faute. Mais il existe bien une variante de `if` sans le `else`. Cette variante s'utilise lorsque la première branche est de type `unit`, donc typiquement une action. A ce moment-là, l'absence d'une branche `else` équivaut à une branche `else ()` disant de ne rien faire dans l'autre cas. Cette possibilité d'un `if ... then ...` sans le `else` est à l'origine de messages d'erreur obscures lorsqu'on oublie le `else` avec une première branche de type autre que `unit`. 
```ocaml
if Random.int 2 = 0 then print_string "gagné";;
```
Attention également aux priorités entre `;` et `then` ou `else`. C'est une source continuelle de bugs.

```ocaml
if false then
  print_string "a"; (* cette ligne est dans le then *)
print_string "b";; (* cette ligne ne l'est plus ! *)
  
```
Ici l'indentation est correcte, et montre que la dernière ligne sera toujours effectuée. Mais c'est très facile de se tromper. 
Toute branche de conditionnelle composée de plusieurs morceaux par `;` doit donc être soit parenthésée, soit regroupée via `begin ... end` (ce qui revient au même).
```ocaml
if false then begin
  print_string "a";
  print_string "b"
end;;
```
## Fonctions observationnellement pures

Les fonctions pures ont de nombreux avantages dont nous avons déjà parlés. La [transparence référentielle](https://fr.wikipedia.org/wiki/Transparence_r%C3%A9f%C3%A9rentielle) garantit que l'on peut raisonner sur un appel de fonction en le remplaçant par un calcul équivalent, ce qui facilite une forme de raisonnement local au code de l'appelant.

### Memoïsation

Une conséquence intéressante du fait qu'une fonction ne dépend que de ses entrées : si on a déjà calculé `f x` pour une certaine valeur de `x` alors on sait que cette valeur ne changera jamais. Si ce calcul était long et si on sait que l'on va le faire plusieurs fois, on peut optimiser le programme en maintenant un cache qui se souvient de ces résultats déjà calculés. Ce procédé s'appelle la [mémoïsation](https://fr.wikipedia.org/wiki/M%C3%A9mo%C3%AFsation) et nous allons voir comment implémenter une fois pour toute ce procédé à l'aide d'une fonction d'ordre supérieur.
```ocaml
let memo f =
  let table = Hashtbl.create 13 in
  fun x -> try Hashtbl.find table x with Not_found ->
    let y = f x in
    Hashtbl.add table x y;
    y
    
 
let super_long_computation x =
  Printf.printf "Ouhla la c'est très long de calculer %d!\n" x;
  x * x
  
let optimized_function = memo super_long_computation;;

let r1 = optimized_function 1;;
let r2 = optimized_function 2;;
let r3 = optimized_function 1;;
let r4 = optimized_function 2;;

let fibo fibo_rec n =
  match n with
  | 0 | 1 -> 1
  | n -> fibo_rec (n - 2) + fibo_rec (n - 1)
  
let rec fibo_std n = fibo fibo_std n 

let memo_rec frec =
  let table = Hashtbl.create 13 in
  let rec self x =
      try Hashtbl.find table x with Not_found ->
      let y = frec self x in
      Hashtbl.add table x y;
      y
   in self

let fibo_fast = memo_rec fibo

(* let fibo40 = fibo_std 40;; *)

let fibo40' = fibo_fast 40;;

```
## Exercice

savoir définir soi-même `List.iter`, `Array.iter`, `Array.append`, `Array.map`, et d'autres fonctions du même genre, soit en utilisant `for` soit en s'en passant.

Entrées et sorties en OCaml
===========================

## Les canaux de communication

- Les entrées et sorties d'un programme OCaml utilisent des *canaux de communications* (sauf cas spéciaux tels que le graphisme).

- Tout canal est soit un canal d'entrée, soit un canal de sortie, et jamais les deux à la fois.

- Certains de ces canaux sont créés par défaut (voir ci-dessous), d'autres peuvent être ouverts et fermés par le programme (voir plus tard).

## Les canaux de communication qui existent toujours

Tout processus Unix a au moins trois canaux de communication (voir le cours de *Systèmes*) :

- `stdin` : entrée « normale » du processus, normalement associée au clavier. Peut aussi être une redirection d'un tuyau ou d'un fichier.

- `stdout` : sortie « normale » du processus, normalement associée à l'écran. Peut aussi être redirigée vers un tuyau ou un fichier.

- `stderr` : sortie pour les messages d'erreur. Normalement confondue avec stdout et associée à l'écran, mais peut aussi être redirigée.

## Sortie vers `stdout`

- Fonctions de sortie vers `stdout` pour les types de base

```ocaml
print_int;;
print_float;;
print_string;;
print_char;;
(* usage *)
print_string "toto";;
print_newline();;
(* ou directement *)
print_string "toto\n";;
(* on peut aussi enchainer des action avec ; *)
print_string "toto"; print_newline ();;
```

- La sortie vers `stdout` n'est pas effectuée tout de suite : il y a un tampon (buffer). Un saut de ligne (p.ex. via `print_newline`) force la sortie du contenu du tampon.

- Pour des valeurs dont le type n'est pas un type de base (p.ex. listes, types sommes, etc), c'est à nous d'écrire des fonctions d'affichage vers `stdout`. Même si en fait l'interpréteur OCaml sait en afficher la plupart quand il nous présente un résultat !

## Le module `Printf`

- Ce module définit une fonction `printf` qui prend en premier argument une chaîne qui décrit le format, puis autant d'arguments que demandé par le format.

- Dans le format, `%i` dénote un entier, `%s` une chaîne de caractères, etc.

- Il y a des variantes pour écrire sur un canal de sortie quelconque ou dans une chaîne de caractères.

- Cette fonction "triche" au niveau typage (car le nombre et les types des arguments *dépendent* du premier argument).

- Similaire à `printf` dans C, C++, Java ...

```ocaml
Printf.printf "La longueur de %s est %i\n" "toto" 4;;
Printf.printf;; (* type dépendent, sera vu en M1 *)
Printf.printf "La longueur de %s est %i\n";; (* string -> int -> unit *)
```

## Sortie vers `stderr`

- Il y a des fonctions analogues pour la sortie vers `stderr`.

- La distinction entre `stdout` et `stderr` est importante : un utilisateur peut avoir besoin de séparer la sortie normale des messages d'erreur.

- Fonctions `prerr_int`, etc (voir le manuel).

## Les types des canaux de communication

- Deux types prédéfinis en OCaml pour les canaux de communication: `in_channel` pour les canaux d'entrée et `out_channel` pour les canaux de sortie.
 
- Bien sûr, `stdin` est de type `in_channel` et `stdout` et `stderr` sont de type `out_channel`.

- Des fonctions spécialisées permettent de créer un nouveau canal en l'associant à un fichier, à une connexion réseau, etc.


## Ouvrir et fermer un fichier pour l'écriture

- Fonction `open_out` pour ouvrir un fichier, de type `string -> out_channel`. Si le fichier n'existe pas il est créé.

- Peut lever une exception `Sys_error`, par exemple quand on a pas les droits nécessaires pour créer ou ouvrir le fichier.

- Fonction `close_out` du type `out_channel -> unit` pour fermer un fichier.

```ocaml
let c = open_out "myfile";;
close_out c;;

(* erreur d'execution : Permission denied *)
let c = open_out "/blabla";;
```

## Écrire vers un canal

- Fonctions `output_string`, `output_char` pour écrire dans un canal de sortie. Le premier argument est le canal.

- La fonction `print_string` vue auparavant est équivalente à `output_string stdout`.

- Il n'y a pas de `output_int`, en revanche `Printf.fprintf` est une variante de `printf` écrivant dans un canal.

- La sortie vers un canal est tamponnée.

- Fonctions `flush` pour vider un tampon, et `flush_all` pour les vider tous.

```ocaml
let rec print_list canal = function
  | [] -> ()
  | h::r ->
      output_string canal (string_of_int h);
      output_char canal '\n';
      print_list canal r

let c = open_out "myfile" in
print_list c [3; 5; 17; 42; 256];
close_out c;;

(* erreur d'exécution *)
let c = open_out "myfile" in
close_out c;
output_string c "toto";; (* écriture après fermeture *)

(* erreur de typage *)
let c = open_in "myfile" in
output_string c "coocoo"; (* écriture dans un in_channel *)
close_in c;;
```

## Entrée par `stdin`

- La fonction `read_line` attend sur `stdin` une ligne terminée par retour-chariot, et envoie comme résultat le contenu de cette ligne (sous forme d'un string) mais *sans* le retour-chariot.

- Il y a également `read_int` et `read_float`.

- Le module `Scanf` permet de lire des lignes dans un format précis (analogue à Printf, mais d'usage délicat).

- Pour des lectures plus complexes, il existe des outils dédiés tels que `ocamllex` et `ocamlyacc` ou `menhir` ... et des cours entiers pour les apprendre (Compilation).

```ocaml
let rec read_and_add x =
  let y = read_int () in
  if y = 0 then x else read_and_add (x+y)

let _ = read_and_add 0
```

## Ouvrir et fermer un fichier pour la lecture

- Fonction `open_in`. Lève l'exception `Sys_error` si le fichier ne peut pas être ouvert (par exemple parce qu'il n'existe pas).

- Fonction `close_in` pour fermer le canal.

- Fonction `input_line` pour lire une ligne complète. Lève l'exception `End_of_file` quand on est à la fin du fichier.

```ocaml
let rec copy_lines ci co =
  try
    let x = input_line ci in
    output_string co x;
    output_string co "\n";
    copy_lines ci co
  with End_of_file -> ()

let copy infile outfile =
  let ci = open_in infile in
  let co = open_out outfile in
  copy_lines ci co;
  close_in ci;
  close_out co
```

## Entrées/sorties et effet de bord

- Les opérations de sortie sont l'exemple même *d'effets de bord*:

   - Leur type résultat `unit` n'indique pas l'action faite en chemin
   - L'ordre d'évaluation des opérations de sorties importe !

- Les opérations d'entrée sont aussi des effets de bord : elles font avancer la tête de lecture. Faire deux lectures de suite ne donnera sans doute pas le même résultat!

- Dans le cas des fonctions récursives, s'assurer que la tête de lecture est avancée avant d'entrer dans la récurrence !

```ocaml
(* Ce code déclenche une boucle infinie ! *)
let rec bad_size ci =
  try
    String.length (input_line ci) + bad_size ci
  with End_of_file -> 0

let c = open_in "myfile" in bad_size c

(* Version correcte *)
let rec size ci =
  try
    let line_size = String.length (input_line ci)
    in line_size + size ci
  with End_of_file -> 0

let c = open_in "myfile" in size c
```

## Attention à l'ordre d'évaluation

- Le souci précédent : dans `bad_size` la droite du `+` est évalué *avant* la gauche.

- L'ordre d'évaluation des arguments dans un appel de fonction n'est officiellement pas spécifié en OCaml.

- En fait, il calcule les arguments de la droite vers la gauche!

- Sauf les opérateurs booléens `&&` et `||`, qui évaluent de gauche vers droite (et peuvent parfois ignorer l'argument de droite!)

- Utiliser des `let ... in` pour forcer l'ordre d'évaluation.

- Exercice : avec les fonctions vues aujourd'hui, comment tester en pratique cet ordre d'évaluation des arguments ?

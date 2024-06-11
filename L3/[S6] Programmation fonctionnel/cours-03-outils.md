L'écosystème d'outils et de bibliothèques OCaml pour développer des logiciel
============================================================================

Un certain nombre d'outils et bibliothèques utiles en OCaml.
Liste loin d'être exhaustive. Ceci n'est pas non plus un manuel complet
de ces outils, voir leurs documentations respectives ou des tutoriels plus
complets.

## OCaml en ligne

Pour l'instant, on a utilisé OCaml via des sites en ligne, et en mode "phrase par phrase".

   - LearnOCaml et son instance ici <http://pf5.ddns.net>
   - Accessible de partout, le "toplevel" <http://try.ocamlpro.com>
   - Documents avec fragments exécutables de code : <http://sketch.sh>

C'est très bien pour commencer, mais ces sites sont dépendants d'un
fournisseur "bénévole", et ne sont donc pas pérennes.

Note technique : derrière le capot, ces sites utilise une compilation d'OCaml vers javascript : `js_of_ocaml`.

## Le toplevel OCaml "historique"

C'est le programme nommé `ocaml` ou `ocaml.exe`, selon les systèmes.
Il doit être lancé dans un terminal (alias une console, un shell).

Aussi appelé *boucle interactive* OCaml, ou *REPL* (read-eval-print loop).
C'est le mode phrase par phrase, que les outils en lignes précédents simulent.
On parle aussi d'interpréteur OCaml, même si le terme n'est pas très juste :
en fait, on y *compile* quand même chaque phrase en bytecode avant de la lancer.

#### Une session

```sh
$ ocaml
        OCaml version 4.07.0

# 1+1;;
- : int = 2
```

Note : le `$` ici symbolise le *prompt* de mon shell, dans lequel je lance `ocaml`.
Le `#` est le prompt affiché par OCaml. Ne pas taper ni `$` ni `#`, sauf ci-dessous
dans le cas de `#use` et quelques autres. Pour sortir : `Ctrl+d`. Pour arrêter une
évaluation qui boucle : `Ctrl+c`.

#### Remarques complémentaires

 - Ici `;;` obligatoire pour démarrer l'évaluation d'une phrase.
 - Très basique côté fonctionnalités : pas d'édition de la ligne en cours, pas d'historique, etc.
 - Un peu mieux : `rlwrap ocaml`, où le programme `rlwrap` donne historique et édition de ligne.
 - Quelques commandes spécifiques au toplevel. Par exemple:
    - `#use ...` : permet de charger tout un fichier source OCaml, comme si on l'avait tapé
    - `#load ...` : permet de charger un fichier binaire bytecode OCaml (voir plus bas)
    - `#trace ...` : affiche un message à chaque appel d'une fonction
 - Ne pas mettre ces commandes dans un fichier OCaml fait pour être compilé (voir plus bas)
 - Il existe aussi des toplevel plus modernes, voir par exemple `utop`

Bref : bien pour quelques essais, mais **éviter de s'en servir directement** pour plus que quelques lignes.

## Editeurs de code pour OCaml

C'est le mode de programmation OCaml recommandé en local sur votre machine.
L'éditeur communiquera avec le toplevel OCaml ou plus tard avec le compilateur.

La plupart des éditeurs de code (eclipse, vscode, ...) supportent OCaml, eventuellement via des plugins.
Mais le meilleur support d'OCaml reste encore `emacs` avec son extension `tuareg-mode`.
Eventuellement, voir aussi l'extension `merlin` (typage en direct, etc).

## Emacs et OCaml

Si emacs et tuareg sont bien installés :

 - Ouvrir ou créer dans emacs un fichier dont l'extension est `.ml`
 - Un menu `tuareg` doit apparaître en haut à droite
 - Ecrire une phrase ocaml dans le fichier, se placer dessus, puis `Ctrl-x-e`
 - Cela doit proposer en bas un choix de toplevel OCaml, acceptez `ocaml`
 - Un deuxième "buffer" (fenêtre emacs) s'ouvre avec la réponse d'OCaml à votre phrase.
 - Voir le menu `tuareg` pour d'autres possibilités (p.ex. évaluer tout le fichier, etc).
 - Plus tard : compiler depuis emacs donne une localisation directe des erreurs.

## Vers des programmes réalistes

   - OCaml structure ses programmes en modules.
   - La bibliothèque standard d’OCaml est assez minimaliste.
   - Elle est documentée dans un manuel officiel, cf <http://ocaml.org/>
   - Des bibliothèques externes sont disponibles grâce à ocamlfind et dune.
   - Des outils de développement sont disponibles : ocamldebug, ocamldoc, ...


## Les modules OCaml définis par des fichiers

Fichiers d’implémentation et d’interface pour un petit exemple:

 - Le fichier [sayHello.ml](2019/cours-02/simple/sayHello.ml) définit le module SayHello.
 - Le fichier [sayHello.mli](2019/cours-02/simple/sayHello.mli) définit l’interface du module SayHello.
 - Le module [Hello](2019/cours-02/simple/hello.ml) utilise le module SayHello.

## Compilation en bytecode (code-octet en bon français)

Pour compiler ce petit programme, il faut :

```sh
$ ocamlc -c sayHello.ml # Compiler l'implémentation de SayHello.
  # Le fichier sayHello.cmo est produit.
$ ocamlc -c sayHello.mli # Compiler l'interface de SayHello.
  # Le fichier sayHello.cmi est produit.
$ ocamlc -c hello.ml     # Compiler l'implémentation de Hello.
  # Les fichiers hello.cmo et hello.cmi sont produits.
  # (Quand le fichier .mli est absent, une interface est
  # automatiquement produite et elle dévoile tout!)
$ ocamlc -o hello sayHello.cmo hello.cmo
  # Pour lier les modules en un exécutable.
```

Extensions:

  - .ml : fichier source OCaml
  - .mli : interface OCaml
  - .cmo : implémentation de module compilé en bytecode
  - .cmi : interface de module compilé.

Ici, on peut obtenir le même programme en une ligne :
 `ocamlc -o hello sayHello.mli sayHello.ml hello.ml`


## Compilation en code natif

Sur les systèmes majeurs, OCaml propose aussi `ocamlopt`, un
compilateur vers le code natif (i.e. l'assembleur) de votre machine.
C'est environ 10 fois plus rapide que le bytecode mais non portable.

Pour compiler notre petit programme, il faut :

```sh
$ ocamlopt -c sayHello.ml # Compiler l'implémentation de SayHello.
  # Le fichier sayHello.cmx est produit.
$ ocamlopt -c sayHello.mli # Compiler l'interface de SayHello.
  # Le fichier sayHello.cmi est produit.
$ ocamlopt -c hello.ml     # Compiler l'implémentation de Hello.
  # Les fichiers hello.cmx et hello.cmi sont produits.
  # (Quand le fichier .mli est absent, une interface est
  # automatiquement produite et elle dévoile tout!)
$ ocamlopt -o hello sayHello.cmx hello.cmx
  # Pour lier les modules en un exécutable.
```

Extensions:

  - .cmx : implémentation de module compilé en natif.
  - .cmi : interface de module compilé.

Ici aussi, ocamlopt peut faire beaucoup de choses en une même ligne:
 `ocamlopt -o hello sayHello.mli sayHello.ml hello.ml`

## Comment utiliser un module?

   - `SayHello.say_it ()` fait appel à la fonction say_it du module SayHello.
   - Pour donner accès aux définitions d’un module sans avoir besoin de préciser
     le nom du module à chaque accès :

```ocaml
open SayHello
let main = say_it ()
```

## Comment se déroule l'exécution d'un programme OCaml ?

  - Toutes les phrases des différents modules sont exécutées (dans l'ordre du "link").
  - Contrairement au toplevel, aucun affichage par défaut : la plupart des phrases
    sont silencieuses (définitions de fonctions, calcul de valeurs "pures", etc).
  - A vous de communiquer, via des effets de bord !
  - Voir par exemple `print_string`, `read_line`, `Printf.printf`, lectures/écritures de fichiers, etc.
  - Accès à la ligne de commande via le tableau `Sys.argv`.
  - Usage (mais non obligatoire) : un unique point d'entrée lançant le déroulement
    du programme, comme le `main` de `hello.ml` ci-dessus.

## Bibliothèque standard d’OCaml

  - Un module nommé `Pervasives` (ou `Stdlib` depuis peu) est
    accessible automatiquement par tout programme OCaml. Il fournit
    des exceptions standards, des opérateurs de comparaisons et
    booléens, de compositions, de calculs arithmétiques, de
    conversions, d’entrées/sorties, ...
  
    <https://ocaml.org/releases/4.11/htmlman/core.html>


  - La bibliothèque standard fournit les utilitaires et les structures
    de données classiques: manipulation de chaînes de caractères ;
    dictionnaires ; ensembles ; structures de séquence, liste, file et
    de pile ; interactions via le terminal ; prise en charge des
    arguments de la ligne de commande ; primitives cryptographiques
    ...
  
    <https://ocaml.org/releases/4.11/htmlman/stdlib.html>

  - Cette bibliothèque standard est complétée par une bibliothèque pour la
    programmation système, nommé Unix. On y trouve les appels systèmes
    standards de POSIX. Ces appels sont en grande partie émulées sous Windows.

    <https://ocaml.org/releases/4.11/htmlman/libunix.html>
               
  - D’autres bibliothèques importantes sont inclues dans la distribution:
    - `Str` : pour utiliser des expressions régulières.
    - `Threads` : pour utiliser des fils d’exécution (système).
    - `Bigarray` : pour travailler sur de grandes quantités de données.
    - `Num` : pour faire du calcul arithmétique en précision arbitraire
      (récemment sorti de la bibliothèque standard, voir plutôt `Zarith`)


## Au fait, qu'est-ce qu'une bibliothèque ?

   - Les bibliothèques sont des ensembles de modules.
   - Elles viennent sous trois formats distincts:
     - Bibliothèques statiques bytecode : .cma
     - Bibliothèques statiques natives : .cmxa
     - Bibliothèques dynamiques natives : .cmxs
   - Pour utiliser une bibliothèque, il suffit de préciser le fichier de la bibliothèque lors de l’édition des liens :
     `ocamlopt -o mywebserver unix.cma webserver.cmo`

   - Le fichier `unix.cma` est situé dans le répertoire standard lib/ocaml
      installé par la distribution. Pour connaître la configuration de votre
      compilateur OCaml: `ocamlc -config`

   - Si la bibliothèque ne se trouve pas dans un emplacement standard, on peut
      spécifier cette emplacement via l’option `-I`. Exemple hypothétique:
      `ocamlc -o myvideogame -I mylib renderengine.cma mainlogic.cmo`

## Les paquets OCaml via linux

Sur un linux récent (p.ex. Ubuntu ou Debian), un grand nombre d'outils OCaml
et de bibliothèques usuelles sont déjà installables via les "paquets"
logiciel de votre distribution. Par exemple :

```sh
sudo apt install tuareg-mode ocaml-findlib dune libzarith-ocaml-dev
```

Tant que cela satisfait vos besoins, s'en servir en priorité (meilleur
intégration avec le reste de votre système, mises-à-jour automatiques, etc).

## Les paquets OCaml via opam

Sinon, l'outil `opam` permet également d'installer des "paquets" OCaml
*par dessus* votre système.

Ses atouts:

  - il n'est pas limité à linux
  - disponibilités plus rapide des versions d'OCaml et des paquets, y compris de paquets plus rares
  - possibilité d'avoir plusieurs versions d'OCaml simultanément (cf `opam switch`)

Ses limites:

  - gourmand en espace disque
  - des installations et mises-à-jour encore assez "fragiles" (plantages de temps en temps)
  - besoin d'une configuration pour s'en servir, du style `eval $(opam env)`

## L’outil ocamlfind

L’outil standard ocamlfind permet d’associer des informations à un nom de
bibliothèque. Cette fonctionnalité permet de simplifier les commandes de
compilation:

```sh
$ ocamlfind query re # Quel est l'emplacement de la bibliothèque 're' ?
  /home/letouzey/.opam/4.07/lib/re
```

Mieux, ocamlfind sait réécrire pour vous les lignes de commande de compilation:
Ainsi, pour rajouter les bonnes options pour inclure la bibliothèque `re` dans l’exécutable hello.

```sh
$ ocamlfind ocamlc -o hello -package re sayHello.cmo hello.cmo
```

## L’outil dune

dune est un outil qui prend en charge la compilation de programmes OCaml.
Il a tendance à remplacer ces dernières années un outil précédent nommé ocamlbuild.

Il suffit de décrire ce que l’on veut faire dans un fichier nommé `dune` à la racine
de l’arbre des sources, contenant au moins `(executable (name hello))`
pour notre exemple.

Exécuter ensuite la commande : `dune build hello.exe` pour que dune
calcule automatiquement les dépendances du projet et lance les bonnes
commandes de compilation!  Nous utiliserons cet outil pour développer
le projet.  Pour plus d’informations: <http://dune.readthedocs.io>

## Autres éléments utiles pour le développement logiciel

OCaml ou sa communauté propose également d'autres outils ou bibliothèques pour :

  - produire de la documentation : `ocamldoc`, `odoc`.
  - faire des tests unitaires : `ounit`, `alcotest`...
  - debugger pas-à-pas : `ocamldebug`
  - profiler l'efficacité de son code : `ocamlprof`
  - produire du code client web à partir de code OCaml : `js_of_ocaml`.
  - écrire ses propres bibliothèques.
  - distribuer ses paquets logiciels.
  - utiliser des extensions du langage.
  - etc

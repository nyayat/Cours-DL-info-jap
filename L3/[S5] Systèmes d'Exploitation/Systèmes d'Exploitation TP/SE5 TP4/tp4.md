TP nº4 : le système de gestion des fichiers (SGF)
==========================

**L3 Informatique - Système**


Ce TP porte, d'une part, sur la consultation du contenu des inœuds, et
d'autre part sur le parcours de répertoire.


#### Exercice 1 : consulter les caractéristiques d'un fichier à l'aide de `stat()`ou `lstat()`

* Écrire un programme qui teste à l'aide de (la valeur de retour de)
  `stat()` si une chaîne de caractères `ref` (passée en paramètre) est
  une référence valide.

* Modifier le programme pour qu'il affiche le numéro d'inœud
  correspondant à `ref` (si `ref` est valide).

* Modifier à nouveau le programme pour qu'il affiche le type de fichier
  sur lequel pointe `ref` (fichier ordinaire, répertoire, lien
  symbolique, tube nommé...). 

  Vérifier que votre programme se comporte correctement pour les fichiers
  créés (dans le répertoire courant) par le script
  [tests_tp4.sh](tests_tp4.sh). Que pensez-vous en particulier du cas de
  `test_lien`? Changer `stat()` en `lstat()` et comparer.

* Modifier le programme pour qu'il indique si le fichier est exécutable
  par au moins une catégorie d'utilisateurs. 

  Vérifier que `tests_tp4.sh`, `test_rep`, `test_exe1` et `test_exe2` sont
  bien reconnus comme exécutables, contrairement à `test_fic`. Qu'en est-il
  de `test_lien` ?


#### Exercice 2 : lire les entrées d'un répertoire avec `readdir()` (ainsi qu'`opendir()` et `closedir()`)

* Écrire un programme ayant le même comportement que `ls -a` (ou `ls -f`,
  plutôt; consulter `man ls` si nécessaire).

* Modifier votre programme pour obtenir (à peu près) le comportement de
  `ls -i` (à peu près au sens où on se contentera d'un `ls -i` non trié,
  mais pas d'un `ls -ai`).


#### Exercice 3 : pwd

Le but de cet exercice est d'écrire un programme affichant la
référence absolue du répertoire courant à l'instar de la commande
`pwd -P` (consulter `man pwd` si nécessaire), sans utiliser la fonction
`getcwd()`. Pour cela, on appliquera l'algotithme suivant :

	* récupérer l'inœud `n` et le disque (_device_) `d` du répertoire courant,
	* chercher dans son père le nom du lien qui correspond à l'inœud
    `n` du disque `d`,
	* concaténer ce nom et le chemin représentant la référence absolue
      du répertoire de départ,
	* recommencer récursivement sur le répertoire parent du répertoire
      courant, jusqu'à atteindre la racine `/` de l'arborescence.

Une caractérisation de la racine `/` est qu'elle est son propre parent :
`.` et `..` ont donc les mêmes numéros d'inœud et de disque.

Testez votre programme et comparer avec le résultat de `pwd -P`. En
particulier, vérifiez qu'il fonctionne correctement sur `lulu`, dont
l'arborescence est constituées de plusieurs disques.


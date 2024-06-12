# TP nº5 : A Long Walk along The Filesystem

**L3 Informatique - Système**

Ce TP permet d'approfondir les parcours d'arborescences. Pour cela, nous
allons programmer une commande pour trouver un fichier à partir d'un
emplacement donné, et manipuler des archives `tar`, en reproduisant la
l'arborescence des répertoires.

Vous trouverez dans le [sujet du TP n°3](../TP3/tp3.md) un rappel sur les archives
`tar` et leur format.

#### Instructions pour faire le TP

1. Mettre à jour le dépôt `git` du cours. Placez-vous dans le
   répertoire du cours et lancez la commande `git pull`.
2. Déplacez-vous dans le répertoire `TP/TP5/`.
3. Lancez la commande `make init` pour initialiser le répertoire de
   travail.
4. Déplacez-vous dans le répertoire `work` pour travailler.


#### Exercice 1 : `findp`, trouver l'emplacement d'un fichier

Nous allons programmer une version simplifiée de la commande `find`, qui
se contentera d'une recherche selon le critère du nom (_basename_): on
souhaite que `findp dir_name target_name` soit un équivalent de `find
dir_name -name target_name`.

Le principe de l'algorithme est simple. Intuitivement, il faut lister
toutes les entrées d'un répertoire, et pour chaque entrée, vérifier si
elle porte le nom cherché; par ailleurs, s'il s'agit d'un répertoire, il
faut poursuivre la recherche dedans.

Déplacez-vous dans le répertoire `work/findp`.

1. Compléter la fonction `concat_strings`. Celle-ci doit prendre trois chaînes
   en arguments, allouer un nouveau buffer (de la bonne taille), y stocker la
   concaténation des deux chaînes, et renvoyer un pointeur vers ce buffer
   (sections marquées `TODO[1]`). En cas d'échec de l'allocation, la fonction
   doit renvoyer `NULL`.

2. Compléter le `main` et la fonction `process_dir` de `findp.c` pour que
   le programme effectue la recherche de fichier uniquement dans le
   répertoire passé en paramètre (sections marquées `TODO[2]`).

3. Il faut maintenant implémenter la recherche récursive; pour cela, nous
   avons besoin de construire les chemins des sous-répertoires à
   explorer. Pour chercher dans un sous-répertoire, il faut concaténer
   son nom au chemin en cours d'inspection et le supprimer quand nous avons
   fini de le traiter. Sans cela, nous n'aurons pas le bon chemin pour
   inspecter les fichiers du sous-répertoire. Implémentez la recherche
   dans les sous-répertoires (point `TODO[3]`).

4. Assurez-vous que si votre programme a trouvé des fichiers portant le
   nom cherché, l'exécution se termine avec `EXIT_SUCCESS`, et avec
   `EXIT_FAILURE` sinon.

5. Testez votre code avec la commande `make check`.


#### Exercice 2 : `untar`, reproduire une arborescence

Dans les exercices 2 et 3 du TP nº3, vous avez extrait et produit des
archives simples formées uniquement de fichiers ordinaires (sans
répertoires). Nous allons maintenant programmer un petit outil pour
extraire des archives `tar` plus générales.

0.
    - Si vous avez fini l'exercice 2 du TP3, renommez le fichier `untar.c`
      en `untar.c.alt`, puis copiez votre code du TP3, exercice 2,
      (`../TP3/work/detar.c`) en `work/untar/untar.c`.
     
    - Sinon, vous pouvez utiliser le fichier source : `work/untar/untar.c`.  
   
   Déplacez-vous dans le répertoire `work/untar` pour la suite de l'exercice.

1. Consulter (avec `hexdump -C` par exemple, ou `tar tf`) une archive
   `tar` représentant une petite arborescence, par exemple :
   
   ```sh
   tata/
   ├── titi
   ├── toto
   └── tutu
       └── toutou
   ```
   Que faudra-t-il faire pour reproduire cette arborescence lors de 
   l'extraction? 

2. Modifier le code pour prendre en charge l'extraction de répertoire.

3. Lancer le test avec la commande `make check`.



#### Exercice 3 : `mktar`, archiver une arborescence

1. Que faut-il faire pour reproduire une arborescence de répertoires dans
   une archive `tar`?

2. Modifier le programme `mktar` du TP nº3 pourqu'il puisse archiver des 
   arborescences.


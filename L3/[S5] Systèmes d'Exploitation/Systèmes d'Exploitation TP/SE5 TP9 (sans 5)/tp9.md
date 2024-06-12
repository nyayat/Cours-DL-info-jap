TP nº9 : tubes (anonymes)
=========================

**L3 Informatique - Système**

Le but de ce TP est d'apprendre à utiliser les tubes anonymes. Ces
derniers sont souvent utiles pour la communication entre processus. Une
autre utilisation permet de synchroniser deux processus qui vivent en
même temps. Par exemple, un processus attend l'autre en essayant de
lire sur le tube. Il continuera son exécution lorsque l'autre aura
écrit dans le tube.

Pour compiler vos programmes vous devez utiliser la commande suivante:

```sh
gcc -std=c17 -Wall -o nom_de_fichier nom_de_fichier.c
```
où *nom_de_fichier* est le nom du fichier que vous souhaitez compiler.

#### Exercice 1 : tubes et communication simple

Écrire un programme qui crée deux processus, un père et son fils.
*Après* la duplication, le père doit tirer un entier au hasard entre 0 et
100, l'afficher sur la sortie standard puis le transmettre à son fils via
un tube anonyme *sous la forme d'un `int`* (et donc *pas* sous la forme d'une
chaîne de caractères). Le fils doit alors afficher le nombre choisi par son
père.

#### Exercice 2 : tubes et synchronisation

Écrire un programme créant deux processus, un père et son fils. Le fils
sera écrivain et le père lecteur d'un même tube.

* le fils doit attendre durant quelques secondes avant d'écrire un
  caractère quelconque dans le tube, afficher `Fini!` puis
  terminer.

* pendant ce temps, le père devra lire un caractère dans le tube, puis
  afficher `Moi aussi!` et terminer.

Vérifier qu'il ne subsiste pas de processus zombie à l'issue de
l'exécution. Pourquoi est-ce bien le cas?


#### Exercice 3 : tubes et redirection

Reprendre le mini-shell `mppsh` du TP8 pour y ajouter le traitement des
commandes composées, du type `cmd1 | cmd2`, avec la généalogie suivante :

```sh
  mppsh
  |- cmd1
  |- cmd2
```

Pour cela, vous pouvez ajouter dans `mppsh-exec.c` une fonction
`execute_compose(char **argv1, char **argv2, char *ref_in, char *ref_out,
int flag)` qui crée un tube anonyme et deux processus fils, qui réalisent
ensuite les redirections nécessaires avant d'appeler la fonction
`execute()`.

Que se passe-t-il si vous oubliez de fermer les descripteurs superflus
après le `fork()`? Expérimentez plusieurs cas différents, par exemple
`ls | tail` et `yes | head`.


#### Exercice 4 : ping-pong

Écrire un programme qui crée deux processus, un père et son fils.

Via un tube, le père envoie `ping` à son fils, et ce dernier répond
`pong`. Ensuite le père répond à nouveau `ping`, etc. Au bout de quelques
échanges de balles, l'un des deux processus affiche sur la sortie
standard `Dehors, j'ai gagné!` puis termine. L'autre processus affiche
alors `Bravo… une petite revanche?` avant de terminer à son tour.

Combien faut-il de tubes?


#### Exercice 5 : la patate chaude

Dans cet exercice, `N` processus `p1`, `p2`, `pN` forment un anneau :
`p1` est relié à `p2` par un tube, `p2` à `p3`, ..., et `pN` est relié à
`p1`.  Un jeton (de type `int`), ou patate chaude, circule dans cet anneau.

Chaque processus lit sur le tube qui le relie au processus précédent.
Quand il reçoit la patate chaude, il affiche un message du type `Mon PID
est 2048 et la patate vaut 17`, puis il incrémente la patate et la
transmet au processus suivant.

La ligne de commande doit récupérer deux paramètres : le premier est la
taille `N` de l'anneau, le deuxième est la valeur maximale de la patate
`patatmax`.

Il faut donc créer l'anneau et les tubes avant de lancer la patate
(initialisée à 0) dedans. Attention, une seule patate doit circuler!
Quand la patate atteint `patatmax`, le processus qui la reçoit doit
afficher `Argh ! Je meurs !` puis se terminer. Faire en sorte que les
autres processus détectent la fin de la partie et terminent également.


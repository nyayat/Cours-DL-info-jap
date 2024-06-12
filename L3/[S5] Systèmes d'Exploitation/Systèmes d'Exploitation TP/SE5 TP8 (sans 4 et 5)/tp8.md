TP nº8 : `mppsh` (mon premier petit shell)
==========================================

**L3 Informatique - Système**

Le but de ce TP est de programmer un embryon de shell permettant :
- de lancer des commandes en avant-plan ou en arrière-plan,
- de rediriger l'entrée et/ou la sortie standard des commandes lancées,
- de gérer les enchaînements de commandes conditionnés.

Pour cela, un fichier `mppsh-parsing.c` définissant certaines fonctions
utiles pour l'interprétation de la ligne de commande vous est fourni.
Vous aurez à compléter les fichiers `mppsh-exec.c` et `mppsh-main.c`.


#### Exercice 1 : redirections

Compléter la fonction `void execute(char **argv, char *ref_in, char
*ref_out, int flag)` du fichier `mppsh-exec.c` pour obtenir le
comportement suivant :
- l'entrée standard du processus doit être redirigée sur le fichier
  `ref_in` (si `ref_in` n'est pas `NULL`);
- la sortie standard du processus doit être redirigée sur le fichier
  `ref_out` (si `ref_out` n'est pas `NULL`), en écrasement si `flag` est
  nul ou égal à `O_TRUNC`, en ajout si `flag` vaut `O_APPEND`;
- le processus doit ensuite être recouvert par la commande décrite par le
  tableau `argv`.

En cas d'erreur (par exemple si l'un des fichiers ne peut pas être
ouvert), le processus doit retourner `EXIT_FAILURE` après avoir affiché
un message adapté.

Tester votre fonction à l'aide du fichier `copie.c` fourni : `make copie`
crée deux exécutables, `copie` et `concatene`, qui prennent deux
paramètres `arg1` et `arg2`, et font respectivement l'équivalent de `cat
<arg1 >arg2` et `cat <arg1 >>arg2`.


#### Exercice 2 : boucle principale du shell

Compléter les fonctions `exec_external()` de `mppsh-exec.c` et `main()`
de `mppsh-main.c` pour reproduire la boucle principale d'un shell :
affichage de l'invite de commande, puis lecture, interprétation et
exécution de la ligne de commande.  Pour le moment, on ne demande que des
exécutions, à l'avant-plan, de commandes externes sans redirection :
`mppsh` doit donc créer un fils pour l'exécution de la commande, et
attendre la fin de son exécution avant un nouveau tour de boucle.

Le programme doit également gérer les lignes invalides (ligne
vide, nom ne correspondant pas à un exécutable, arguments invalides...) :
`mppsh` doit poursuivre son exécution après l'affichage d'un message
d'erreur; aucun fils ne doit subsister.

Tester `mppsh` pour vous assurer qu'il permet bien d'exécuter des
commandes avec ou sans arguments. Modifier ensuite votre `main()` pour
pouvoir gérer les redirections d'entrée et de sortie standard (la
fonction `parse_redirections()` pourra être utile).


#### Exercice 3 : commandes internes

Que se passe-t-il si la ligne de commande traitée par `mppsh`
est de la forme `cd rep`? Pourquoi?  Modifier le programme pour
résoudre le problème.

Ajouter également le traitement d'une commande `exit` permettant de
quitter proprement `mpsh`, ainsi qu'une commande  `echo` copiant ses
paramètres sur la sortie standard.

_(il existe une commande externe `/bin/echo`, mais la plupart des shells
ont leur propre commande interne `echo`, et nous avons besoin d'une
commande interne manipulant les flots standard pour ce qui suit)_

Que se passe-t-il maintenant si l'utilisateur saisit la ligne de commande
`echo coucou > sortie`? Ajouter ce qu'il faut pour gérer correctement les
redirections dans le cas des commandes internes : `mppsh` doit pouvoir
rétablir les flots standard initiaux une fois la commande terminée
(pensez à `dup`).


#### Exercice 4 : processus à l'arrière-plan

On souhaite maintenant gérer les lignes de commande terminées par un
`'&'`. La commande sera alors lancée en arrière-plan, c'est-à-dire 
que le processus fils devra créer un nouveau groupe de processus dont 
il sera le leader avant de procéder à un recouvrement, et que le processus
père continuera son exécution _sans attendre_, après avoir stocké le pid de
son fils (donc du nouveau groupe) dans le tableau global `pid_jobs`. 
Modifier `exec_external()` en conséquence.

Modifier ensuite la boucle principale de `mppsh` pour qu'elle procède,
avant chaque invite de commande, à un examen des jobs stockés dans
`pid_jobs` pour déterminer si certains sont terminés; il faudra dans ce
cas libérer la case correspondante, et afficher un message.

Ajouter également à `mppsh` une commande interne `jobs` permettant
d'afficher tous les processus en arrière-plan avec leur indice dans le
tableau et leur pid. 

_Si vous voulez afficher également la ligne de commande correspondante,
il faut prévoir un tableau supplémentaire `char*
argv_jobs[MAX_JOBS][MAX_TOKENS]` (ou `char* argv_jobs[MAX_JOBS]` si vous
stockez seulement `argv[0]`, ou la ligne non découpée).  Mais
attention, il faudra allouer la place nécessaire pour **recopier** ces
chaînes de caractères, et penser à la libérer ensuite._


#### Exercice 5 : enchaînements conditionnés

Ajouter la gestion des enchaînements de commandes de la forme `cmd1 &&
cmd2` et `cmd1 || cmd2` : la `cmd2` ne doit être exécutée que si `cmd1` a
échoué (resp. réussi). La fonction `parse_connector()` pourra être utile
(et devrait permettre de traiter relativement simplement des commandes
avec arguments, voire avec redirections si vous le souhaitez).


TP nº7 : synchronisation père-fils avec `wait` (et `waitpid`)
=============================================================

**L3 Informatique - Système**


Dans certains des exercices ci-dessous, on demande que des processus
tirent (secrètement) des valeurs aléatoires. Cela passe par l'utilisation
d'un *générateur pseudo-aléatoire*, c'est-à-dire d'un algorithme qui
calcule les valeurs successives (et parfaitement déterministes) d'une
suite récursive définie par une *graine* `u_0`, et une relation de la
forme `u_{n+1} = f(u_n)`, où `f` est une fonction aussi chaotique que
possible, au sens où, par exemple, `f(n)` et `f(n+1)` diffèrent
énormément, et tous les `u_n` diffèrent les uns des autres pour `n`
compris entre 0 et un très grand `N`.

Un bon exemple de tel générateur pseudo-aléatoire est fourni par les
fonctions `(s)random()` : `srandom()` permet de définir la graine, et
chaque appel à `random()` calcule le terme suivant de la suite
correspondante. Le choix de la graine peut être fait de manière
constante, par exemple avec `srandom(0)`, pour assurer la
reproductibilité de l'expérience; ou au contraire différemment à chaque
exécution, par exemple avec `srandom(time(NULL))` ou `srandom(getpid())`.



#### Exercice 1 : épinards

Écrire un programme qui crée deux processus, un père et son fils. Le père
affiche d'abord (une seule fois) le message `Mange tes épinards!`, auquel
le fils répond obstinément `Non!` un certain nombre de fois, avant
de finir par céder et d'afficher `Oui pôpa...` et de terminer (son
assiette). Le père affiche alors `C'est bien, Popeye! Tu seras fort comme
papa.` puis termine lui aussi.

Attention, le père doit détecter la terminaison du fils, et non avoir
préalablement connaissance du moment où celui-ci décidera de s'arrêter.
Le fils déterminera ce moment soit en tirant à pile ou face à chaque tour
de boucle, soit en choisissant (aléatoirement) à l'avance le nombre de
tours qu'il effectuera.

Une fois que tout marche bien, exécuter à nouveau le programme en
redirigeant sa sortie *vers un fichier ordinaire*, puis vérifier que le
contenu de ce fichier est conforme à vos attentes. Si ce n'est pas le cas,
penser au tampon de `stdout`...


#### Exercice 2 : fin d'une dynastie (linéaire)

Écrire un programme qui crée une hiérarchie linéaire de 18 processus.
Chaque processus affiche `Louis`_`i`_, où _`i`_ augmente de processus 
en processus. Faire en sorte que les _`i`_
apparaissent dans l'ordre croissant, et que le prompt de votre shell ne
réapparaisse pas tant que les affichages ne sont pas terminés.

(Vous avez bien sûr le droit de rajouter les lignes manquantes pour
reproduire l'intégralité du poème [_Les belles familles_](belles_familles.md) 
de Jacques Prévert).


#### Exercice 3 : à table!

Écrire un programme qui crée deux processus, un père et son fils. Toutes
les secondes, le père ~~appelle son fils~~ affiche le message `À table!`,
tandis que le fils fait obstinément la sourde oreille. Au bout d'un
certain temps (aléatoire), le fils cède, affiche `Oui, voilà, c'est bon
quoi!` (et termine). Le père lâche alors un `C'est pas trop tôt, tu n'es
pas à l'auberge ici!`, avant de terminer à son tour.

La fonction `sleep()` permet de mettre un processus en pause pendant une
durée évaluée en secondes.

_(ce que fait le père se rapproche de ce que fait un shell pour détecter
la terminaison d'un processus lancé à l'arrière-plan)_

#### Exercice 4 : devinette

Écrire un programme qui crée 10 processus, un père et ses fils. Chacun
tire (indépendamment) une valeur aléatoire entre 0 et 255. Le père garde 
la sienne secrète, tandis que les fils terminent en retournant leur tirage.  
Le père collecte les tirages de ses fils, puis annonce le vainqueur : le PID
du fils qui est le plus proche de sa valeur secrète.

Prévoyez suffisamment d'affichages pour pouvoir vérifier que le père ne
se trompe pas (et que les tirages des processus sont bien tous
indépendants)!


#### Exercice 5 : (tous) à table!

Reprendre l'exercice 3, avec cette fois une plus grande famille : le père
appelle tous ses enfants à table, et répète son appel aussi longtemps que
nécessaire. Il accueille chaque enfant d'un `Ah, voici PID! Les autres, à
table!` (avec le PID adapté). Lorsqu'il n'en manque plus qu'un, il change
son message en `Ah, voici PID1. PID2, à table, on n'attend plus que toi!`
(avec les PID adaptés).

Exemple d'exécution :
```bash
$ a_table 4
5132, 5133, 5134, 5135, à table!
À table!
À table!
À table!
Voilà, voilà, j'arrive...
Ah, voici 5133! Les autres, à table!
À table!
À table!
Voilà, voilà, j'arrive...
Ah, voici 5135! Les autres, à table!
À table!
Voilà, voilà, j'arrive...
Ah, voici 5132! 5134, à table, on n'attend plus que toi!
À table!
À table!
Voilà, voilà, j'arrive...
Ah, enfin! C'est pas trop tôt, tu n'es pas à l'auberge ici!`
```

_(cela nécessite donc que le père stocke les identifiants de ses fils pour
cocher les terminaisons au fur et à mesure, et cela se rapproche un peu
plus de ce que doit réellement faire un shell pour gérer les jobs à
l'arrière-plan)_

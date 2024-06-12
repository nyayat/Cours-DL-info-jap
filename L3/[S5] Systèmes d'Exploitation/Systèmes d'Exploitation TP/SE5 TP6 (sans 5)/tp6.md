# TP nº6 : duplication de processus avec `fork` et `exec`

**L3 Informatique - Système**

On écrira les fonctions du TP dans un fichier `tp6.c`,
  et leurs en-têtes dans un fichier `tp6.h`.
Le fichier `tp6.c` ne comportera pas de fonction `main()`;
  on testera chaque exercice
  dans la fonction `main()` d'un fichier différent.
On rappelle qu'on peut compiler séparément les fichiers :

```sh
  $ cc -c -g -O1 -Wall -Wextra -o tp6.o tp6.c
  $ cc -c -g -O1 -Wall -Wextra -o ex1.o ex1.c
  $ cc    -g -O1 -Wall -Wextra -o ex1 ex1.o tp6.o
```

On pourra automatiser certaines opérations
  en écrivant un Makefile en début de TP.

On utilisera la fonction `assert()` (voir `man 3 assert`)
  pour tester la validité des valeurs de retour des fonctions appelées.

#### Exercice 1

Écrire une fonction `f1()` d'en-tête :

```C
  void f1(unsigned int nprint, const char *parent, const char *child)
```

Cette fonction doit :

  - invoquer `fork()`;
  - le processus parent doit afficher `nprint` fois la chaîne `parent`;
  - le processus enfant doit afficher `nprint` fois la chaîne `child`.

Tester la fonction (par exemple, `f1(100, "hello", "goodbye")`.
Comment interpréter l'affichage ?

#### Exercice 2

1. Fork bomb

Écrire une fonction `f2()` d'en-tête :

```C
  void f2(unsigned int nprocs, unsigned int nprint, const char *fname)
```

Cette fonction doit :

  - ouvrir en écriture un fichier de nom `fname` (avec `open()`);
    l'ouverture devra échouer si le fichier existe déjà;
  - invoquer `nprocs` fois la fonction `fork()`;
  - obtenir son process ID (avec `getpid()`);
  - écrire `nprint` fois son process ID dans le fichier ouvert;
    quelle variante de `printf` peut être utilisée à cet effet ?
  - fermer le fichier ouvert (avec `close()`).

  en n'oubliant pas de tester la validité
  des valeurs de retour des fonctions utilisées.

Testez la fonction `f2()`
  pour des valeurs de `nprocs` entre 2 et 4 (pas plus),
  et des valeurs de `nprint` entre 10 et 100.
Qu'observez-vous ?
Combien de processus sont créés par la fonction `f2()` ?

2. Descripteurs de fichiers

Modifier la fonction `f2()` pour qu'elle écrive,
  en plus de son process ID et **avec un seul appel à `dprintf()`**,
  le process ID du processus parent (avec `getppid()`)
  et le numéro du descripteur du fichier ouvert.
Avant de tester votre nouvelle fonction,
  émettre des hypothèses sur ce qu'affichera cette fonction modifiée.
Vos hypothèses sont-elles vérifiées ? Pourquoi ?
Pourquoi est-il important de n'avoir qu'un seul appel à `dprintf()` ?

Que pouvez-vous déduire
  sur le devenir des descripteurs de fichier
  après un appel à `fork()` ?
Est-il dangereux d'invoquer `close()` ? Pourquoi ?

#### Exercice 3

1. Arborescence de processus

Écrire une fonction `void f3(void)` qui crée la généalogie de processus suivante :

```
  p1
  ├──p2
  ├──p3
  │  ├──p4
  │  ├──p5
  │  │  ├──p6
  │  │  └──p7
  │  └──p8
  └──p9
```

Chaque processus devra afficher (en une seule fois) :
  - son process ID,
  - le process ID du processus parent,
  - sa profondeur par rapport au processus p1
    (0 pour `p1`, 1 pour `p2`, `p3`, `p9`, …),

puis s'arrêter avec un appel à `pause()`.

Dans un autre terminal,
  vérifier que la généalogie des processus
  est bien celle attendue,
  à l'aide de `ps` ou `pstree`.
Terminer le programme avec CTRL+C.
Les processus créés ont-ils tous disparu ? Pourquoi ?

2. Processus orphelins

Modifier la fonction `f3()`
  pour que le processus racine (et lui seul)
  n'invoque pas `pause()`.
Les processus créés ont-ils disparu ? Pourquoi ?
Terminer manuellement les processus restants.

#### Exercice 4

Le but de cet exercice est d'effectuer des _recouvrements_ de processus.
Pour cela, il faudra utiliser l'une des fonctions de la famille `exec`
que nous n'avons pas eu le temps de détailler en cours, mais dont vous
trouverez la liste à la fin des [slides](../../Cours/cours6.pdf) du cours 6.
N'hésitez pas à consulter `man` pour plus de détail! 

1. Programme `twice`

Écrire une fonction `f4a()` d'en-tête :

```C
  void f4a(const char *cmd, int argc, char* argv[argc+1])
```

  qui exécute deux fois la commande `cmd`
  avec les arguments `argv[1]`, `argv[2]`…, `argv[argc]`

Compiler un exécutable `twice`
  tel que `./twice cmd arg1 arg2 … argn`
  invoque la fonction `f4a()`.
Le tester, par exemple `./twice ls $HOME`
  ou `./twice echo "bonjour" "au revoir"`.

2. Programme `repeat`

Écrire une fonction `f4b()` d'en-tête :

```C
  void f4b(unsigned int ntimes, const char *cmd, int argc, char* argv[argc])
```

  qui exécute `ntimes` fois la commande `cmd`
  avec les arguments `argv[1]`, `argv[2]`…, `argv[argc+1]`

Compiler un exécutable `repeat`
  tel que `./repeat k cmd arg1 arg2 … argn`
  invoque la fonction `f4b()`.
On utilisera la fonction `strtol()` pour la conversion,
  par exemple `int n = (int)strtol(str, (char **)NULL, 10);`.

#### Exercice 5

Si `p` est un nombre premier,
  et `g` et `m` sont des nombres entiers compris entre `1` et `p-1`,
  un **logarithme discret de `m` en base `g` modulo `p`**
  est un entier `k` qui vérifie la relation :

  `g`^`k` ≡ `m` mod `p`.

La recherche d'un logarithme discret est un problème difficile,
  ce qui la met au cœur des technologies contemporaines
  de cryptographie asymétrique,
  comme l'*échange de clef Diffie-Hellman*
  ou le *chiffrement El Gamal*.
Ici, on cherchera à paralléliser
  une recherche naïve de logarithme discret.

1. Logarithme discret non parallélisé

Écrire une fonction `f5a()` d'en-tête :

```C
  int f5a(unsigned int p, unsigned int g, unsigned int m)
```

  qui teste toutes les valeurs possibles de `k` entre `0` et `p-1`
  et renvoie un logarithme discret de `m` s'il existe.
La fonction renverra `p`
  si aucun logarithme discret n'a pu être trouvé.
La fonction renverra `-1` si `p` ≥ 2147483648,
  ou si `g` ou `m` ne sont pas compris entre `1` et `p-1`.

Tester votre fonction pour `m` = 1 et `p` = 555555587.
La retester pour `m` = 1 et `p` = 2147483647.

2. Logarithme discret parallélisé

Écrire une fonction `f5b()` d'en-tête :

```C
  int f5b(unsigned int p, unsigned int g, unsigned int m, unsigned int a, unsigned int b)
```

La fonction `f5b()` cherchera un logarithme discret de `m`
  *parmi les entiers de la forme `n` * `b` + `a`*
  (par exemple, pour `b` = 2 et `a` = 1,
    la fonction cherchera parmi les nombres impairs).
Quelle condition doit-on avoir sur `b`
  pour que le programme termine ?

Écrire un programme qui utilisera deux processus,
  chacun faisant un appel à `f5b(p, g, m, a, 2)`
  pour différentes valeurs de `a`,
  et écrit sur la sortie standard un logarithme discret de `m`
  s'il en existe un.

3. Parallélisme paramétrique

Écrire une fonction `f5()` d'en-tête :

```C
  void f5(unsigned int p, unsigned int g, unsigned int m, unsigned int nprocs)
```

  qui utilisera `nprocs` processus différents,
  chacun faisant un appel différent à `f5b()`
  qui parcourra les entiers de la forme `n` * `nprocs` + `a`
  pour différentes valeurs de `a`.

Le temps nécessaire pour afficher le premier
  entier `k` varie-t-il en fonction de `nprocs` ?
Vous pourrez utiliser les
  fonctions `time()` et `difftime()` de <time.h>.

TP nº3 : manipulation d'archives tar
=====================

**L3 Informatique - Système**

L'objectif de ce TP est de programmer un outil permettant d'obtenir certaines informations d'archives `tar`.

On rappelle que `tar` est un outil d'archivage, permettant par exemple les usages suivants :

```bash
tar cf test.tar fichier1 ... fichierN      # création d'une archive test.tar
tar tvf test.tar                           # liste des fichiers contenus dans test.tar
tar xf test.tar                            # extraction des fichiers présents dans test.tar
```

Note : par défaut, une archive `tar` n'est pas compressée, mais il suffit d'utiliser dessus un outil de compression tel que `gzip` pour obtenir des archives compressées `*.tar.gz`, souvent aussi nommées `*.tgz`. L'outil `tar` peut directement faire cela, voir par exemple `tar czf`. Dans ce TP nous ne travaillerons qu'avec des archives `tar` *non compressées*.

#### Le format des archives tar

Il existe plusieurs variantes de `tar`, elles-mêmes avec un certain nombre d'extensions possibles. Ce qui suit devrait suffire à pouvoir lire la plupart des fichiers `.tar`, et à écrire des `.tar` acceptés par la plupart des utilitaires `tar`. Pour une description plus exhaustive du format, voir [ici](https://www.gnu.org/software/tar/manual/html_node/Standard.html).

Un fichier `tar` est une suite de blocs de 512 octets. S'il représente une archive des fichiers `f1`, ..., `fn`, alors ce fichier `tar` comporte, dans l'ordre :
 
  - un bloc entête pour `f1`
  - les blocs correspondant au contenu de `f1`
  - ...
  - un bloc entête pour `fn`
  - les blocs correspondant au contenu de `fn`
  - deux blocs finaux formés uniquement de zéros

Si la taille d'un des fichiers archivés `fi` n'est pas un multiple de 512, alors le dernier bloc concernant `fi` est complété avec des octets nuls `'\0'` à hauteur de 512 octets. 

Un bloc entête a une structure décrite par le type `struct posix_header` dans le fichier [tarutils.h](templates/tarutils.h) fourni. Notez que cette structure fait exactement 512 octets de long (macro `BLOCKSIZE`), afin de correspondre exactement à la lecture (ou à l'écriture) d'un bloc. Voici quelques mots sur les principaux champs, les autres pouvant être ignorés ici :

  - `char name[100]` : nom long du fichier (_ie_ sa référence relative au point d'archivage). On supposera ici que 100 caractères suffisent pour stocker ce nom. Les caractères inutilisés seront mis à `'\0'`.
  
  - `char mode[8]` : permissions du fichier, converties en entier. Comme pour tous les autres champs numériques de l'entête, le dernier caractère est un `'\0'`, et les autres des caractères-chiffres entre `'0'` et `'7'` représentant un entier en octal. On pourra lire ce champ avec un `sscanf(hd.mode, "%o", ...)` et le remplir avec un `sprintf(hd.mode, "%07o", ...)`.
  
  - `char size[12]` : taille du fichier. Même remarque que précédemment concernant le codage de ce nombre, mais cette fois sur 12 caractères au lieu de 8. La lecture pourra se faire par `sscanf(hd.size, "%o", ...)` et l'écriture par `sprintf(hd.size, "%011o", ...)`.
  
  - `char chksum[8]` : empreinte ("checksum") de ce bloc entête. À la lecture, vous pouvez l'ignorer. En revanche, pour fabriquer un `tar` acceptable par GNU `tar` ce champ doit être correct. Pour cela, utiliser la fonction fournie `set_checksum()` de `tarutils.c` une fois que votre entête est prêt. Pour plus de détail, voir le commentaire devant `set_checksum()`.

  - `char typeflag` : il vaut `'0'` ou `'\0'` pour un fichier ordinaire,  et par exemple `'5'`pour un répertoire. *Les archives manipulées dans ce TP ne contiendront que des fichiers ordinaires*.
 
<!--- - `char magic[6]` : pour le format de `tar` que l'on utilise ici, ce champ devra être mis à `"ustar"` (vous pouvez utiliser la macro `TMAGIC` définie dans `tarutils.h` et valant `"ustar"`), et le champ suivant `version` être à `"00"` (sans terminateur). --->


#### Instructions générales

Mettez à jour le dépôt `git` du cours sur votre machine (en lançant la commande `git pull` depuis le dépôt cloné).

Déplacez-vous ensuite dans le répertoire `TP/TP3/` du dépôt git, et exécutez la commande `make init`.
Ceci crée :
  * un répertoire `TP/TP3/work/` contenant déjà une partie du code, que vous devrez compléter.
  * plusieurs fichiers d'archive dans `/tmp/<USERNAME>/` :
      - deux archives valides, `titi.tar` contenant 2 petits fichiers
        (chacun tenant sur un bloc) et `tarutils.tar` contenant 2 plus gros fichiers;
      - deux archives corrompues, `sans_zeros.tar` à qui manquent les
        deux blocs finaux de `'\0'`, et `tronque.tar` dont le dernier bloc
        de contenu est tronqué (il manque les `'\0'` d'alignement).

Vous devrez compléter les fichiers `.c` fournis dans le répertoire `TP/TP3/work/`, et effectuer des tests dans `/tmp/<USERNAME>/` : la manipulation d'une archive, notamment l'extraction,  peut écraser des fichiers déjà présents. De plus vos premiers essais peuvent mal se comporter (par exemple créer des flopées de fichiers aux noms cabalistiques). Il est donc *impératif* de faire vos essais dans un répertoire de test ne contenant rien d'important, en particulier vos fichiers C. Se placer dans `/tmp/<USERNAME>` est un bon choix.


**Tous les accès en lecture/écriture aux différents fichiers manipulés**
devront être effectués à l'aide des fonctions de bas niveau. En revanche,
l'utilisation des fonctions de `stdio.h` est autorisée pour tous les
affichages sur la sortie ou l'erreur standard (`printf`, `fprintf`,
`perror`) ou pour le formatage des chaînes des caractères (`sprintf`,
`sscanf`). 


#### Exercice 1 : liste des fichiers contenus dans une archive `tar`

Compléter `listar.c` pour obtenir un programme tel que `listar toto.tar` liste le contenu d'une archive `toto.tar` dans l'esprit de `tar tvf toto.tar`. Plus précisément, `listar toto.tar` devra afficher, pour chaque entête du fichier `toto.tar` : les permissions du fichier décrit par l'entête (en octal), sa taille (en décimal), le nombre de blocs que son contenu occupe, et enfin son nom.

Votre programme devra :
  * lire chaque entête *en une seule fois*;
  * interpréter et afficher les informations contenues dans l'entête;
  * se déplacer à l'entête suivant à l'aide de la fonction `lseek`; 
  * s'arrêter lorsqu'il rencontre un bloc rempli de `'\0'` (en vérifiant éventuellement la présence d'un deuxième juste après, comme le format le spécifie).

Pour vous déplacer d'entête en entête dans le fichier `tar`, vous pouvez remarquer que le nombre de blocs occupés par un fichier de taille `filesize` est la partie entière _supérieure_ de la division de `filesize` par 512, c'est-à-dire `(filesize + 512 - 1) / 512`, ou encore `(filesize + 512 - 1) >> 9` puisque 512 = 2^9, ou `(filesize + BLOCKSIZE - 1) >> BLOCKBITS` avec les macros définies dans `tarutils.h`.

Vérifier que `listar` parvient à lister le contenu des archives valides fournies, et détecte les erreurs de format des archives corrompues.


#### Exercice 2 : extraction du contenu d'une archive

1. **Extraction d'un fichier particulier** : compléter `detar.c` pour obtenir un programme `detar` tel que l'appel à `detar toto.tar fic` extraie de l'archive uniquement le fichier dont le nom est passé en paramètre. **On supposera qu'aucune création de répertoire n'est nécessaire.**

  Le fichier `fic` créé doit avoir les droits indiqués dans l'entête; il
  doit également avoir la bonne taille : en général, le dernier bloc de
  contenu est complété par des aoctets nuls qui ne doivent pas être
  recopiés.

  Vérifier que `detar` se comporte bien comme `tar xvf` lorsqu'on lui
  passe deux paramètres, par exemple `titi.tar` et `grosminet`.

  ___Attention! L'extraction d'une archive peut faire des dégâts, se placer dans `/tmp/<USERNAME>/` pour les tests est encore plus important qu'à l'exercice précédent.___


2. **Extraction complète d'une archive simple** : modifier `detar.c` pour que l'appel à `detar toto.tar` extraie de l'archive tous les fichiers qu'elle contient. 

  Vérifier que `detar` se comporte bien comme `tar xvf` lorsqu'on lui
  passe un seul paramètre.

#### Exercice 3 : création d'une archive simple

Compléter `mktar.c` pour obtenir un programme `mktar` tel que l'appel à `mktar archive.tar fic1 ... ficn` crée l'archive `archive.tar` contenant les fichiers ordinaires `fic1` ... `ficn` (situés dans le répertoire courant). Ce fichier `archive.tar` devra pouvoir être lu avec l'utilitaire GNU `tar`. Cela signifie que certains champs doivent obligatoirement être renseignés : `typeflag`, `name`, `mode`, `size` et `checksum`. 

La fonction `stat()` sera nécessaire pour obtenir certaines de ces
informations.

Utilisez `mktar` pour créer des archives, et vérifier que `tar` parvient à en lister et extraire le contenu sans erreur.

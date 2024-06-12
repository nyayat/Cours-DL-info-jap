# Pendule inversé et contrôleur PID

## Présentation

Ce dossier contient un exemple classique de système physique qu'on peut chercher
à contrôler, le pendule inversé.

## Dépendances

Le projet utilise du code Python 3, et le générateur d'interfaces SWIG pour
exposer le code C généré par Heptagon à Python. Pour le lancer, vous devez :

- avoir Heptagon, à installer via OPAM,

- avoir SWIG, à installer via le gestionnaire de paquet de votre système
  d'exploitation (`apt-get` sous GNU/Linux Debian ou Ubuntu, `pacman` sous Arch
  Linux, `brew` sous macOS, etc.),

- avoir la bibliothèque Python `matplotlib`, à installer `pip install --user
  matplotlib`.

## Utilisation

Vous pouvez lancer le programme en exécutant `make run`.

Pour lancer le programme avec l'affichage des courbes, exécutez `make runplot`.
Attention : afficher les courbes engendre un ralentissement assez conséquent.

L'interface utilisateur fonctione de la façon suivante :

- **Q** permet de quitter,

- **P** permet de mettre en pause,

- **R** permet de réinitialiser la simulation,

- **M** permet de changer de mode (manuel, contrôleur PID, contrôleur BangBang),

- **←** et **→** permettent de déplacer le mobile,

- **clic gauche** permet de déplacer le mobile à la souris.

Les deux dernières commandes ne fonctionnent qu'en mode manuel.

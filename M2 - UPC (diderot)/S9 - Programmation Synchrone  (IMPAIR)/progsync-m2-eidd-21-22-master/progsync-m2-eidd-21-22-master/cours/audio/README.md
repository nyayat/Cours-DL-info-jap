# Synthèse sonore élémentaire en Heptagon

Ce dossier contient quelques noeuds Heptagon élementaires qui produisent du son.

Il utilise les bibliothèques SDL2 et sndfile. Elles doivent être installées via
le gestionnaire de paquet de votre système d'exploitation (`apt-get` sous
GNU/Linux Debian ou Ubuntu, `pacman` sous Arch Linux, `brew` sous macOS, etc.).

```shell
$ make
$ ./audio
```

Pour essayer différents codes, il faut éditer le noeud `main` dans `audio.ept`.

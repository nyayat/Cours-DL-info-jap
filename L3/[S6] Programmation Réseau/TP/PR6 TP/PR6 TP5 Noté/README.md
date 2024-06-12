## Utilisation :
1. `make all` pour compiler les fichiers
2. `./serveur numéro_port` pour lancer le serveur
3. `./client1 adresse_IP numéro_port` et `./client2 adresse_IP numéro_port` pour lancer les clients (respectivement celui qui envoie 5 entiers, et celui qui demande le maximum) sur un deuxième terminal
4. `make clean` pour nettoyer les fichiers générés par `make all`

## Exemple
1. `make all`
2. `./serveur 6666`
3. `./client2 localhost 6666`
4. `./client1 localhost 6666` 
5. `./client2 localhost 6666`
6. `make clean`

## Remarque :
Lorsque l'on joue le rôle du client sans passer par les fichiers `client*.c`, les retours chariots sont pris en compte dans les send.
Il y a alors deux conséquences à cela :
- Pour la saisie du pseudo, seulement `MAX_NAME-1`=9 caractères sont admissibles (le 10e étant alors `\n`)
- Pour la réponse à la demande `MAX`, la réponse sera déclinée en deux lignes :
    - la première comptenant `REP<pseudo>`
    - et la deuxième, `<ip><val>`
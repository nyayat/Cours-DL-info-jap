#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>
#include "addition.h"

int main() {
  int fd_req, fd_rep, nb_lus;
  requete buf;
  struct stat status;

  /* création du tube de requête */
  if(mkfifo(tube_requetes, 0622) == -1 && errno != EEXIST) {
    perror("mkfifo");
    exit(1);
  }

  /* vérification... */
  if (stat(tube_requetes, &status) == -1) {
    perror("stat");
    exit(1);
  }

  if (S_ISFIFO(status.st_mode) == 0)  {
    fprintf(stderr, "%s is not a fifo\n", tube_requetes);
    exit(1);
  }
  
  /* ouverture du tube de requête (si une seule fois) */
  
  printf("j'essaie d'ouvrir le tube...\n");
  //fd_req = open(tube_requetes, O_RDONLY);
  //fd_req = open(tube_requetes, O_RDONLY | O_NONBLOCK);
  fd_req = open(tube_requetes, O_RDWR);
  if (fd_req == -1) {
    perror("open req");
    exit(1);
  }
  printf("ouverture effectuée\n");

  /* boucle infinie */
  while(1) {
    /* ouverture du tube (si à chaque tour) */
    /* printf("j'essaie d'ouvrir le tube...\n");
    fd_req = open(tube_requetes, O_RDONLY);
    printf("ouverture effectuée\n"); 
*/
    /* lecture d'une requête */
    printf("j'essaie de lire une requête...\n");
    nb_lus = read(fd_req, &buf, sizeof(requete));

    if(nb_lus == -1) {
      perror("read");
      exit(1);
    }
    else {
      printf("lecture effectuée : %d octets reçus\n", nb_lus);
      if (nb_lus == sizeof(requete)) {
	/* ouverture du tube de réponse */
	fd_rep = open(buf.tube, O_WRONLY);
	if (fd_rep == -1) {
          perror("open rep");
    	  exit(1);
    	}

	/* gros calcul ...  */
	int res = buf.a + buf.b;

	/* envoi de la réponse */
	if (write(fd_rep, &res, sizeof(int)) == -1) {
	  perror("write");
	  exit(1);
	}
	/* fermeture du tube de réponse */
	close(fd_rep);
	/* fermeture du tube de requête (si ouverture à chaque tour) */
	/* close(fd_req); */
      }
      else {
	fprintf(stderr, "requête incomplète\n");
	/* discutable... */
	continue;
      }
    }
  }
}

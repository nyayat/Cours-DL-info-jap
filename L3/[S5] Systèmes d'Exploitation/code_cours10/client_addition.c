#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include "addition.h"

int main() {
  int fd_req, fd_rep;
  requete buf;
  
  /* préparation de la requête */
  buf.a = 5;
  buf.b = 7;
  strncpy(buf.tube, "/tmp/mon_tube_reponse", MAX_REF);

  /* ouverture du tube de requête */
  fd_req = open(tube_requetes, O_WRONLY);
  if (fd_req == -1) {
    perror("open req");
    exit(1);
  }
  
  /* création du tube de réponse (personnel) */
  if (mkfifo(buf.tube, 0622) == -1) {
    perror("mkfifo");
    exit(1);
  }

  /* envoi de la requête  */
  if (write(fd_req, &buf, sizeof(requete)) == -1) {
    perror("write");
    unlink(buf.tube);
    exit(1);
  }

  /* fermeture du tube de requête */
  close(fd_req);
	
  /* ouverture du tube de réponse */
  fd_rep = open(buf.tube, O_RDONLY);
  if (fd_rep == -1) {
    perror("open rep");
    exit(1);
  }

  /* lecture de la réponse */
  int res;
  if (read(fd_rep, &res, sizeof(int)) == -1) {
    perror("read");
    exit(1);
  }

  /* fermeture et suppression du tube de réponse  */ 
  close(fd_rep);
  unlink(buf.tube);

  /* affichage final */
  printf("%d + %d = %d\n", buf.a, buf.b, res);

  return 0;
}

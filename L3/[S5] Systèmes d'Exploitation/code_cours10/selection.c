#include <sys/select.h>
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BLEU   "\033[94m"
#define ROUGE  "\033[91m"
#define NORMAL "\033[00m"
#define LEN 10

void ecrire(int fd, int t){
  while(1) {
    dprintf(fd, "%d", getpid());
    sleep(t);
  }
}

int afficher(int fd, char *couleur) {
  char buf[LEN];
  memset(buf, 0, LEN);
  if (read(fd, buf, LEN) > 0) 
    printf("%slu sur %d : %s%s\n", couleur, fd, buf, NORMAL);
  else return 0;
  return 1;
}

void lire(int fd1, int fd2){
  fd_set readfds;
  int encore_fd1, encore_fd2;
  int max_fd = (fd1>fd2)?fd1:fd2;

  encore_fd1 = encore_fd2 = 1;
  while(encore_fd1 || encore_fd2) {
    FD_ZERO(&readfds);
    if (encore_fd1) FD_SET(fd1, &readfds);
    if (encore_fd2) FD_SET(fd2, &readfds);
    select(max_fd+1, &readfds, NULL, NULL, NULL);
    /* readfds a été modifié et contient seulement les descripteurs prêts */
    if (FD_ISSET(fd1, &readfds)) {
      encore_fd1 = afficher(fd1, BLEU);
    }
    if(FD_ISSET(fd2, &readfds)) {
      encore_fd2 = afficher(fd2, ROUGE);
    }
  }
}

int main(void) {
  int tube1[2], tube2[2];

  if (pipe(tube1) == -1) {
    perror("tube1");
    exit(1);
  }

  if (pipe(tube2) == -1) {
    perror("pipe2");
    exit(1);
  }

  switch (fork()) {
    case -1 : 
      perror("fork"); 
      exit(1);
    case 0 : /* écrivain tube1 */
      close(tube2[0]);
      close(tube2[1]);
      close(tube1[0]);
      ecrire(tube1[1], 1);
    break;
    default : 
    switch (fork()) {
      case -1 : 
        perror("fork");
        exit(1);
      case 0 : /* écrivain tube2 */
        close(tube1[0]);
        close(tube1[1]);
        close(tube2[0]);
        ecrire(tube2[1], 5);
        break;
      default : /* lecteur */
        close(tube1[1]);
        close(tube2[1]);
        lire(tube1[0], tube2[0]);
    }
  }
  
  return 0;
}

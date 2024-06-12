#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main() {
  int id=getpid();
  printf("Je suis le processus %d\n",id);
  printf("Je fais un fork\n");
  int idfork=fork();
  if(idfork==0){
    int idfils=getpid();
    int idpere=getppid();
    printf("Je suis %d fils de %d\n",idfils,idpere);
  } else {
    printf("Mon fils est %d\n",idfork);
    int etat;
    waitpid(idfork, &etat, 0);
  }
  return 0;
}

#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main(void){
  int r;

  r = fork();
  switch(r) {
    case -1 : 
      perror("fork");
      exit(1);
    case 0 : // fils
      printf("je suis le fils; mon pid est %d, celui de mon père %d\n",
           getpid(), getppid());
      break;
    default : // père
      printf("JE SUIS LE PÈRE; MON PID EST %d, JE VIENS DE CRÉER UN FILS, DE PID %d\n",
           getpid(), r);
  }

  return 0;
}

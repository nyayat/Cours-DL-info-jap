#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main(void){
  int r;

  printf("mon pid : %d\n", getpid());
  r = fork();
  if (r == -1) {
    perror("fork");
    exit(1);
  }

  printf("mon pid : %d\tretour de fork : %d\n", getpid(), r);
  
  return 0;
}

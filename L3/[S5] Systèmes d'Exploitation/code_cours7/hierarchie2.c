#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main(void){
  switch (fork()) {
    case -1 :
      perror("fork1");
      exit(1);
    case 0 :
      if (fork() == -1) {
        perror("fork2");
        exit(1);
      }
  }

  pause();
  
  return 0;
}

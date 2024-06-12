#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char **argv){
  if(argc>1) {
    if (fork()==0)
      execvp(argv[1], argv+1);
    else {
      wait(NULL);
      printf("fils terminé");
    }
  }

}

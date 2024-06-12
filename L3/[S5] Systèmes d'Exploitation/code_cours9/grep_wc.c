#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char **argv){
  int fd[2];
  
  if (argc!=3) {
    fprintf(stderr, "usage : %s <mot> <fichier>\n", argv[0]);
    exit(1);
  }

  if (pipe(fd) == -1) {
    perror("pipe");
    exit(1);
  }

  switch (fork()) {
    case -1 : 
      perror("fork");
      exit(1);
    break;
    case 0 : // grep argv[1] argv[2] [ecrivain]
      close(fd[0]);
      dup2(fd[1], 1);
      close(fd[1]);
      execlp("grep", "grep", argv[1], argv[2], NULL);
      exit(1);
    default : // wc -l [lecteur]
      //close(fd[1]);
      dup2(fd[0], 0);
      close(fd[0]);
      execlp("wc", "wc", "-l", NULL);
      exit(1);
  }
}

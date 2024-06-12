#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main(int argc, char **argv) {
  int fd0, fd1, w;

  switch (fork()) {
    case -1 : 
      perror("fork");
      exit(EXIT_FAILURE);
      
    case 0 :
      fd0 = open(argv[1], O_RDONLY);
      fd1 = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0600);
      dup2(fd0, 0);
      dup2(fd1, 1);
      close(fd0);
      close(fd1);
      //execlp("cat", "cat", NULL);
      execl("cat", "cat", NULL);
      perror("exec");
      exit(EXIT_FAILURE);

    default :
      wait(&w);
      printf("valeur de retour du fils: %d\n", WEXITSTATUS(w));
  }

  return 0;
}

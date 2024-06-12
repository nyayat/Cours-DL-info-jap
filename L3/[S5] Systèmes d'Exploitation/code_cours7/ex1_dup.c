#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>

int main(void) {
  printf("pid = %d\n", getpid());
  
  int fd = open("toto", O_WRONLY | O_CREAT | O_TRUNC, 0600);

  sleep(5);
  dup2(fd, 1);
  close(fd);

  fprintf(stderr, "redirection faite\n");

  sleep(5);
  
  printf("ceci est un test\n");

  return 0;
}

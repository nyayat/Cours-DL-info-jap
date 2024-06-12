#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>

int main() {
  char buf[32];
  int nb, fd = open("toto", O_RDONLY /*O_WRONLY*/);
  if (fd < 0) {
    perror("problème d'ouverture");
    exit(1);
  }
  chmod("toto", 0);
  if ((nb=read(fd, buf, 32)) < 0) {
    perror("problème de lecture");
    exit(1);
  }
  write(1, buf, nb);
  chmod("toto", 0600);
  close(fd);
}

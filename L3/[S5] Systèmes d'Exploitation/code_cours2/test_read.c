#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <assert.h>
#include <string.h>

int main(int argc, char **argv){
  int fd, nb;
  char c, buf[32];

  assert(argc>1);

  fd = open(argv[1], O_RDONLY);
  if (fd < 0) {
    perror("échec ouverture");
    return 1;
  }
    
  //nb = read(fd, &c, 1);
  //memset(buf, 0, 32);
  nb = read(fd, buf, 32);
  if (nb<0) {
    perror("échec lecture");
    close(fd);
    return 2;
  } 
  //printf("%d caractère lu : %c\n", nb, c);
  //printf("%d caractère(s) lu(s) : __%s__\n", nb, buf);
  write(STDOUT_FILENO, buf, nb);
  close(fd);
  

  return 0;
}

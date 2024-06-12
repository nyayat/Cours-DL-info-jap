#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <assert.h>
#include <stdio.h>

int main(void){
  int fd, tmp;
  while(1){
    fd = open("/etc/passwd", O_RDONLY);
    if (fd<0) {
      perror("échec ouverture");
      fprintf(stderr, "dernier descripteur attribué: %d\n", tmp);
      return -1;
    }
    tmp = fd;
  }
}

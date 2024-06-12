#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <assert.h>
#include <errno.h>

int main(int argc, char **argv){
  int fd;

  assert(argc>1);

  fd = open(argv[1], O_RDONLY);
  if (fd < 0) {
    perror("ouverture en lecture seule : échec ");
    printf("errno = %d; ENOENT = %d; ELOOP = %d; EACCES = %d\n", errno, ENOENT, ELOOP, EACCES);
  }
  else {
    printf("ouverture en lecture seule : succès\n");
    close(fd);
  }

  fd = open(argv[1], O_RDWR);
  if (fd < 0) {
    perror("ouverture en lecture/écriture : échec ");
    printf("errno = %d; ENOENT = %d; ELOOP = %d; EACCES = %d\n", errno, ENOENT, ELOOP, EACCES);
  }
  else {
    printf("ouverture en lecture/écriture : succès\n");
    close(fd);
  }

  fd = open(argv[1], O_WRONLY | O_CREAT | O_EXCL, S_IRUSR | S_IWUSR);
  if (fd < 0) {
    perror("ouverture en création exclusive : échec ");
    printf("errno = %d; ENOENT = %d; ELOOP = %d; EACCES = %d\n", errno, ENOENT, ELOOP, EACCES);
  }
  else {
    printf("ouverture en création exclusive : succès\n");
    close(fd);
  }

  return 0;
}

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>

int main(void){
  int fd1 = open("toto", O_WRONLY | O_CREAT, S_IRUSR | S_IWUSR), fd2;
  char c = 'X';
  int i;
  
  if(fork()==0)
    c = '.';
  fd2 =   open("titi", O_WRONLY | O_CREAT, S_IRUSR | S_IWUSR);
  for(i=0; i<1e6; i++){
    write(fd1, &c, sizeof(char));
  }
  for(i=0; i<1e6; i++){
    write(fd2, &c, sizeof(char));
  }
  
  return 0;
}


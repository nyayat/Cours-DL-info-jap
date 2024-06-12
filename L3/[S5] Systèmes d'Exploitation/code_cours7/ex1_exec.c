#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main(void){
  printf("mon pid : %d\n", getpid());
  //execl("/usr/bin/xeyes", "toto", NULL);
  //execl("/opt/X11/bin/xeyes", "toto", NULL);
  execlp("xeyes", "toto", NULL);
  printf("ce message ne devrait pas s'afficher!\n");
}

#include <sys/types.h>
#include <unistd.h>
#include<stdio.h>

int main(){
  printf("père : pid = %d\n", getpid());

  if (fork()>0) {
    pause();
  } 
  else {
    printf("fils : pid = %d\n", getpid());
    if (fork()>0) { } 
    else {
      printf("petit-fils : pid = %d\n", getpid());
      pause();
    }
  }
}

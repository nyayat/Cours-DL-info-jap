#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

int main(){
  int x=0;
  int pid = fork();
  if ( pid == 0 ) { 
    x=25;
    printf("Valeur de x pour le fils:%d\n",x);
    printf("fin du processus fils\n"); 
  }
  else { 
    while(x!=25){
      sleep(2);
      printf("Valeur de x pour le pere %d\n",x);
    }
      printf("fin du processus pere\n"); 
  }
  return 0;
}

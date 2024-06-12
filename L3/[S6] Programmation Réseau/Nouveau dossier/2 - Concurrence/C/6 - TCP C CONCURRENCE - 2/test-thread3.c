#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

void *affiche(void *ptr);

int main(){
  pthread_t th1, th2;
  char *s1="Je suis thread 1\n";
  char *s2="Je suis thread 2\n";
  pthread_create(&th1,NULL,affiche,s1);
  pthread_create(&th2,NULL,affiche,s2);

  char *r1;
  char *r2;
  pthread_join(th1,(void **)&r1);
  pthread_join(th2,(void **)&r2);
  printf("%s",r1);
  printf("%s",r2);
  return 0;
}

void *affiche(void *ptr){
  char *s=(char *)ptr;
  printf("Mon message est : %s",s);
  char *mess=(char *)malloc(100*sizeof(char));
  strcat(mess,s);
  strcat(mess," Fini\n");
  pthread_exit(mess);
}

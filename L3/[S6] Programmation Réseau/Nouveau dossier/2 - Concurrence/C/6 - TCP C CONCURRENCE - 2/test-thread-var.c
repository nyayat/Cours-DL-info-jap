#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

void *inc(void *ptr);

int a=0;

int main(){
  pthread_t th1, th2;
  pthread_create(&th1,NULL,inc,NULL);
  pthread_create(&th2,NULL,inc,NULL);

  pthread_join(th1,NULL);
  pthread_join(th2,NULL);
  printf("a vaut %d\n",a);
  return 0;
}

void *inc(void *ptr){
  a=a+1;
  return NULL;
}

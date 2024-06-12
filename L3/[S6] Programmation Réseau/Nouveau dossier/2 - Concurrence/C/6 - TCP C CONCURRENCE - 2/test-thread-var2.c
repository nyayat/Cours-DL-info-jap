#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

void *inc(void *ptr);
void *dec(void *ptr);

int a=0;

int main(){
  pthread_t th1, th2,th3;
  pthread_create(&th1,NULL,inc,NULL);
  pthread_create(&th2,NULL,dec,NULL);
  pthread_create(&th3,NULL,dec,NULL);
  pthread_join(th1,NULL);
  pthread_join(th2,NULL);
  pthread_join(th3,NULL);
  printf("a vaut %d\n",a);
  return 0;
}

void *inc(void *ptr){
  a=a+1;
  return NULL;
}

void *dec(void *ptr){
  if(a>0){
    sleep(1);
    a=a-1;
  }
  return NULL;
}

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

void *inc(void *ptr);
void *dec(void *ptr);

pthread_mutex_t verrou= PTHREAD_MUTEX_INITIALIZER;
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
  pthread_mutex_lock(&verrou);
  a=a+1;
  pthread_mutex_unlock(&verrou);
  return NULL;
}

void *dec(void *ptr){
  pthread_mutex_lock(&verrou);
  if(a>0){
    a=a-1;
  }
  pthread_mutex_unlock(&verrou);
  return NULL;
}

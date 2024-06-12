#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>

void *recoit_udp(void *arg){
  int sock=*((int *)arg);
  char tampon[100];
  while(1){
    int rec=recv(sock,tampon,100,0);
    tampon[rec]='\0';
    printf("Message recu : %s\n",tampon);
  }
}


int main() {
  int sock1=socket(PF_INET,SOCK_DGRAM,0);
  struct sockaddr_in address_sock1;
  address_sock1.sin_family=AF_INET;
  address_sock1.sin_port=htons(5555);
  address_sock1.sin_addr.s_addr=htonl(INADDR_ANY);
  int sock2=socket(PF_INET,SOCK_DGRAM,0);
  struct sockaddr_in address_sock2;
  address_sock2.sin_family=AF_INET;
  address_sock2.sin_port=htons(5556);
  address_sock2.sin_addr.s_addr=htonl(INADDR_ANY);
  int r=bind(sock1,(struct sockaddr *)&address_sock1,sizeof(struct sockaddr_in));
  if(r==0){
    int r2=bind(sock2,(struct sockaddr *)&address_sock2,sizeof(struct sockaddr_in));
    if(r2==0){
      pthread_t th1,th2; 
      pthread_create(&th1,NULL,recoit_udp,&sock1);
      pthread_create(&th2,NULL,recoit_udp,&sock2);
      pthread_join(th1,NULL);
      pthread_join(th2,NULL);
    }
  }
  return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>

void *comm(void *arg){
  int sockcomm=*((int *)arg);
  char *mess="Hi\n";
  send(sockcomm,mess,strlen(mess)*sizeof(char),0);
  char buff[100];
  int recu=recv(sockcomm,buff,99*sizeof(char),0);
  buff[recu]='\0';
  printf("Message recu : %s\n",buff); 
  close(sockcomm);
  free(arg);
  return NULL;
}

int main(int argc, char**argv) {
  if(argc!=2){
    printf("Erreur il faut fournir un numero de port");
    return 0;
  }
  int p=atoi(argv[1]);
  int sock=socket(PF_INET,SOCK_STREAM,0);
  struct sockaddr_in address_sock;
  address_sock.sin_family=AF_INET;
  address_sock.sin_port=htons(p);
  address_sock.sin_addr.s_addr=htonl(INADDR_ANY);
  int r=bind(sock,(struct sockaddr *)&address_sock,sizeof(struct sockaddr_in));
  if(r==0){
    r=listen(sock,0);
    struct sockaddr_in caller;
    socklen_t size=sizeof(caller);
    while(1){
      int *sock2=(int *)malloc(sizeof(int));
      *sock2=accept(sock,(struct sockaddr *)&caller,&size);
      if(*sock2>=0){
        pthread_t th;
        pthread_create(&th,NULL,comm,sock2);
      }
    }
  }
  return 0;
}    

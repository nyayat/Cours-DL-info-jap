#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <fcntl.h>

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
      fcntl( sock1, F_SETFL, O_NONBLOCK);
      fcntl( sock2, F_SETFL, O_NONBLOCK);
      fd_set initial;
      int fd_max=0;
      FD_ZERO(&initial);
      FD_SET(sock1,&initial);
      if(fd_max<sock1){fd_max=sock1;}
      FD_SET(sock2,&initial);
      if(fd_max<sock2){fd_max=sock2;}
      char tampon[100];
      int rec1=0;
      int rec2=0;
      while(1){
        fd_set rdfs;
        FD_COPY(&initial,&rdfs);
        int ret=select(fd_max+1, &rdfs, NULL, NULL, NULL);
        while(ret>0){
          if(FD_ISSET(sock1,&rdfs)){
            rec1=recv(sock1,tampon,100,0);
            printf("Taille de données recues %d\n",rec1);
            if(rec1>=0){
              tampon[rec1]='\0';
              printf("Message recu : %s\n",tampon);
            }
            ret--;
          }
          if(FD_ISSET(sock2,&rdfs)){
            rec2=recv(sock2,tampon,100,0);
            printf("Taille de données recues %d\n",rec2);
            if(rec2>=0){
              tampon[rec2]='\0';
              printf("Message recu : %s\n",tampon);
            }
            ret--;
          }
        }
      }
    }
  }
  return 0;
}

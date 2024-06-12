#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
 #include <poll.h>
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
      struct pollfd p[2];
      p[0].fd=sock1;
      p[0].events=POLLIN;
      p[1].fd=sock2;
      p[1].events=POLLIN;
            char tampon[100];
      int rec1=0;
      int i;
      while(1){
        int ret=poll(p,2,-1);
        if(ret>0){
          for(i=0;i<2;i++){
            if(p[i].revents==POLLIN){
              rec1=recv(p[i].fd,tampon,100,0);
              printf("Taille de données recues %d\n",rec1);
              if(rec1>=0){
                tampon[rec1]='\0';
                printf("Message recu : %s\n",tampon);
              }
            }
         
          }
        }
      }
    }
  }
  return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>

int main() {
  int sock=socket(PF_INET,SOCK_DGRAM,0);
  int ok=1;
  int r=setsockopt(sock,SOL_SOCKET,SO_BROADCAST,&ok,sizeof(ok));
  if(r==0){
    struct addrinfo *first_info;
    struct addrinfo hints;
    memset(&hints, 0, sizeof(struct addrinfo));
    hints.ai_family = AF_INET;
    hints.ai_socktype=SOCK_DGRAM;
    r=getaddrinfo("255.255.255.255","8888",NULL,&first_info);
    if(r==0){
      if(first_info!=NULL){
        struct sockaddr *saddr=first_info->ai_addr;
        char tampon[100];
        int i=0;
        for(i=0;i<=10;i++){
          strcpy(tampon,"MESSAGE ");
          char entier[3];
          sprintf(entier,"%d",i);
          strcat(tampon,entier);
          sendto(sock,tampon,strlen(tampon),0,saddr,(socklen_t)sizeof(struct sockaddr_in));
        }
      }
    }
  }
  return 0;
}

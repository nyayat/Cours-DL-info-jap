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
  sock=socket(PF_INET,SOCK_DGRAM,0);
  struct sockaddr_in address_sock;
  address_sock.sin_family=AF_INET;
  address_sock.sin_port=htons(8888);
  address_sock.sin_addr.s_addr=htonl(INADDR_ANY);
  int r=bind(sock,(struct sockaddr *)&address_sock,sizeof(struct sockaddr_in));
  if(r==0){
    char tampon[100];
    while(1){
      int rec=recv(sock,tampon,100,0);
      tampon[rec]='\0';
      printf("Message recu : %s\n",tampon);
    }
  }
  return 0;
}

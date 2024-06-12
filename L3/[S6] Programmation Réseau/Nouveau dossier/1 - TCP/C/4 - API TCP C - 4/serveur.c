#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>

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
    while(1){
      struct sockaddr_in caller;
      socklen_t size=sizeof(caller);
      int sock2=accept(sock,(struct sockaddr *)&caller,&size);
      if(sock2>=0){
        //Ici on affiche les informations du client
        printf("Port de l'appelant: %d\n",ntohs(caller.sin_port));
        printf("Adresse de l'appelant: %s\n",inet_ntoa(caller.sin_addr));
        char *mess="Yeah!\n";
        send(sock2,mess,strlen(mess)*sizeof(char),0);
        char buff[100];
        int recu=recv(sock2,buff,99*sizeof(char),0);
        buff[recu]='\0';
        printf("Message recu : %s\n",buff);
        close(sock2);
      }   
    }
  }
  return 0;
}    

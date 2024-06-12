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
  struct sockaddr_in adress_sock;
  adress_sock.sin_family = AF_INET;
  adress_sock.sin_port = htons(p);
  inet_aton("127.0.0.1",&adress_sock.sin_addr);
  
  int descr=socket(PF_INET,SOCK_STREAM,0);
  int r=connect(descr,(struct sockaddr *)&adress_sock,
                sizeof(struct sockaddr_in));
  if(r!=-1){
    char buff[100];
    int size_rec=recv(descr,buff,99*sizeof(char),0);
    buff[size_rec]='\0';
    printf("Caracteres recus : %d\n",size_rec);
    printf("Message : %s\n",buff);
    sleep(5);
    char *mess="SALUT!\n";
    send(descr,mess,strlen(mess),0);
    close(descr);
  }
  return 0;
}


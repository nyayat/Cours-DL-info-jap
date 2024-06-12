#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>

int main(int argc,char **argv) {
  if(argc!=2){
    printf("Erreur il faut fournir un numero de port");
    return 0;
  }
  int p=atoi(argv[1]);
  struct addrinfo *first_info;
  struct addrinfo hints;
  hints.ai_family=AF_INET;
  int r=getaddrinfo("localhost",NULL,&hints,&first_info);
  if(r==0){
    struct sockaddr_in *addressin;
    if(first_info!=NULL){
      addressin=(struct sockaddr_in *)first_info->ai_addr;
      addressin->sin_family=AF_INET;
      addressin->sin_port=htons(p);
      int descr=socket(AF_INET,SOCK_STREAM,0);
      int r2=connect(descr,(struct sockaddr *)addressin,sizeof(struct sockaddr_in));
      if(r2!=-1){
        char buff[100];
        int size_rec=recv(descr,buff,99*sizeof(char),0);
        buff[size_rec]='\0';
        printf("Message : %s\n",buff);
        char *mess="SALUT L'AMI!\n";
        send(descr,mess,strlen(mess),0);
        close(descr);
      }
                     
    }else{
      printf("Probleme ne trouve pas localhost");
    }
  }
  
  return 0;
}


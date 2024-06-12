#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>

int main() {
  struct addrinfo *first_info;
  struct addrinfo hints;
  hints.ai_family = AF_INET;
  int r=getaddrinfo("lulu.informatique.univ-paris-diderot.fr",NULL,&hints,&first_info);
  if(r==0){
    struct addrinfo *info=first_info;
    int found=0;
    struct sockaddr *saddr;
    struct sockaddr_in *addressin;
    while(info!=NULL && found==0){
      addressin=(struct sockaddr_in *)info->ai_addr;
      found=1;
      info=info->ai_next;
    }
    if(found==1){
      struct sockaddr_in adress_sock;
      adress_sock.sin_family = AF_INET;
      adress_sock.sin_port = htons(7);
      adress_sock.sin_addr=addressin->sin_addr;
  
      int descr=socket(PF_INET,SOCK_STREAM,0);
      int r2=connect(descr,(struct sockaddr *)&adress_sock,
                sizeof(struct sockaddr_in));
      if(r2!=-1){
        //To be continued...
      }
    }
  }
 
  return 0;
}


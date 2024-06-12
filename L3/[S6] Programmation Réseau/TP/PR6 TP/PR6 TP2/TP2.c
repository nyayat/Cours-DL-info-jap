#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <string.h>


int main(){
  //1.1
  /*uint32_t n=0x01020304;
  if(htonl(n)!=n) printf("little : %d --> %d\n", n, htonl(n));
  else printf("big : %d %d\n", n, htonl(n));*/

  //1.2
  /*uint32_t n=0x01020304;
  char tab[4];
  sprintf(tab, "%d", n);
  for(int i=0; i<4; i++) printf("%x", tab[i]);
  printf("\n");*/

  //2.1
  //host lampe depuis l'ufr
  //ou host lampe.informatique.univ-paris-diderot.fr depuis l'extérieur

  //2.2
  /*int sock=socket(PF_INET, SOCK_STREAM, 0);

  struct sockaddr_in adress;
  adress.sin_family=AF_INET;
  adress.sin_port=htons(13);
  inet_aton("192.168.70.237", &adress.sin_addr);

  int c=connect(sock, (const struct sockaddr *) &adress, sizeof(struct sockaddr_in));
  if(c!=-1){
    char buff[100];
    int sizeR=recv(sock, buff, 99*sizeof(char), 0);
    buff[sizeR]='\0';
    printf("%s\n", buff);
    close(sock);
  }*/

  //3.
  /*int sock=socket(PF_INET, SOCK_STREAM, 0);

  struct sockaddr_in adress;
  adress.sin_family=AF_INET;
  adress.sin_port=htons(7);
  inet_aton("192.168.70.237", &adress.sin_addr);

  int c=connect(sock, (const struct sockaddr *) &adress, sizeof(struct sockaddr_in));
  if(c!=-1){
    for(int i=1; i<11; i++){
      const char *message="Hello";
      send(sock, message, strlen(message), 0);

      char buff[100];
      int sizeR=recv(sock, buff, 99*sizeof(char), 0);
      buff[sizeR]='\0';
      printf("%s%d\n", buff, i);
    }
    close(sock);
  }*/

  //4.
  int sock=socket(PF_INET, SOCK_STREAM, 0);

  struct sockaddr_in adress;
  adress.sin_family=AF_INET;
  adress.sin_port=htons(6668);
  inet_aton("127.0.0.1", &adress.sin_addr);

  int c=connect(sock, (const struct sockaddr *) &adress, sizeof(struct sockaddr_in));
  if(c!=-1){
    for(int i=0; i<4; i++){
      char buff[100];
      int sizeR=recv(sock, buff, 99*sizeof(char), 0);
      buff[sizeR]='\0';
      printf("%s\n", buff);

      char r[sizeR];
      int begin, end=sizeR;
      for(begin=0; begin<sizeR; begin++){
        r[begin]=buff[end-1];
        end--;
      }
      r[begin]='\0';

      send(sock, r, begin, 0);
    }
    close(sock);
  }

  return 0;
}

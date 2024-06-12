#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>

int main() {
  struct in_addr address;
  char *string_address=
      (char *)malloc(sizeof(char)*INET_ADDRSTRLEN); 
  inet_pton(AF_INET,"127.0.0.1",&address);
  inet_ntop(AF_INET,&address,string_address,
           INET_ADDRSTRLEN);
  printf("L'adresse vaut : %s\n",string_address);
  return 0;
}

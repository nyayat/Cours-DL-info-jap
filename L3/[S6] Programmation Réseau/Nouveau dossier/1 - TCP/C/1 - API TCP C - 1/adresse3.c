#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>

int main() {
  struct in6_addr address;   			
  char*string_address=(char*)malloc(
		       sizeof(char)*INET6_ADDRSTRLEN);   	     
  inet_pton(AF_INET6,
     "2a00:1450:400c:c02:0:0:0:93",&address);
  inet_ntop(AF_INET6,&address,string_address,
           INET6_ADDRSTRLEN);
  printf("L'adresse vaut : %s\n",string_address);
  return 0;
}

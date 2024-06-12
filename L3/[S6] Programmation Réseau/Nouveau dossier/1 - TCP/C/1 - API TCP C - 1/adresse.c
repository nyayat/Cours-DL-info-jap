#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main() {
  struct in_addr address;
  char *string_address; 
  inet_aton("127.0.0.1",&address);
  string_address=inet_ntoa(address);
  printf("L'adresse vaut : %s\n",string_address);
  return 0;
}


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

int main() {
  struct hostent* host;
  host=gethostbyname("www.google.com");
  if(host==NULL){
    printf("Unknown\n");
  }
  char **aliases=host->h_aliases;
  while(*aliases!=NULL){
    printf("Alias : %s\n",*aliases);
    aliases++;
  }
  struct in_addr **addresses=(struct in_addr**)host->h_addr_list;
  while(*addresses!=NULL){
    printf("Address : %s\n",inet_ntoa(**addresses));
    addresses++;
  }
  return 0;
}

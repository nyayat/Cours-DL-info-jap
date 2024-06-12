#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

int main() {
  struct addrinfo *first_info;
  struct addrinfo hints;
  memset(&hints, 0, sizeof hints);
  hints.ai_family = AF_INET;
  int r=getaddrinfo("www.google.com",NULL,&hints,&first_info);
  if(r==0){
    struct addrinfo *info=first_info;
    while(info!=NULL){
      struct sockaddr *saddr=info->ai_addr;
      struct sockaddr_in *addressin=(struct sockaddr_in *)saddr;
      struct in_addr address=(struct in_addr) (addressin->sin_addr);
      printf("Address IPv4 : %s\n",inet_ntoa(address));
      info=info->ai_next;
    }   
  }
  freeaddrinfo(first_info);
  return 0;
}

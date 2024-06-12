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
  hints.ai_family = PF_UNSPEC;
  int r=getaddrinfo("www.google.com",NULL,&hints,&first_info);
  if(r==0){
    struct addrinfo *info=first_info;
    while(info!=NULL){
      struct sockaddr *saddr=info->ai_addr;
      if(saddr->sa_family==AF_INET){
        struct sockaddr_in *addressin=(struct sockaddr_in *)saddr;
        struct in_addr address=(struct in_addr) (addressin->sin_addr);
        printf("Address IPv4 : %s\n",inet_ntoa(address));
      }
      if(saddr->sa_family==AF_INET6){
        struct sockaddr_in6 *addressin=(struct sockaddr_in6 *)saddr;
        struct in6_addr address=(struct in6_addr) (addressin->sin6_addr);
        char*string_address=(char*)malloc(
		       sizeof(char)*INET6_ADDRSTRLEN);   	     
        inet_ntop(AF_INET6,&address,string_address,
           INET6_ADDRSTRLEN);
        printf("Address IPv6 : %s\n",string_address);
      }
      info=info->ai_next;
    }   
  }
  freeaddrinfo(first_info);
  return 0;
}

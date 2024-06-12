#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char** argv){
	int sock=socket(PF_INET, SOCK_DGRAM, 0);
	struct addrinfo* first_info;
	struct addrinfo hints;
	memset(&hints, 0, sizeof(struct addrinfo));
	hints.ai_family=AF_INET;
	hints.ai_socktype=SOCK_DGRAM;
	int r=getaddrinfo(argv[2], "1717", &hints, &first_info);
	if(r==0){
		if(first_info!=NULL){
			struct sockaddr* saddr=first_info->ai_addr;
			char tampon[100];
			for(int i=0; i<atoi(argv[1]); i++){
				sendto(sock, NULL, 0, 0, saddr, (socklen_t)sizeof(struct sockaddr_in));
				
                int rec=recv(sock, tampon, 100, 0);
                if(rec==-1){
                    perror("recv");
                    return 0;
                }
                tampon[rec]='\0';
                printf("%s", tampon);
			}
		}
	}
	return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>

//2.1
int main(int argc, char* argv[]){
    if(argc!=2){
        printf("error argc\n");
        exit(1);
    }

    int sock=socket(PF_INET, SOCK_STREAM, 0);
	if(sock==-1){
		printf("error socket\n");
		exit(1);
	}
	
    struct sockaddr_in adress;
    adress.sin_family=AF_INET;
    adress.sin_port=htons(atoi(argv[1]));
    adress.sin_addr.s_addr=htonl(INADDR_ANY);

    int r=bind(sock, (struct sockaddr *)&adress, sizeof(struct sockaddr_in));
    if(r==-1){
        printf("error bind\n");
        exit(1);
    }

    r=listen(sock, 0);
    if(r==-1){
        printf("error listen\n");
        exit(1);
    }

    while(1){
        struct sockaddr_in client;
        socklen_t size=sizeof(client);

        int sock2=accept(sock, (struct sockaddr *)&client, &size);
        if(sock2==-1){
            printf("error accept client\n");
            exit(1);
        }

        srand(time(NULL));
        int number=rand()%65536,  tentatives=20, answer, len;
        char buff[100];

        while((tentatives--)>0){
            len=recv(sock2, buff, 99, 0);
            if(len==-1){
                printf("error receive\n");
                close(sock2);
                exit(1);
            }

            buff[len]='\0';
            answer=atoi(buff);

            if(answer<0 || answer>65535) tentatives++;
            else{
                if(answer==number){
                    sprintf(buff, "GAGNE\n");
                    send(sock2, buff, strlen(buff), 0);
                    close(sock2);
                    return 0;
                }
                /*else if(answer<number) sprintf(buff, "PLUS %d\n", tentatives);
                else if(answer>number) sprintf(buff, "MOINS %d\n", tentatives);*/
                else if(answer<number) sprintf(buff, "PLUS\n");
                else if(answer>number) sprintf(buff, "MOINS\n");
                send(sock2, buff, strlen(buff), 0);
            }
        }
        sprintf(buff, "PERDU, res= %d\n", number);
        send(sock2, buff, strlen(buff), 0);
        close(sock2);
    }
    return 0;
}

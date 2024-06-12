#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

//1.
int main(int argc, char* argv[]){
    if(argc!=2){
        printf("argc!=2\n");
        exit(1);
    }

    int sock=socket(PF_INET, SOCK_STREAM, 0);
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

        char buff[100];
        int len=recv(sock2, buff, 99, 0);
        if(len==-1)){
            printf("error receive\n");
            close(sock2);
            exit(1);
        }

        buff[len]='\0';
        printf("%s", buff);

        send(sock2, buff, len, 0);

        close(sock2);
    }
    return 0;
}
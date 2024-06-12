#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

//3.3
int main(int argc, char* argv[]){
    if(argc!=2){
        printf("argc!=2\n");
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

        char buff[101];
        memset(buff, 0, 101);
        r=recv(sock2, buff, 100, 0);
        if(r==-1){
            printf("error receive filename\n");
            close(sock2);
            exit(1);
        }

        int file=open(buff, O_WRONLY | O_CREAT | O_TRUNC, 700);
        if(file==-1){
            printf("error open file\n");
            close(sock2);
            exit(1);
        }

        sprintf(buff, "DEBUT\n");
        r=send(sock2, buff, strlen(buff), 0);
        if(r==-1){
            printf("error send debut\n");
            close(sock2);
            close(file);
            exit(1);
        }

        r=recv(sock2, buff, 100, 0);
        if(r==-1){
            printf("error receive number iterations\n");
            close(sock2);
            close(file);
            exit(1);
        }
        buff[r]='\0';
        int n=atoi(buff);

        for(int i=0; i<n; i++){
            r=recv(sock2, buff, 100, 0);
            if(r==-1){
                printf("error receive\n");
                close(sock2);
                close(file);
                exit(1);
            }
            buff[r]='\0';

            r=write(file, buff, strlen(buff));
            if(r==-1){
                printf("error write");
                close(sock2);
                close(file);
                exit(1);
            }
        }
        close(file);

        sprintf(buff, "BIEN RECU\n");
        r=send(sock2, buff, strlen(buff), 0);
        if(r==-1){
            printf("error send\n");
            close(sock2);
            exit(1);
        }
        close(sock2);
    }

    return 0;
}
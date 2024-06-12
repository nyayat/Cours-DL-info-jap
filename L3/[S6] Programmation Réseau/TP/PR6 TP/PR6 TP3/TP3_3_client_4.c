#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

//3.4
int main(int argc, char* argv[]){
    if(argc!=3){
        printf("argc!=3\n");
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
    inet_aton("127.0.0.1", &adress.sin_addr);

    int r=connect(sock, (struct sockaddr *)&adress, sizeof(struct sockaddr_in));
    if(r==-1){
        printf("error connect\n");
        close(sock);
        exit(1);
    }

    r=send(sock, argv[2], strlen(argv[2]), 0);
    if(r==-1){
        printf("error send filename\n");
        close(sock);
        exit(1);
    }

    char buff[101];
    r=recv(sock, buff, 100, 0);
    if(r==-1){
        printf("error receive debut\n");
        close(sock);
        exit(1);
    }
    buff[r]='\0';
    printf("%s\n", buff);

    for(int i=0; i<2; i++){
        sprintf(buff, "message numero %d\n", i);
        r=send(sock, buff, strlen(buff), 0);
        if(r==-1){
            printf("error send\n");
            close(sock);
            exit(1);
        }
        sleep(1);
    }
    sprintf(buff, "end\n");
    r=send(sock, buff, strlen(buff), 0);
    if(r==-1){
        printf("error send end\n");
        close(sock);
        exit(1);
    }

    r=recv(sock, buff, 100, 0);
    if(r==-1){
        printf("error receive\n");
        close(sock);
        exit(1);
    }
    buff[r]='\0';
    printf("%s\n", buff);

    close(sock);
    return 0;
}
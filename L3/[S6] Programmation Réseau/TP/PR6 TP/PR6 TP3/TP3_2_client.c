#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>

//2.3
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
    inet_aton("127.0.0.1", &adress.sin_addr);

    int r=connect(sock, (struct sockaddr *)&adress, sizeof(struct sockaddr_in));
    if(r==-1){
        printf("error connect\n");
        exit(1);
    }

    int min=0, max=65535, answer;
    char buff[100];

    while(1){
        answer=(min+max)/2;
        printf("min : %d\nmax : %d\nanswer : %d\n", min, max, answer);
        sprintf(buff, "%d", answer);
        r=send(sock, buff, strlen(buff), 0);
        if(r==-1){
            printf("error send\n");
            close(sock);
            exit(1);
        }

        r=recv(sock, buff, 99, 0);
        if(r==-1){
            printf("error receive\n");
            close(sock);
            exit(1);
        }
        buff[r]='\0';
		printf("%s\n", buff);

        if(strcmp(buff, "GAGNE\n")==0){
            close(sock);
            return EXIT_SUCCESS;
        }
        if(strcmp(buff, "PLUS\n")==0) min=answer;
        else if(strcmp(buff, "MOINS\n")==0) max=answer;
        else{
            close(sock);
            return EXIT_SUCCESS;
        }
    }
}
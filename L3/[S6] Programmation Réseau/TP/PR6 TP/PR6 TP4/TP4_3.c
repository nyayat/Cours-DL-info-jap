#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>

//3.
void* play(void* arg){
    int player1=*((int*)arg);
    int player2=*((int*)arg+sizeof(int));
    free(arg);

    srand(time(NULL));
    int number=rand()%65536, tentatives=10, answer1, answer2, len;
    char buff[100];
    sprintf(buff, "DEBUT\n");
    send(player1, buff, strlen(buff), 0);
    send(player2, buff, strlen(buff), 0);

    while((tentatives--)>0){
        len=recv(player1, buff, 99, 0);
        if(len==-1){
            printf("error receive\n");
            close(player1);
            close(player2);
            return NULL;
        }
        buff[len]='\0';
        answer1=atoi(buff);

        len=recv(player2, buff, 99, 0);
        if(len==-1){
            printf("error receive\n");
            close(player1);
            close(player2);
            return NULL;
        }
        buff[len]='\0';
        answer2=atoi(buff);

        if(answer1==number){
            if(answer2!=number){
                sprintf(buff, "GAGNE\n");
                send(player1, buff, strlen(buff), 0);
                sprintf(buff, "PERDU, res= %d\n", number);
            }
            else{
                sprintf(buff, "MATCH NUL\n");
                send(player1, buff, strlen(buff), 0);
            }
            send(player2, buff, strlen(buff), 0);
            close(player1);
            close(player2);
            return NULL;
        }
        else if(answer2==number){
            sprintf(buff, "GAGNE\n");
            send(player2, buff, strlen(buff), 0);
            sprintf(buff, "PERDU, res= %d\n", number);
            send(player1, buff, strlen(buff), 0);
            close(player1);
            close(player2);
            return NULL;
        }
        else{
            if(answer1<number) sprintf(buff, "PLUS %d\n", tentatives);
            else sprintf(buff, "MOINS %d\n", tentatives);
            send(player1, buff, strlen(buff), 0);
            if(answer2<number) sprintf(buff, "PLUS %d\n", tentatives);
            else sprintf(buff, "MOINS %d\n", tentatives);
            send(player2, buff, strlen(buff), 0);
        }
    }
    sprintf(buff, "PERDU, res= %d\n", number);
    send(player1, buff, strlen(buff), 0);
    send(player2, buff, strlen(buff), 0);
    close(player1);
    close(player2);
    return NULL;
}

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

        int* players=(int*)malloc(2*sizeof(int));
        *players=accept(sock, (struct sockaddr *)&client, &size);
        if(*players==-1){
            printf("error accept client\n");
            free(players);
            exit(1);
        }

        *(players+sizeof(int))=accept(sock, (struct sockaddr *)&client, &size);
        if(*(players+sizeof(int))==-1){
            printf("error accept client 2\n");
            close(*players);
            free(players);
            exit(1);
        }

        pthread_t th;
        pthread_create(&th, NULL, play, players);
    }
    return 0;
}
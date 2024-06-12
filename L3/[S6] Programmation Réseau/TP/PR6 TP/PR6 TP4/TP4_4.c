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
void* play(void* arg);

int main(int argc, char* argv[]){
    if(argc!=3){
        printf("error args : ./game port nbPlayers\n");
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

    int nbPlayers=atoi(argv[2]);
    while(1){
        struct sockaddr_in client;
        socklen_t size=sizeof(client);

        //int* players=(int*)malloc((nbPlayers+1)*sizeof(int)); //ne marche pas...
        int* players=(int*)calloc(nbPlayers*nbPlayers, sizeof(int));
        if(players==NULL){
            printf("error malloc\n");
            exit(1);
        }

        *players=nbPlayers;
        for(int i=1; i<nbPlayers+1; i++){
            *(players+i*sizeof(int))=accept(sock, (struct sockaddr *)&client, &size);
            if(*(players+i*sizeof(int))==-1){
                printf("error accept client\n");
                for(int j=0; j<i; j++) close(*(players+j*sizeof(int)));
                free(players);
                exit(1);
            }
        }
        pthread_t th;
        pthread_create(&th, NULL, play, players);
    }
    return 0;
}

void* play(void* arg){
    int i;
    int nbPlayers=*((int*)arg);
    int players[nbPlayers];
    for(i=0; i<nbPlayers; i++) players[i]=*((int*)arg+sizeof(int)*(i+1));
    free(arg);

    srand(time(NULL));
    int number=rand()%65536, tentatives=10, len;
    int answers[nbPlayers];
    char buff[100];
    sprintf(buff, "DEBUT\n");
    for(i=0; i<nbPlayers; i++) send(players[i], buff, strlen(buff), 0);

    int gagnants[nbPlayers];
    for(i=0; i<nbPlayers; i++) gagnants[i]=-1;
    int lastInd=0;

    while((tentatives--)>0){
        for(i=0; i<nbPlayers; i++){
            len=recv(players[i], buff, 99, 0);
            if(len==-1){
                printf("error receive %d\n", i);
                for(int i=0; i<nbPlayers; i++) close(players[i]);
                return NULL;
            }
            buff[len]='\0';
            answers[i]=atoi(buff);
            if(answers[i]==number) gagnants[lastInd++]=i;
        }

        if(lastInd>0){
            lastInd=0;
            for(i=0; i<nbPlayers; i++){
                if(i==gagnants[lastInd]){
                    lastInd++;
                    sprintf(buff, "GAGNE\n");
                    send(players[i], buff, strlen(buff), 0);
                }
                else{
                    sprintf(buff, "PERDU, res= %d\n", number);
                    send(players[i], buff, strlen(buff), 0);
                }
                close(players[i]);
            }
            return NULL;
        }
        for(i=0; i<nbPlayers; i++){
            if(answers[i]<number) sprintf(buff, "PLUS %d\n", tentatives);
            else sprintf(buff, "MOINS %d\n", tentatives);
            send(players[i], buff, strlen(buff), 0);
        }
    }
    sprintf(buff, "PERDU, res= %d\n", number);
    for(i=0; i<nbPlayers; i++){
        send(players[i], buff, strlen(buff), 0);
        close(players[i]);
    }
    return NULL;
}
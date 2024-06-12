#include "client.h"

int main(int argc, char* argv[]){
    //verification du nombre d'arguments
    if(argc!=3){
        perror("argc : ./client1 adresse port");
        exit(1);
    }

    for(int i=0; i<5; i++){
        //creation de la socket de communication
        int sock=socket(PF_INET, SOCK_STREAM, 0);
        if(sock==-1){
            perror("socket");
            exit(1);
        }

        struct sockaddr_in address;
        address.sin_family=AF_INET;
        address.sin_port=htons(atoi(argv[2]));
        inet_aton(argv[1], &address.sin_addr);

        //connexion au serveur
        int r=connect(sock, (struct sockaddr*)&address, sizeof(struct sockaddr_in));
        if(r==-1){
            perror("connect");
            close(sock);
            exit(1);
        }

        //echange avec le serveur
        char pseudo[MAX_NAME];
        char buffer[100];
        srand(time(NULL)*i);

        //envoie du pseudo
        sprintf(pseudo, "client %d ", i);
        int len=send(sock, pseudo, MAX_NAME, 0);
        if(len==-1){
            perror("send pseudo");
            close(sock);
            exit(1);
        }

        //reception du hello
        len=recv(sock, buffer, 99, 0);
        if(len==-1){
            perror("recv hello");
            close(sock);
            exit(1);
        }
        buffer[len]='\0';
        printf("%s\n", buffer);

        //envoie de la valeur
        int val=rand()%65536;
        sprintf(buffer, "INT %d", val);
        printf("%s\n", buffer);
        len=send(sock, buffer, strlen(buffer), 0);
        if(len==-1){
            perror("send number");
            close(sock);
            exit(1);
        }

        //reception du message de validation
        len=recv(sock, buffer, 99, 0);
        if(len==-1){
            perror("recv okay");
            close(sock);
            exit(1);
        }
        buffer[len]='\0';
        printf("%s\n", buffer);

        printf("\n");
        close(sock);
    }
    return 0;
}
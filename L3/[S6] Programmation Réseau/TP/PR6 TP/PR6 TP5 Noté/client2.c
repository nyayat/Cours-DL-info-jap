#include "client.h"

int main(int argc, char* argv[]){
    //verification du nombre d'arguments
    if(argc!=3){
        perror("argc : ./client2 adresse port");
        exit(1);
    }

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

    //envoie du pseudo
    const char* pseudo="client MAX";
    int len=send(sock, pseudo, MAX_NAME, 0);
    if(len==-1){
        perror("send pseudo");
        close(sock);
        exit(1);
    }

    char buffer[100];

    //reception du hello
    len=recv(sock, buffer, 99, 0);
    if(len==-1){
        perror("recv hello");
        close(sock);
        exit(1);
    }
    buffer[len]='\0';
    printf("%s\n", buffer);

    //envoie de la demande
    sprintf(buffer, "MAX");
    printf("%s\n", buffer);
    len=send(sock, buffer, strlen(buffer), 0);
    if(len==-1){
        perror("send max");
        close(sock);
        exit(1);
    }

    //reception du message de la reponse
    len=recv(sock, buffer, 99, 0);
    if(len==-1){
        perror("recv answer max");
        close(sock);
        exit(1);
    }
    buffer[len]='\0';
    printf("%s\n", buffer);

    close(sock);
    return 0;
}
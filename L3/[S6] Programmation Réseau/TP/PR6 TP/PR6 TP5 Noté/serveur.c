#include "serveur.h"

//variables globales
pthread_mutex_t verrou=PTHREAD_MUTEX_INITIALIZER;
char pseudoMax[MAX_NAME+1];
uint32_t ipMax;
uint16_t max;

int main(int argc, char* argv[]){
    //verification du nombre d'arguments
    if(argc!=2){
        perror("argc : ./serveur port");
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
    address.sin_port=htons(atoi(argv[1]));
    address.sin_addr.s_addr=htonl(INADDR_ANY);

    //liaison avec un port
    int r=bind(sock, (struct sockaddr *)&address, sizeof(struct sockaddr_in));
    if(r==-1){
        perror("bind");
        exit(1);
    }

    //ecoute au niveau du port
    r=listen(sock, 0);
    if(r==-1){
        perror("listen");
        exit(1);
    }

    while(1){
        struct sockaddr_in client;
        socklen_t size=sizeof(client);

        //preparation des paramatres a envoyer aux threads (cf. ligne 71)
        int* args=(int*)malloc(sizeof(int)+sizeof(uint16_t));
        if(args==NULL){
            perror("malloc args");
            exit(1);
        }

        //prise en charge des clients
        *args=accept(sock, (struct sockaddr*)&client, &size);
        if(*args==-1){
            perror("accept client");
            free(args);
            exit(1);
        }

        //recuperation de l'adresse ip
        *(args+sizeof(int))=client.sin_addr.s_addr;

        //creation de concurrence
        pthread_t th;
        pthread_create(&th, NULL, server, args);
    }
    return 0;
}

//args de la forme : int socket_client, uint32_t adresseIP_client
void* server(void* args){
    //socket du client
    int client=*((int*)args);
    uint32_t ip=*((int*)args+sizeof(int));
    free(args);
    
    //lecture du pseudo
    char pseudo[MAX_NAME+1]; //+1 pour mettre \0
    int len=recv(client, pseudo, MAX_NAME, 0);
    if(len==-1){
        perror("recv pseudo");
        close(client);
        return NULL;
    }
    pseudo[MAX_NAME]='\0';

    //envoie du message hello
    char buffer[100];
    sprintf(buffer, "HELLO %s", pseudo);
    len=send(client, buffer, strlen(buffer), 0);
    if(len==-1){
        perror("send hello");
        close(client);
        return NULL;
    }

    //lecture de la demande du client
    len=recv(client, buffer, 4, 0);
    if(len==-1){
        perror("recv request client");
        close(client);
        return NULL;
    }
    buffer[3]='\0'; //pour enlever le \n (cas MAX) ou l'espace (cas INT)

    //cas demande du max
    if(strcmp(buffer, "MAX")==0){
        //max non initialise
        if(strcmp(pseudoMax, "")==0){
            sprintf(buffer, "%s", "NOP");
            len=send(client, buffer, strlen(buffer), 0);
            if(len==-1) perror("send nop");
        }

        //envoie du max
        else{
            sprintf(buffer, "REP%s%d%d", pseudoMax, ipMax, max);
            len=send(client, buffer, strlen(buffer), 0);
            if(len==-1) perror("send max");
        }
    }

    //cas envoie d'un int
    else if(strcmp(buffer, "INT")==0){
        //reception de la valeur
        len=recv(client, buffer, 99, 0);
        if(len==-1){
            perror("recv number");
            close(client);
            return NULL;
        }
        buffer[len]='\0';

        //comparaison pour savoir si max
        uint16_t value=atoi(buffer);
        pthread_mutex_lock(&verrou); //bloque l'acces en concurrence
        if(value>=htons(max)){
            max=htons(value);
            sprintf(pseudoMax, "%s", pseudo);
            ipMax=ip;
        }
        pthread_mutex_unlock(&verrou); //debloque

        //envoie du message ok
        sprintf(buffer, "%s", "INTOK");
        len=send(client, buffer, strlen(buffer), 0);
        if(len==-1) perror("send int ok");
    }

    close(client);
    return NULL;
}
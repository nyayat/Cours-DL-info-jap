#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

char fortune[512];

int getNbLines(const char* filename){
    FILE* file=fopen(filename, "r");
    if(file==NULL){
        perror("file not exists");
        return 0;
    }

    int res=0;
    for(int c=getc(file); c!=EOF; c=getc(file)){
        if(c=='%') res++;
    }

    fclose(file);
    printf("%d", res);
    return res;
}

void getFortune(){
    const char* filename="/usr/share/games/fortunes/fortunes";
    FILE* file=fopen(filename, "r");
    if(file!=NULL){
        srand(time(NULL));
        int nbLines=getNbLines(filename);
        int line=rand()%nbLines;

        //trouve la bonne citation
        while(--line!=0) for(int c=getc(file); c!='%'; c=getc(file));
        //met la citation dans le buffer a envoyer
        memset(fortune, 0, sizeof(fortune));
        for(int count=0, c=getc(file); c!='%'; c=getc(file), count++) fortune[count]=c;
    }
    fclose(file);
}

int main(){
	int sock=socket(PF_INET, SOCK_DGRAM, 0);
	struct sockaddr_in address_sock;
	address_sock.sin_family=AF_INET;
	address_sock.sin_port=htons(1717);
	address_sock.sin_addr.s_addr=htonl(INADDR_ANY);

	int r=bind(sock, (struct sockaddr *)&address_sock, sizeof(struct sockaddr_in));
    if(r!=0){
        perror("bind");
        return 0;
    }
    
    struct sockaddr_in emet;
	socklen_t emet_len=sizeof(emet);
    char tampon[100];
    while(1){
        int rec=recvfrom(sock, tampon, 100, 0, (struct sockaddr*)&emet, &emet_len);
        if(rec==-1){
            perror("recvfrom");
            return 0;
        }
		tampon[rec]='\0';

        getFortune();
        sendto(sock, fortune, strlen(fortune), 0, (struct sockaddr*)&emet, emet_len);
    }

	return 0;
}
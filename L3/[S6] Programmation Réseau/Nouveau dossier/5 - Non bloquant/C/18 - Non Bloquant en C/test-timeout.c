#include <strings.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/select.h>

int main(void)
{
    struct timeval tv;
    fd_set initial;
    int ret=0;
    tv.tv_sec = 2;
    tv.tv_usec = 500000;
    FD_ZERO(&initial);
    FD_SET(STDIN_FILENO, &initial);
    char mess[2];
    while(1){
      fd_set rfds;
      FD_COPY(&initial,&rfds);
      ret=select(STDIN_FILENO+1, &rfds, NULL, NULL, &tv);
      printf("Valeur de retour de select : %d\n",ret);
      if (FD_ISSET(STDIN_FILENO, &rfds)){
        printf("On appuie sur une touche\n");
        read(STDIN_FILENO,mess,1);
        mess[1]='\0';
        printf("Touche %s\n",mess);
      } else {
        printf("Timed out.\n");
        return 0;
      }
    }
    return 0;
} 

#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <signal.h>

int main(void){
  int fd[2];
  long int n;

  /* pour ignorer le signal SIGPIPE (vu plus tard dans le semestre) */
  /* 
  struct sigaction act;
  act.sa_handler = SIG_IGN;
  sigemptyset (&act.sa_mask);
  act.sa_flags = 0;
  sigaction(SIGPIPE, &act, NULL);
  */
  
  pipe(fd);
  close(fd[0]);

  n = write(fd[1], "c", sizeof(char));

  if (n == -1) perror("write");
  else printf("retour de write : %d\n", n);
 
  return 0;
}

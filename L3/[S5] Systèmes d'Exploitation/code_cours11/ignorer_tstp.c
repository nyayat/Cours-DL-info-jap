#include <signal.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>


int main(void){
  struct sigaction action;
  memset(&action, 0, sizeof(struct sigaction));
  action.sa_handler = SIG_IGN;

  dprintf(STDERR_FILENO, "processus de pid %d lancé\n", getpid());

  sigaction(SIGTSTP, &action, NULL);
 
  while(1)
    pause();
}

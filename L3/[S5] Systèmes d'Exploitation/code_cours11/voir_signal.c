#include <signal.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>

void affiche(int sig) {
  dprintf(STDERR_FILENO, "signal %d reçu par %d\n", sig, getpid());
}

int main(void){
  struct sigaction action;
  memset(&action, 0, sizeof(struct sigaction));
  action.sa_handler = affiche;

  dprintf(STDERR_FILENO, "processus de pid %d lancé\n", getpid());

  for (int i=1; i<NSIG; i++) {
    sigaction(i, &action, NULL);
  }

  while(1)
    pause();
}

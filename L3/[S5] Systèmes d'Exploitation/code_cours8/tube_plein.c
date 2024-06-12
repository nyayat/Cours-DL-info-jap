#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <limits.h>

#define SIZE 1 << 15

char buf[SIZE];

void pass(int signal) {}

void install_handler() {
    struct sigaction act;
    act.sa_handler = pass;
    sigemptyset (&act.sa_mask);
    act.sa_flags = 0;
    sigaction(SIGUSR1, &act, NULL);
}

void filling(int fd) {
  int n, total = 0;
  /* n = write(fd, buf, SIZE); total += n;
  printf("ai écrit %d caractères (total %d)\n", n, total);
  n = write(fd, buf, PIPE_BUF/2); total += n;
  printf("ai écrit %d caractères (total %d)\n", n, total);
  */
  while(1) {
    n = write(fd, buf, SIZE); total += n;
    /* n = write(fd, buf, PIPE_BUF); total += n; */
    printf("ai écrit %d caractères (total %d)\n", n, total);
  }
}

int main(void){
  int pipefd[2];
  int n;

  if (pipe(pipefd) == -1) {
    perror("pipe");
    exit(1);
  }

  switch (fork()) {
  case -1 :
    perror("fork");
    exit(1);
  case 0 : // écrivain
    close(pipefd[0]);
    printf("pid écrivain = %d\n", getpid());
    // printf("capacité totale du tube : %d\n", 2*SIZE);
    filling(pipefd[1]);
    printf("écriture finie");
    break;
  default: // lecteur
    close(pipefd[1]);
    printf("pid lecteur = %d\n", getpid());
    install_handler();
    pause(); // envoyer SIGUSR1 pour débloquer le processus
    n = read(pipefd[0], buf, SIZE);
    printf("ai lu %d caractères\n", n);
    pause();
  }
}

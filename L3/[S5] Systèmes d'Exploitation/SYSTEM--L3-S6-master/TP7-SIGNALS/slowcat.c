#include <signal.h>
#include <stdbool.h>
#include <stdio.h>
#include <sys/ioctl.h>
#include <unistd.h>

#include "try.h"

static volatile sig_atomic_t gotAlarm = false;
static size_t chucksize = 160;
static void handler(int sig) {
  gotAlarm = true;
  alarm(1); // Set next alarm
}

static bool copy_chunk(){
	char buf[chucksize];
	size_t remaining = chucksize;
		  while (remaining > 0) {
    ssize_t rdLen = try(read(STDIN_FILENO, buf, remaining));
    if (rdLen == 0) {
      return true;
    }
    remaining -= rdLen;
    char *bufPtr = buf;
    while (rdLen > 0) {
      ssize_t wrLen = try(write(STDOUT_FILENO, bufPtr, rdLen));
      rdLen -= wrLen;
      bufPtr += wrLen;
    }
  }
  return false;
}


int main(int argc, char const *argv[])
{
	struct sigaction newact;
	struct sigaction sigold;

	char buf[BUFSIZ];
	newact.sa_handler = handler;
	sigemptyset(&newact.sa_mask);
	newact.sa_flags = SA_RESTART;

	sigaction(SIGALRM, &newact, &sigold);
	alarm(1);

	while(true){
		    bool reachedEOF = copy_chunk();
    if (reachedEOF) {
      exit(EXIT_SUCCESS);
    }

		while(!gotAlarm){
			pause();
		}
		gotAlarm = false;
	}
	return 0;
}
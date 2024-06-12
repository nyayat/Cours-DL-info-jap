#include <signal.h>
#include <stdbool.h>
#include <stdio.h>
#include <sys/ioctl.h>
#include <unistd.h>

#include "try.h"

static size_t totlen = 0;
static size_t prevlen = 0;
void handler(int sig){
	printf("%ld B/s\n", totlen - prevlen);
	prevlen = totlen;
	alarm(1);
 	fflush(stdout);

}
int main(int argc, char const *argv[])
{
	struct sigaction newact;
	struct sigaction sigold;

	newact.sa_handler = handler;
	sigemptyset(&newact.sa_mask);
	newact.sa_flags = SA_RESTART;

	sigaction(SIGALRM, &newact, &sigold);
	ssize_t len;
	char buf[BUFSIZ];

	alarm(1);
	while((len = read(STDIN_FILENO, buf, sizeof(buf))) > 0){
		totlen += len;
	}
	return 0;
}
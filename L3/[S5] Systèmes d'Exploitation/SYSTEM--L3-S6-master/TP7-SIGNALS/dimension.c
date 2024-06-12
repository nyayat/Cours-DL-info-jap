#include <signal.h>
#include <stdbool.h>
#include <stdio.h>
#include <sys/ioctl.h>
#include <unistd.h>

#include "try.h"

static volatile sig_atomic_t gotWINCH = false;
static struct sigaction newact;
static struct sigaction oldact;
static void handler(int sig) {
  gotWINCH = true;
}

static void print_w(){
	struct winsize ws;
	try(ioctl(STDIN_FILENO, TIOCGWINSZ, &ws));

	printf("X: %ld Y: %ld", ws.ws_row, ws.ws_col); // Fails if stdin is not a terminal
  fflush(stdout);

}
int main(int argc, char const *argv[])
{
	newact.sa_handler = handler;
	sigemptyset(&newact.sa_mask);
	newact.sa_flags = 0;

	print_w();
	sigaction(SIGWINCH, &newact, &oldact);
	while (true) {\
		pause();
				if (gotWINCH){
			gotWINCH = false;
			print_w();
		}
	}
	return 0;
}

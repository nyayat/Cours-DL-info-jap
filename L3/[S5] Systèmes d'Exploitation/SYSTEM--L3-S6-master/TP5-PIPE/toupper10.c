#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>


void handler_sucess(int sig){
	_exit(EXIT_SUCCESS);
}
int main(int argc, char const *argv[])
{
	char c;
	pid_t n;
	int len;
	int p[2];
	signal(SIGCHLD, handler_sucess);
	pipe(p);
	switch(n = fork()){
		case 0: // Child : reader
			close(p[1]);
			for (int i = 0; i < 10 && (read(p[0], &c, 1)) == 1; i++){
				write(STDOUT_FILENO, &c, 1);
			}
			break;
		default: // Parent : writer
			close(p[0]);
			while(read(STDIN_FILENO, &c , 1) == 1){
				c = toupper(c);
				write(p[1], &c, 1);
			}
			
	}
	return 0;
}




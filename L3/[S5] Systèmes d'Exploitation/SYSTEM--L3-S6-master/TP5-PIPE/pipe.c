#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>

int main(int argc, char const *argv[])
{
	char c;
	pid_t n;
	int len;
	int p[2];
	pipe(p);
	switch(n = fork()){
		case 0:
			close(p[0]);
			while(read(STDIN_FILENO, &c , 1) == 1){
				c = toupper(c);
				write(p[1], &c, 1);
			}
			break;
		default:
			close(p[1]);
			while(read(p[0], &c , 1) == 1){
				write(STDOUT_FILENO, &c, 1);
			}
			
	}
	return 0;
}




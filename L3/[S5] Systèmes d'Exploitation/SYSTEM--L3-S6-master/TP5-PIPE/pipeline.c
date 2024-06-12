#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>

int main(int argc, char const *argv[])
{
	pid_t n;
	int p[2];
	if (argc < 2 ){
		fprintf(stderr, "Usage: %s CMD1 [CMD2 [CMD3 ...]]\n", argv[0]);
   		exit(EXIT_FAILURE);
   	}

	pipe(p);
	switch(n = fork()){
		case 0: // CHILD : first 
			dup2(p[1], STDOUT_FILENO);
			close(p[1]);
			close(p[0]);
			execlp(argv[1], argv[1], (char *) NULL);

		default: // PARENT : second
			close(p[1]);
			
	}
	return 0;
}

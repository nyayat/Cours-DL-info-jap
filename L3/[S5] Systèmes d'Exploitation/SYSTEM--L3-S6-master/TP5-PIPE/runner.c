#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>

int main(int argc, char *argv[])
{
	pid_t n;
	int dev_null;
	int status;
	if (argc < 2) {
   	 fprintf(stderr, "Usage: %s COMMAND\n", argv[0]);
  	  exit(EXIT_FAILURE);
  	}

	switch (n = fork()){
		case 0:
			dev_null = open("/dev/null", O_WRONLY);
			dup2(dev_null,STDOUT_FILENO);
			dup2(dev_null, STDERR_FILENO);
			close(dev_null);
			execvp(argv[1], argv+1);
		default:
			waitpid(n, &status, 0);
	}
	if (status >= 0)
		printf("OK\n");
	else
		printf("ERREUR\n");
	return 0;
}
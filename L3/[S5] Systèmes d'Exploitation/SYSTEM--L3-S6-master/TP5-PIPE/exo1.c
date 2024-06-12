#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>

int main()
{
	int fd = open("result", O_CREAT | O_WRONLY | O_TRUNC, 0644);
	dup2(fd, STDOUT_FILENO);
	close(fd);
	execlp("ls", "ls", "-l", NULL);
	return 0;
}
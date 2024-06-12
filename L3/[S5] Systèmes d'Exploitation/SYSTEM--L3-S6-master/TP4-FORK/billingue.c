#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
int main()
{
	pid_t n;
	char  pid_number[50];
	int len = 0;

	switch(n = fork()){
		case 0 : /* CHILD */
			len += sprintf(pid_number, "I am the child, my pid is %d", getpid());
			break;
		default : /* PARENT */
			len += sprintf(pid_number, "I am the parent, my pid is %d", getpid());
			break;
	}
	write(1, pid_number, len);
	puts("");
	return 0;
}
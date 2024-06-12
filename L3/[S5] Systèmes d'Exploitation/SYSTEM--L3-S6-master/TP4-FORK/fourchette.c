#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main(int argc, char const *argv[])
{
	char* check;
	char  pid_number[100];
	int len = 0;
	pid_t n;
	long i;
	if (argc == 1)
		i = 0;
	else{
		i = strtol(argv[argc - 1], &check, 10);
		if (!strcmp(argv[argc -1], check))
			i = 0;

	}
	 
	if (i < 20){
		switch(n = fork()){
			case 0 :
				len += sprintf(pid_number, "Je suis le numero %ld, my pid is %d et celui de mon pere le %d", i, getpid(), getppid());
				write(1, pid_number, len);
				sprintf(check, "%ld", i+1);
				execl(argv[0], argv[0], check, NULL);
			default :
							puts("");

					while (1) pause();


		}
	}
}

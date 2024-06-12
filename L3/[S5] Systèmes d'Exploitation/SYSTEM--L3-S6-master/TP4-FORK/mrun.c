#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

/* failed */
int main(int argc, char const *argv[])
{
	pid_t n;
	char envname[100] = "RUN_0";
	int i;
	for(i = 0 ; getenv(envname) != NULL; sprintf(envname, "RUN_%d", ++i)){
		switch(n = fork()){
			case 0: /*CHILD*/
				execvp(getenv(envname), argv+1);
			default :
				printf("%s", argv+2);
	}
	}
	return 0;
}
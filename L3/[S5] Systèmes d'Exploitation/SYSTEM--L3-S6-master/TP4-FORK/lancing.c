#include <unistd.h>
#include <stdio.h>

int main()
{
	pid_t n;

	switch(n = fork()){
		case 0: /* CHILD */
			switch(n = fork()){
				case 0 :
					execlp("free", "free", NULL);

				default:
					execlp("ps", "ps", NULL);

			}
		default: /* PARENT */
			execlp("ls", "ls", NULL);
				
	}

	return 0;
}
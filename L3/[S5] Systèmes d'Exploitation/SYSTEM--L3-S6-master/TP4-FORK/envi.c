#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
int main(int argc, char const *argv[])
{
	
	char envname[100] = "RUN_0";
	int i;
	for(i = 0 ; getenv(envname) != NULL; sprintf(envname, "RUN_%d", ++i)){
		printf("%s ", getenv(envname));
	}
	return 0;
}
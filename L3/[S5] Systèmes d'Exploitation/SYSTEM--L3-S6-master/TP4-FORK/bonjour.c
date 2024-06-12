#include <stdio.h>
#include <unistd.h>
int main()
{

	printf("Bonjour!\n");
	fork();
	printf("Aurevoir\n");
	return 0;
}
#include <stdio.h>
#include <unistd.h>
#include <string.h>
// #include "try.h"
int main (int argc, char** argv){
	int i, ch, end = '\n';
	const char* sep = " ";
	while((ch = getopt(argc, argv, "s n S:")) != -1){
		switch(ch){
			case 'n':
				sep = 0;break;
			case 's':
				end = 0; break;
			case 'S':
				strcpy(sep, optarg);

		}
	}
	for (i = optind; i < argc; i++)
		printf("%s%s", argv[i], sep);
 	printf("%c", end);
}
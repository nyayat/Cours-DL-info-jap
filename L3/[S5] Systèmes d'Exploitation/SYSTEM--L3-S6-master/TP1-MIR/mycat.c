#include <stdio.h>
// #include "try.h"
int main (int argc, char** argv){
	char c;
	setvbuf(stdin, NULL, _IONBF, sizeof(char));
	setvbuf(stdout, NULL, _IONBF, sizeof(char));  
	//Bufferiser : faire tout en meme temps au lieu de char par char 

	for (; (c = fgetc(stdin)) != EOF;)
		fputc(c, stdout);

	return 0;
 
}
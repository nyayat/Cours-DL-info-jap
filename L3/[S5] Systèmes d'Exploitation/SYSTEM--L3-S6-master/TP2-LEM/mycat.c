#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int mycat_files(int in, int out){
	char message[4096];
	while((bytes = read(in, message, sizeof(message))) > 0){
		if (write(out, message, bytes) != bytes)
			return -1;
	}
	return 0;
}

int main(int argc, char const *argv[])
{
	unsigned int i;
	int file;
	const unsigned int stdout = 1;
	const unsigned int stdin = 0;
	if( argc == 1)
		return mycat_files(stdin, stdout);

	for(i = 1; i < argc; i++){
		if((file = open(argv[i], O_RDONLY)) > 0){
			if (mycat_files(file, stdout) < 0)
				return -1;
		}
		
		else {
			/* error message for file*/
		}
	}
	
}

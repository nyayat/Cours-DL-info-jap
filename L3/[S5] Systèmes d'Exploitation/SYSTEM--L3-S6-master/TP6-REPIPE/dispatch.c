#include <stdio.h>
#include <string.h>
#include <unistd.h>

static struct {
	const char* name;
	int in;
	int out;
} proc[] = {{"add", 0, 0}, {"sub", 0, 0},
            {"mul", 0, 0}, {"div", 0, 0},
            {NULL, 0, 0}};

static void start_proc(){
	for (int i = 0; proc[i].name; i++){
		int pipIn[2], pipOut[2];
		pipe(pipIn);
		pipe(pipOut);
		switch(fork()){
			case 0: //Child : open
				for(int j = 0; j < i; j++){ //close previous
					close(proc[j].in);
					close(proc[j].out);
				}
				dup2(pipIn[0], STDIN_FILENO);
				dup2(pipOut[1], STDOUT_FILENO);
				close(pipIn[0]);
				close(pipIn[1]);
				close(pipOut[0]);
				close(pipIn[1]);
				execl(proc[i].name, proc[i].name, (char*) NULL);
			default :
				close(pipIn[0]);
				close(pipOut[1]);
				proc[i].in = pipIn[1];
				proc[i].out = pipOut[0];

		}
	}
}

static void dispatch(){
	char buf[BUFSIZ];
	while(fgets(buf, sizeof(buf), stdin)){
		char* requestName = strtok(buf, " \t\n");
		if (!requestName)
			requestName = "";
		char* request = strtok(NULL, "");
		if (!request || !strchr(request, '\n'))
			request = "\n";
		if (!strcmp(requestName, "q"))
			break;
		for (int i = 0; proc[i].name; i++){

			if (strcmp(requestName, proc[i].name) == 0){
				write(proc[i].in, request, strlen(request));
        		ssize_t len = read(proc[i].out, buf, sizeof(buf));
        		write(STDOUT_FILENO, buf, len);

        		break;
			}

		}

	}
}
int main(int argc, char const *argv[])
{
	start_proc();
	dispatch();

	return 0;
}
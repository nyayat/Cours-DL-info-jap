/*
 ============================================================================
 Author      : Leonard (Lenny) NAMOLARU
============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> // pause()
#include <signal.h>
#include <time.h>

#define NB_FILS 9

int counter;

void handler (int sig) {
	printf ("pid : %d , valeur du compteur : %d \n", getpid(), counter);
	return;
}

void handler2 (int sig) {
	printf ("DEBUT pid : %d  \n", getpid());
	return;
}


int main(void) {

	 struct sigaction str;
	 str.sa_handler = handler;
	 sigemptyset( &str.sa_mask );
	 str.sa_flags = 0;

	 // int sigaction( int sig, const struct sigaction *act, struct sigaction *oldact)
	 if( sigaction( SIGINT, &str, NULL) < 0) {
		 perror("sigaction()");
		 exit(1);
	 }

	 str.sa_handler = handler2;
	 // int sigaction( int sig, const struct sigaction *act, struct sigaction *oldact)
	 if( sigaction( SIGUSR1, &str, NULL) < 0) {
		 perror("sigaction()");
		 exit(1);
	 }


	int i, resultat;
	pid_t pid;
	int tous_les_pid_des_fils[NB_FILS];
	for(i = 0 ; i < NB_FILS ; i++) {
		pid = fork();
		if(pid == -1){
			perror("fork");
			exit(1);
		}

		if (pid > 0)
			tous_les_pid_des_fils[i] = pid;
		else
			break;
	}

	if(pid > 0) {



		int i;
		for(i = 0 ; i < NB_FILS ; i++) {
			resultat = kill(tous_les_pid_des_fils[i], SIGUSR1);
			if(resultat == -1){
				perror("kill");
				exit(1);
			}
		}

		counter = 0;

		// void srand (unsigned int seed)
		srand( time(NULL) ); // pid_t getpid (void)

		while (1) {
			int min = 1, max = 3;
			// int rand (void)
			int sec = (rand() % (max - min +1)) + min;
			sleep(sec);
			counter++;
		}

	} else {
		pause();
		counter = 0;

		// void srand (unsigned int seed)
		srand( time(NULL) ); // pid_t getpid (void)

		while (1) {
			int min = 1, max = 3;
			// int rand (void)
			int sec = (rand() % (max - min +1)) + min;
			sleep(sec);
			counter++;
		}

		return 0;
	}


	return EXIT_SUCCESS;
}

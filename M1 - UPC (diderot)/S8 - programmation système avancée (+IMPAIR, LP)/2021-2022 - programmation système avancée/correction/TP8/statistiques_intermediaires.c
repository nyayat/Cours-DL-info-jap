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

#define N 100


int tab[N];


int maxTabI (int* t, int n) {
	int max = t[0];
	int maxI = 0;

	int i;
	for(i = 1 ; i < n ; i++) {
		if(t[i] > max){
			max = t[i];
			maxI = i;
		}
	}

	return maxI;
}

int minTabI (int* t, int n) {
	int min = t[0];
	int minI = 0;

	int i;
	for(i = 1 ; i < n ; i++) {
		if(t[i] < min){
			min = t[i];
			min = i;
		}
	}

	return minI;
}

// fréquences extrémales
void handler (int sig) {
	int min_num = minTabI (tab, N);
	int max_num = maxTabI (tab, N);

	printf ("fréquences extrémales : MAX : num = %d , freq = %d , MIN : num = %d , freq = %d \n", max_num + 1, tab[max_num], min_num + 1, tab[min_num]);
	return;
}

void handler2 (int sig) {
	int min_num = minTabI (tab, N);
	int max_num = maxTabI (tab, N);

	printf ("fréquences extrémales : MAX : num = %d , freq = %d , MIN : num = %d , freq = %d \n", max_num + 1, tab[max_num], min_num + 1, tab[min_num]);

	int i;
	for(i = 0 ; i < N ; i++)
		printf("num = %d , freq = %d \n", i + 1, tab[i]);

	exit(0);
}


/*
 * Écrire un programme qui lance deux processus;
 *
 * le fils écrit des entiers aléatoires (entre 1 et 100) dans un tube, sur lequel le père lit en boucle.
 *
 * Chacun établit des statistiques des entiers envoyés ou reçus.
À chaque signal SIGINT reçu, chacun affiche les fréquences
extrémales observées.  S'il reçoit SIGTERM, il affiche un
compte-rendu complet puis termine. Faire en sorte que les compte-rendus
finaux concordent. Faire également en sorte qu'en cas de décès brutal
de l'un des deux processus, l'autre termine proprement en affichant
son compte-rendu (penser à SIGCHLD et SIGPIPE).
 */

int main(void) {
	int filedes[2];
	int result = pipe(filedes);
	if(result == -1){
		perror("pipe");
		exit(1);
	}


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
	 if( sigaction( SIGTERM, &str, NULL) < 0) {
		 perror("sigaction()");
		 exit(1);
	 }

	 str.sa_handler = handler2;
	 // int sigaction( int sig, const struct sigaction *act, struct sigaction *oldact)
	 if( sigaction( SIGCHLD, &str, NULL) < 0) {
		 perror("sigaction()");
		 exit(1);
	 }

	 str.sa_handler = handler2;
	 // int sigaction( int sig, const struct sigaction *act, struct sigaction *oldact)
	 if( sigaction( SIGPIPE, &str, NULL) < 0) {
		 perror("sigaction()");
		 exit(1);
	 }




	pid_t pid = fork();
	if(pid == -1){
		perror("fork");
		exit(1);
	}

	if(pid == 0) {
		close(filedes[0]);

		// void srand (unsigned int seed)
		srand( time(NULL) ); // pid_t getpid (void)

		while (1) {
			int min = 1, max = 100;
			// int rand (void)
			int num = (rand() % (max - min +1)) + min;

			// ssize_t write (int filedes, const void *buffer, size_t size)
			result = write(filedes[1], &num, sizeof(num));
			if(result == -1){
				perror("write");
				exit(1);
			}

			tab[num - 1]++;

		}
	} else {
		close(filedes[1]);

		int i;
		for(i = 0 ; i < N ; i++)
			tab[i] = 0;

		while (1) {
			int buffer;
			// ssize_t write (int filedes, const void *buffer, size_t size)
			result = read(filedes[0], &buffer, sizeof(buffer));
			if(result == -1){
				perror("read");
				exit(1);
			}

			tab[buffer - 1]++;
		}

	}

	return EXIT_SUCCESS;
}


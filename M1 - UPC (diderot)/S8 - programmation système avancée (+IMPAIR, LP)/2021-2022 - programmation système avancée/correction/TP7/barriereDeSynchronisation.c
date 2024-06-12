/*
 ============================================================================
 Name        : barriereDeSynchronisation.c
 Author      : Leonard (Lenny) NAMOLARU
  gcc barriereDeSynchronisation.c -pthread -obarriereDeSynchronisation
 ============================================================================
 */
#include <stdlib.h> // rand(), srand(), atoi(), exit(), EXIT_SUCCESS, EXIT_FAILURE
#include <unistd.h> // fork(), getpid(), sleep()
#include <stdio.h> // fprintf(), perror()
#include <sys/mman.h> // mmap()
#include <pthread.h> // pthread_mutexattr_init(), pthread_mutexattr_setpshared(), pthread_mutex_init()
#include <sys/wait.h> // wait()
#include <errno.h>
#include <string.h>

/**
 * un type barriere_t avec :
 * - un mutex servant à protéger les parties critiques du code,
 * - le nombre de processus qui ne sont pas encore arrivés à la barrière (au début cet entier vaut bien entendu N),
 * - un entier qui vaudra vrai si et seulement si le barrière peut être ouverte,
 */
typedef struct barriere_t {
	pthread_mutex_t mutex;
	int nb_processus_pas_encore_arrives_barriere;
	int barriere_peut_etre_ouverte;
} barriere_t ;

#define SIZE_MEMOIRE_BARRIERE sizeof(barriere_t)

/**
 * une fonction void barriere(barriere_t*) sera appelée exactement
 * une fois dans chaque processus, et vérifiera la propriété suivante:
 *
 * si un processus n'est pas le dernier à atteindre la barrière, alors
 * il doit attendre jusqu'à ce que le dernier processus atteigne la
 * barrière pour pouvoir continuer; c'est-à-dire que la fonction
 * barriere doit bloquer le processus appelant jusqu'à ce que le
 * dernier processus appelle barriere;
 * lorsque le dernier processus appelle barriere, tous les processus
 * sont débloqués simultanément.
 */
void barriere(barriere_t* barriere_struct) {
	int can_continue;

	// Chaque fils affichera un message à l'écran avec son pid à son arrivée à la
	// barrière et juste après l'avoir franchie
	printf("Le fils avec pid = %d est arrivee a la barriere \n", getpid());

	int mutex_lock_result = pthread_mutex_lock( &(barriere_struct->mutex) );
	if(mutex_lock_result != 0) {
		char* error_msg = strerror( mutex_lock_result ); // char * strerror (int errnum)
		fprintf(stderr, "Function pthread_mutex_lock() : %s \n", error_msg);
		exit (EXIT_FAILURE);
	}

	/* SC - DEBUT */
	(barriere_struct->nb_processus_pas_encore_arrives_barriere)--;

	if(barriere_struct->nb_processus_pas_encore_arrives_barriere == 0)
		barriere_struct->barriere_peut_etre_ouverte = 1;

	can_continue = barriere_struct->barriere_peut_etre_ouverte;
	/* SC - FIN */

	int mutex_unlock_result = pthread_mutex_unlock( &(barriere_struct->mutex) );
	if(mutex_unlock_result != 0) {
		char* error_msg = strerror( mutex_unlock_result ); // char * strerror (int errnum)
		fprintf(stderr, "Function pthread_mutex_unlock() : %s \n", error_msg);
		exit (EXIT_FAILURE);
	}

	// Si un processus n'est pas le dernier à atteindre la barrière
	// Attendre jusqu'à ce que le dernier processus atteigne la
	// barrière pour pouvoir continuer
	if (can_continue != 1) {
		do{
			int mutex_lock_result = pthread_mutex_lock( &(barriere_struct->mutex) );
			if(mutex_lock_result != 0) {
				char* error_msg = strerror( mutex_lock_result ); // char * strerror (int errnum)
				fprintf(stderr, "Function pthread_mutex_lock() : %s \n", error_msg);
				exit (EXIT_FAILURE);
			}

			/* SC - DEBUT */
			can_continue = barriere_struct->barriere_peut_etre_ouverte;
			/* SC - FIN */

			int mutex_unlock_result = pthread_mutex_unlock( &(barriere_struct->mutex) );
			if(mutex_unlock_result != 0) {
				char* error_msg = strerror( mutex_unlock_result ); // char * strerror (int errnum)
				fprintf(stderr, "Function pthread_mutex_unlock() : %s \n", error_msg);
				exit (EXIT_FAILURE);
			}

		}while(can_continue != 1);
	}

	// Chaque fils affichera un message à l'écran avec son pid à son arrivée à la
	// barrière et juste après l'avoir franchie
	printf("Le fils avec pid = %d a franchie la barriere \n", getpid());
}

int main(int argc, char* argv[]) {
	// Un processus père initialisera la structure barriere_t et son
	// contenu puis créera N processus fils grâce l'appel système fork;

	if (argc < 2) {
		fprintf(stderr,"Utilisation : %s N \n", argv[0]);
		exit(EXIT_FAILURE);
	}

	int N = atoi(argv[1]); // int atoi (const char *string)

	// void * mmap (void *address, size_t length,int protect, int flags, int filedes, off_t offset)
	barriere_t* ptr_barriere_t = mmap((void *) 0, SIZE_MEMOIRE_BARRIERE, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANON, -1 , 0);
	if( (void*) ptr_barriere_t == MAP_FAILED) {
		perror("Function mmap()"); // void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	ptr_barriere_t->nb_processus_pas_encore_arrives_barriere = N;
	ptr_barriere_t->barriere_peut_etre_ouverte = 0;

	 // int pthread_mutexattr_init(pthread_mutexattr_t *attr);
	pthread_mutexattr_t attr;
	int mutexattr_init_result = pthread_mutexattr_init(&attr);
	if(mutexattr_init_result != 0) {
		// pour récuperer le massage correspondant à l’erreur,
		// faut passer par strerror() ; La fonction perror() est
		// inutilisable avec les mutex

		char* error_msg = strerror( mutexattr_init_result ); // char * strerror (int errnum)
		fprintf(stderr, "Function pthread_mutexattr_init() : %s \n", error_msg);
		exit (EXIT_FAILURE);
	}

	// int pthread_mutexattr_setpshared(pthread_mutexattr_t *attr,int pshared);
	int mutexattr_setpshared_result = pthread_mutexattr_setpshared(&attr, PTHREAD_PROCESS_SHARED);
	if(mutexattr_setpshared_result != 0) {
		char* error_msg = strerror( mutexattr_setpshared_result ); // char * strerror (int errnum)
		fprintf(stderr, "Function pthread_mutexattr_setpshared() : %s \n", error_msg);
		exit (EXIT_FAILURE);
	}

	// int pthread_mutex_init(pthread_mutex_t *mutex, const pthread_mutexattr_t *attr);
	int mutex_init_result = pthread_mutex_init( &(ptr_barriere_t->mutex), &attr);
	if(mutex_init_result != 0) {
		char* error_msg = strerror( mutex_init_result ); // char * strerror (int errnum)
		fprintf(stderr, "Function pthread_mutex_init() : %s \n", error_msg);
		exit (EXIT_FAILURE);
	}

	int i;
	pid_t fork_result;
	for(i = 0 ; i < N ; i++) {
		fork_result = fork();
		if(fork_result == -1) {
			perror("Function fork()"); // void perror (const char *message)
			exit(EXIT_FAILURE);
		}

		if(fork_result == 0) { // FILS
			/**
			 * Chacun des processus fils attendra un temps aléatoire de quelques
			 * secondes avant de faire un appel à barriere.
			 */

			// void srand (unsigned int seed)
			srand( getpid() ); // pid_t getpid (void)

			int min = 2, max = 20;
			// int rand (void)
			int sec = (rand() % (max - min +1)) + min;

			// unsigned int sleep (unsigned int seconds)
			sleep( sec );
			barriere(ptr_barriere_t);
			return EXIT_SUCCESS;
		}
	}

	/* PERE */
	pid_t wait_result;
	while (( wait_result = wait(NULL)) > 0 ); // pid_t wait (int *status-ptr)

	return EXIT_SUCCESS;
}



/**
 * Via la commande top, vous surveillerez la consommation CPU de processus en attente.  Que constatez-vous ?
 * Reponse : Bcp de CPU....
 *   PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
  648 lenny     20   0    2648    100      0 R  98.7   0.0   0:10.32 barriereDeSynch
  650 lenny     20   0    2648    100      0 R  98.3   0.0   0:05.34 barriereDeSynch
  651 lenny     20   0    2648    100      0 R  98.3   0.0   0:10.33 barriereDeSynch
 */

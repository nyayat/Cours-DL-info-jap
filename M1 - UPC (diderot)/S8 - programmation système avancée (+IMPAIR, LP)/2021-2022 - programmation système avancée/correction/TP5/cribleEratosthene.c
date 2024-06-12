/*
 ============================================================================
 Name        : cribleEratosthene.c
 Author      : Leonard (Lenny) NAMOLARU
 ============================================================================
 */
#include <stdlib.h> // exit(), EXIT_SUCCESS, EXIT_FAILURE, atoi()
#include <stdio.h> // printf(), fprintf(), perror()
#include <sys/mman.h> // mmap()
#include <sys/wait.h> // wait()
#include <unistd.h> // fork()
#include <errno.h>

void *initialisation(int N);
void affiche(void *map, int N);
void raye_multiples(void *map, int N, int p);

/**
 *  void *initialisation(int N)
 *  Une fonction qui crée le tableau initial en mémoire partagée
 *  et renvoie l'adresse en mémoire en cas de succès, MAP_FAILED en cas d'échec.
 */
void* initialisation(int N) {

	/* Une projection mémoire anonyme et partagée à l'aide de mmap
	 *
	 *  Shared anonymous mapping:
	 *  Purpose : Sharing memory between processes (IPC)
	 */

	// void* mmap(void* adr, size_t len, int prot, int flags, int fd, off_t offset)
	// void * mmap (void *address, size_t length,int protect, int flags, int filedes, off_t offset)
	return( mmap(0, sizeof(int)* N, PROT_READ | PROT_WRITE, MAP_ANON | MAP_SHARED, -1, 0) );
}


/**
 * Une fonction qui affiche la liste des indices pour lesquels la case correspondante contient 1.
 */
void affiche(void *map, int N) {
	int* ptr_memoire_partagee = map;

	int i;
	for(i = 0 ; i < N ; i++) {
		if( (*(ptr_memoire_partagee + i)) == 1 )
			printf("%d \t", i + 2);
	}
	printf("\n");
}

/**
 * void raye_multiples(void *map, int N, int p)
 * Une fonction qui raye du tableau les multiples de p
 * (c'est-à-dire met à 0 les cases les ayant comme indices).
 *
 * Un multiple de n est le produit de n par un nombre entier.
 * Autrement dit, un multiple de n est un nombre qui, divisé par n,
 * donne un résultat entier (la division euclidienne a un reste égal à zéro).
 * (Source : Wikipedia)
 */
void raye_multiples(void *map, int N, int p) {
	int* ptr_memoire_partagee = map;

	int i;
	for(i = 2 ; i <= N ; i++) {
		if( (i % p == 0) && (i != p) )
			*( ptr_memoire_partagee + (i-2) ) = 0;
	}

	/*
	 * Milleur methode :
	 * for(i = 2 * p ; i < n ; i += p)
	 *
	 * Ainsi on parcours les cases 2p.. 3p.. 4p
	 */
}

/**
 * Un programme qui crée le tableau en mémoire partagée
 * et crée N-1 fils : chaque fils s'occupe d'un entier entre 2
 * et N; si l'entier est déjà rayé (i.e. sa case vaut 0), il ne
 * fait rien, sinon il raye les multiples de l'entier du tableau.
 */
int main(int argc, char* argv[]) {

	if(argc < 2) {
		// int fprintf (FILE *stream, const char *template, ...)
		fprintf(stderr, "Utilisation : %s N \n", argv[0]);
		exit(EXIT_FAILURE);
	}

	// int atoi (const char *string)
	int N = atoi(argv[1]);

	int* ptr_memoire_partagee = initialisation(N);
	if(ptr_memoire_partagee == MAP_FAILED) {
		perror("Function mmap()");
		exit(EXIT_FAILURE);
	}

	int i;
	for(i = 2 ; i <= N ; i++)
			(*( ptr_memoire_partagee + (i-2) )) = 1;

	pid_t fork_result;
    for(i = 2 ; i <= N ; i++){ // Creation de N-1 fils
    	fork_result = fork();

    	if(fork_result == -1) {
    		perror("Function fork()");
    		exit(EXIT_FAILURE);
    	}

    	if(fork_result == 0) // FILS
    		break;
    }

    if(fork_result == 0) { // FILS
    	if( !( (*(ptr_memoire_partagee + (i - 2))) == 0 ) )
    		raye_multiples(ptr_memoire_partagee, N, i);
    } else /* PERE */ {
    	// Attente de terminaison de tous les enfants
    	while( wait(NULL) > 0);

    	if(errno != ECHILD) {
    		exit(EXIT_FAILURE);
    	}

    	/**
    	 * Le programme principal affiche la liste des nombres premiers trouvés (une
    	 * fois que tous les fils ont fini leur travail).
    	 */
    	affiche(ptr_memoire_partagee, N);
    }

	return EXIT_SUCCESS;
}

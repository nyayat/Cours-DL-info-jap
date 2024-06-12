/*
 ============================================================================
 Name        : incrementation_simultanee_inc_v2_mutex.c
 Author      : Leonard (Lenny) NAMOLARU

  gcc incrementation_simultanee_inc_v2_mutex.c -lrt -pthread -o incrementation_simultanee_inc_v2_mutex
  ./incrementation_simultanee_inc_v2_mutex & ./incrementation_simultanee_inc_v2_mutex
 ============================================================================
 */

#include <stdio.h> // perror()
#include <stdlib.h> // exit(), EXIT_SUCCESS, EXIT_FAILURE
#include <unistd.h> // ftruncate()
#include <fcntl.h> // fcntl : file control ; Objets memoire POSIX : pour les constantres O_
#include <sys/mman.h> // mmap(), msync() ; Objets memoire POSIX : pour shm_open()

#include <errno.h> // Pour strerror()
#include <string.h> // strerror()
#include <pthread.h>

#include "incrementation_simultanee_data.h"


#define NOM_SEGMENT_MEMOIRE_PARTAGEE "/val"
#define CHMOD_SEGMENT 0666
#define SHM_SIZE sizeof(memory)
#define NB_INCREMENTATION 10000

/**
 * Écrire un programme inc qui doit incrémenter cent mille fois la valeur
 * de l'entier contenue dans le segment /val.
 *
 * Après la dernière itération, le programme lit une dernière fois la valeur écrite
 * et l'affiche sur sa sortie standard.
 *
 * Lancer deux instances de ce programme : ./inc & ./inc.
 * Si la plus grande valeur affichée n'est pas 200000, c'est que vous
 * avez oublié de protéger la zone critique de votre programme :
 * déterminez-la et protégez-la avec des mutex.
 */
int main(int argc, char* argv[]) {

	/*
	 * int shm_open(cont char *name, int oflag, mode_t mode);
	 * retourne un descripteur fichier si OK, -1 sinon
	 */
	int shm_descripteur = shm_open(NOM_SEGMENT_MEMOIRE_PARTAGEE, O_RDWR, CHMOD_SEGMENT);
	if(shm_descripteur == -1) {
		perror("Function shm_open()"); //  void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	/**
	 * Sur Linux une fois objet mémoire ouvert on peut utiliser les opérations read et write
	 * sur son descripteur comme pour les fichiers.
	 * Mais cela n’est pas un comportement préconisé par POSIX.
	 * Par exemple sur MacOS il est impossible d’effectuer read/write sur les objets mémoire.
	 */

	/**
	 * La méthode conforme à Single Unix Specification pour accéder à un
	 * objet mémoire passe par une projection en mémoire avec mmap()
	 */

	// void * mmap (void *address, size_t length,int protect, int flags, int filedes, off_t offset)
	memory* ptr_mmap = mmap((void *) 0, SHM_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, shm_descripteur, 0);
	if( (void*) ptr_mmap == MAP_FAILED) {
		perror("Function mmap()"); //  void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	/* DEBUT : SC */

	/**
	 * #include <pthread.h>
	 * int pthread_mutex_lock(pthread_mutex_t *mutex);
	 * int pthread_mutex_unlock(pthread_mutex_t *mutex);
	 * Both return 0 on success, or a positive error number on error
	 */

	int mutex_lock_result = pthread_mutex_lock( &(ptr_mmap->mutex) );
	if(mutex_lock_result != 0) {
		char* error_msg = strerror( mutex_lock_result ); // char * strerror (int errnum)
		fprintf(stderr, "Function pthread_mutex_lock() : %s \n", error_msg);
		exit (EXIT_FAILURE);
	}

	int i = 0;
	for(i = 0 ; i < NB_INCREMENTATION ; i++)
		(ptr_mmap->number)++;

	printf("La valeur apres la derniere iteration : %d \n", ptr_mmap->number);

	/* FIN: SC */

	int mutex_unlock_result = pthread_mutex_unlock( &(ptr_mmap->mutex) );
	if(mutex_unlock_result != 0) {
		char* error_msg = strerror( mutex_unlock_result ); // char * strerror (int errnum)
		fprintf(stderr, "Function pthread_mutex_unlock() : %s \n", error_msg);
		exit (EXIT_FAILURE);
	}

	return EXIT_SUCCESS;
}

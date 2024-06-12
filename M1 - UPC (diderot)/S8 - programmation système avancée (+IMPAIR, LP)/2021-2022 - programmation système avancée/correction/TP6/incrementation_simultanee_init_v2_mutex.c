/*
 ============================================================================
 Name        : incrementation_simultanee_init_v2_mutex.c
 Author      : Leonard (Lenny) NAMOLARU

  gcc incrementation_simultanee_init_v2_mutex.c -lrt -pthread -o incrementation_simultanee_init_v2_mutex
  ./incrementation_simultanee_init_v2_mutex
 ============================================================================
 */

#include <stdio.h> // perror()
#include <stdlib.h> // exit(), EXIT_SUCCESS, EXIT_FAILURE
#include <unistd.h> // ftruncate()
#include <fcntl.h> // fcntl : file control ; Objets memoire POSIX : pour les constantres O_
#include <sys/mman.h> // mmap(), msync() ; Objets memoire POSIX : pour shm_open()
#include <sys/stat.h> // fstat()
#include <pthread.h> // pthread_mutexattr_init(), pthread_mutexattr_setpshared(), pthread_mutex_init()
#include <errno.h> // Pour strerror()
#include <string.h> // strerror()
#include "incrementation_simultanee_data.h"

#define NOM_SEGMENT_MEMOIRE_PARTAGEE "/val"
#define CHMOD_SEGMENT 0666

/**
 * Écrire un programme qui ouvre un segment de mémoire partagée /val et
 * l'initialise pour contenir l'entier 0.
 *
 */
int main(int argc, char* argv[]) {

	/*
	 * int shm_open(cont char *name, int oflag, mode_t mode);
	 * retourne un descripteur fichier si OK, -1 sinon
	 */
	int shm_descripteur = shm_open(NOM_SEGMENT_MEMOIRE_PARTAGEE, O_CREAT | O_RDWR, CHMOD_SEGMENT);
	if(shm_descripteur == -1) {
		perror("Function shm_open()"); //  void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	int new_length = sizeof(memory);

	/*
	 * int ftruncate (int fd, off_t length)
	 *
	 * The ftruncate function changes the size of a file to length.
	 * If length is shorter than the previous length, data at the end will be lost.
	 * The file must be writable by the user to perform this operation.
	 * If length is longer, holes will be added to the end.
	 * The return value is 0 for success, or -1 for an error.
	 *
	 * Source : Doc via Eclipse
	 */
	 int ftruncate_result = ftruncate(shm_descripteur, new_length);
	 if(ftruncate_result == -1) {
		perror("Function ftruncate()"); //  void perror (const char *message)
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
	memory* ptr_mmap = mmap((void *) 0, new_length, PROT_READ | PROT_WRITE, MAP_SHARED, shm_descripteur, 0);
	if( (void*) ptr_mmap == MAP_FAILED) {
		perror("Function mmap()"); //  void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	// Le programme initialise le segment de mémoire partagée pour contenir l'entier 0
	ptr_mmap->number = 0;

	/*
	 * #include <pthread.h>
	 * int pthread_mutex_init(pthread_mutex_t *mutex, const pthread_mutexattr_t *attr);
	 * Returns 0 on success, or a positive error number on error
	 *
	 * The mutex argument identifies the mutex to be initialized.
	 *
	 * The attr argument is a pointer to a pthread_mutexattr_t object
	 * that has previously been initialized to define the attributes for the mutex.
	 * If attr is specified as NULL, then the mutex is assigned various default attributes.
	 *
	 * When dealing with nondefault attributes, we use pthread_mutexattr_init to
	 * initialize a pthread_mutexattr_t structure.
	 *
	 * #include <pthread.h>
	 * int pthread_mutexattr_init(pthread_mutexattr_t *attr);
	 * Return: 0 if OK, error number on failure
	 *
	 * The pthread_mutexattr_init function will initialize the pthread_mutexattr_t
	 * structure with the default mutex attributes.
	 *
	 * There are three attributes of interest:
	 * the process-shared attribute, the robust attribute, and the type attribute.
	 *
	 * Within POSIX.1, the process-shared attribute is optional;
	 * you can test whether a platform supports it by checking whether the _POSIX_THREAD_PROCESS_SHARED symbol is defined.
	 * You can also check at runtime by passing the _SC_THREAD_PROCESS_SHARED parameter to the sysconf function.
	 * Although this option is not required to be provided by POSIXc onforming operating systems,
	 * the Single UNIX Specification requires that XSIconforming operating systems do support it.
	 *
	 * Within a process, multiple threads can access the same synchronization object.
	 * This is the default behavior. In this case, the process-shared mutex attribute
	 * is set to PTHREAD_PROCESS_PRIVATE.
	 *
	 * If the process-shared mutex attribute is set to PTHREAD_PROCESS_SHARED,
	 * a mutex allocated from a memory extent shared between multiple processes may
	 * be used for synchronization by those processes.
	 *
	 * We can change the process-shared attribute with the pthread_mutexattr_setpshared
	 * function.
	 *
	 * #include <pthread.h>
	 * int pthread_mutexattr_setpshared(pthread_mutexattr_t *attr,int pshared);
	 * pshared: PTHREAD_PROCESS_PRIVATE, PTHREAD_PROCESS_SHARED
	 * Return: 0 if OK, error number on failure
	 *
	 * Sources :
	 * (1) The Linux Programming Interface, Michael Kerrisk, No Starch Press
	 * (2) Advanced Programming in the UNIX Environment, Third Edition, W. Richard Stevens, Stephen A. Rigo, Addison-Wesley
	 */

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
	int mutex_init_result = pthread_mutex_init( &(ptr_mmap->mutex), &attr);
	if(mutex_init_result != 0) {
		char* error_msg = strerror( mutex_init_result ); // char * strerror (int errnum)
		fprintf(stderr, "Function pthread_mutex_init() : %s \n", error_msg);
		exit (EXIT_FAILURE);
	}

	/**
	 * int msync (void *address, size_t length, int flags)
	 * msync returns 0 for success and -1 for error
	 */
	int msync_result = msync(ptr_mmap, new_length, MS_SYNC);
	if(msync_result == -1) {
		perror("Function msync()"); //  void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	return EXIT_SUCCESS;
}

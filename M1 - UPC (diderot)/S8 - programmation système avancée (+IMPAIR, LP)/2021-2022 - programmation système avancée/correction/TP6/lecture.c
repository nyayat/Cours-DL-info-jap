/*
============================================================================
 Name        : lecture.c
 Author      : Leonard (Lenny) NAMOLARU

 gcc lecture.c -lrt -pthread -o lecture
 ============================================================================
 */

#include <fcntl.h> // Objets m�moire et semaphore POSIX : pour les constantes O_
#include <sys/stat.h> // fstat(), Objets m�moire et semaphore POSIX : pour les constantes droits d�acces
#include <sys/mman.h> // mmap(), Objets m�moire POSIX : pour shm_open
#include <semaphore.h> // sem_wait()

#include <string.h>
#include <stdio.h> // snprintf(), perror()
#include <stdlib.h> // atexit(), exit(), EXIT_FAILURE, EXIT_SUCCESS

#define SHM_NAME "/liste"

/**
 * Un programme qui permet d'afficher la liste des personnes
 * enregistr�es pr�c�d�es de leur indice *
 */
int main(int argc, char* argv[]) {


	/*
	 * int shm_open(cont char *name, int oflag, mode_t mode);
	 * retourne un descripteur fichier si OK, -1 sinon
	 */
	int shm_result = shm_open(SHM_NAME, O_RDWR, 0666);
	if(shm_result == -1) {
		perror("Function shm_open()"); // void perror (const char *message)
		exit(EXIT_FAILURE);
	}


	/*
	 * int fstat (int filedes, struct stat *buf)
	 *
	 * The fstat function is like stat,
	 * except that it takes an open file descriptor
	 * as an argument instead of a file name
	 *
	 * Source : Doc via Eclipse
	 */
	struct stat buf;
	int fstat_result = fstat (shm_result, &buf);

	// void * mmap (void *address, size_t length,int protect, int flags, int filedes, off_t offset)
	int* mmap_result = mmap(0, buf.st_size, PROT_READ, MAP_SHARED, shm_result, 0);
	if(mmap_result == MAP_FAILED) {
		perror("Function mmap()");
		exit(EXIT_FAILURE);
	}

	int i;
	char* liste = (char *) (mmap_result + 2 );
	for(i = 0 ; i < *(mmap_result) ; i++)
		printf("%c", *(liste + i) );

	return EXIT_SUCCESS;
}

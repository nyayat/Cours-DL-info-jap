/*
============================================================================
 Name        : ecriture.c
 Author      : Leonard (Lenny) NAMOLARU

 gcc ecriture.c -lrt -pthread -o ecriture
 ============================================================================
 */

#include <fcntl.h> // Objets m�moire et semaphore POSIX : pour les constantes O_
#include <sys/stat.h> // fstat(), Objets m�moire et semaphore POSIX : pour les constantes droits d�acces
#include <sys/mman.h> // mmap(), Objets m�moire POSIX : pour shm_open()
#include <semaphore.h> // sem_wait()

#include <unistd.h> // ftruncate()
#include <string.h> // strlen(), memcpy()
#include <stdio.h> // snprintf(), perror()
#include <stdlib.h> // atexit(), exit(), EXIT_FAILURE, EXIT_SUCCESS

#define SHM_NAME "/liste"
#define SEM_NAME "/semaphore"

sem_t* sem_result;
void exit_sem() {
	sem_post(sem_result);
}

/**
 * Un programme qui permet d'ajouter un nom :
 * Les noms = cha�nes de caract�res de la forme :
 *
 * indice (en d�cimal sous forme de cha�ne)
 * espace
 * nom
 * retour � la ligne
 */
int main(int argc, char* argv[]) {

	if(argc < 2) {
		fprintf(stderr,"Utilisation : %s mon_nom \n", argv[0]);
		exit(EXIT_FAILURE);
	}

	/*
	 * int shm_open(cont char *name, int oflag, mode_t mode);
	 * retourne un descripteur fichier si OK, -1 sinon
	 */
	int shm_result = shm_open(SHM_NAME, O_RDWR, S_IRUSR | S_IWUSR );
	if(shm_result == -1) {
		perror("Function shm_open()"); // void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	/* sem_t *sem_open(const char *name, int oflag [, * mode_t mode, unsigned int value ] );
	 * Returns pointer to semaphore on success, or SEM_FAILED on error
	 *
	 * Source : The Linux Programming Interface, Michael Kerrisk, No Starch Press
	 */
	sem_result = sem_open(SEM_NAME, O_RDWR, S_IRUSR | S_IWUSR, 1);
	if(sem_result == SEM_FAILED) {
		perror("Function sem_open()"); // void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	/*
	 * int sem_wait(sem_t *sem);
	 *
	 * The sem_wait() function decrements (decreases by 1) the value of the semaphorereferred to by sem.
	 * Returns 0 on success, or �1 on error
	 *
	 * Source : The Linux Programming Interface, Michael Kerrisk, No Starch Press
	 */
	 int wait_result = sem_wait(sem_result);
		if(wait_result == -1) {
			perror("Function sem_wait()"); // void perror (const char *message)
			exit(EXIT_FAILURE);
		}

	int atexit_result = atexit(exit_sem); // int atexit (void (*function) (void))
	if(atexit_result != 0) {
		fprintf(stderr,"atexit() error \n");
		sem_post(sem_result);
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
	int* mmap_result = mmap(0, buf.st_size, PROT_READ | PROT_WRITE , MAP_SHARED, shm_result, 0);
	if(mmap_result == MAP_FAILED) {
		perror("Function mmap()");
		exit(EXIT_FAILURE);
	}

	int lseek = *mmap_result;
	int index = *(mmap_result + 1);
	*(mmap_result + 1) += 1;

	char buffer[255];
	sprintf(buffer, "%d %s \n", index, argv[1]);
	int buffer_length = strlen(buffer);
	*mmap_result += buffer_length;

	// void * memcpy (void *restrict to, const void *restrict from, size_t size)
	// The memcpy function copies size bytes from the object beginning at from into the object beginning at to (Source : Doc via Eclipse)
	memcpy( (char *) mmap_result + lseek, buffer , buffer_length);

	// int msync (void *address, size_t length, int flags)
	int msync_result = msync(mmap_result, buf.st_size, MS_SYNC);
	if(msync_result < 0) {
		perror("Function msync()");
		exit(EXIT_FAILURE);
	}

	return EXIT_SUCCESS;
}

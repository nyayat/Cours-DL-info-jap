/*
 ============================================================================
 Name        : loadadd.c
 Author      : Leonard (Lenny) NAMOLARU
 ============================================================================
 */
#include <fcntl.h> // file control : open()
#include <stdlib.h> // exit(), EXIT_FAILURE, EXIT_SUCCESS
#include <stdio.h> // perror()
#include <unistd.h> // ftruncate()
#include <string.h> // memcpy()
#include <sys/mman.h> //mmap(), msync()
/* stat() */
#include <sys/types.h>
#include <sys/stat.h>

#define FILE "libop.a"
#define FILE_SIZE 1024

int add(int, int);

/**
 * int add(int, int)
 * Une fonction qui renvoie la somme de ses deux arguments.
 */
int add(int num1, int num2) {
	return (num1 + num2);
}


/**
 * Le main() de load.c doit créer (s'il n'existe pas) un fichier
 * libadd.a de 1024 octets, le projeter en mémoire, et y copier (avec
 * memcpy()) les 1024 octets débutant à (l'adresse de) la fonction add.
 * Exécuter le programme et vérifier que le fichier libadd.a a bien été créé.
 */
int main(int argc, char* argv[]) {

	// int open (const char *filename, int flags[, mode_t mode])
	int file_descriptor = open(FILE, O_RDWR | O_CREAT, 0666);
	if(file_descriptor == -1) {
		perror("Function open()"); // void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	struct stat file_stat1;
	// int stat (const char *filename, struct stat *buf)
	int stat_result = stat(FILE, &file_stat1);
	if(stat_result == -1) {
		perror("Function open()"); // void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	if(file_stat1.st_size != FILE_SIZE) {
		// int ftruncate (int fd, off_t length)
		// The file must be writable by the user to perform this operation (Source : Doc via Eclipse)
		if (ftruncate(file_descriptor, FILE_SIZE) == -1){
			perror("Function ftruncate()");
			exit(EXIT_FAILURE);
		}
	}

	// void * mmap (void *address, size_t length,int protect, int flags, int filedes, off_t offset)
	void* ptr_memoire = mmap((void *) 0, FILE_SIZE, PROT_READ|PROT_WRITE, MAP_SHARED, file_descriptor, 0);
	if(ptr_memoire == MAP_FAILED) {
		perror("Function mmap()");
		exit(EXIT_FAILURE);
	}

	// void * memcpy (void *restrict to, const void *restrict from, size_t size)
	// The memcpy function copies size bytes from the object beginning at from into the object beginning at to (Source : Doc via Eclipse)
	memcpy(ptr_memoire, &add, FILE_SIZE);

	// int msync (void *address, size_t length, int flags)
	int msync_result = msync(ptr_memoire, FILE_SIZE, MS_SYNC);
	if(msync_result < 0) {
		perror("Function msync()");
		exit(EXIT_FAILURE);
	}

	return EXIT_SUCCESS;
}

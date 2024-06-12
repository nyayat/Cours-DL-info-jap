/*
 ============================================================================
 Name        : use.c
 Author      : Leonard (Lenny) NAMOLARU
 ============================================================================
 */
#include <fcntl.h> // file control : open()
#include <stdlib.h> // exit(), EXIT_FAILURE, EXIT_SUCCESS
#include <stdio.h> // perror()
#include <sys/mman.h> //mmap()
#include <sys/wait.h> // wait()
#include <unistd.h> // fork()

/* stat() */
#include <sys/types.h>
#include <sys/stat.h>

#define FILE "libop.a"
#define FILE_SIZE 1024

/**
 * Écrire un programme use.c qui crée une zone mémoire partagée,
 * synchronisée avec le fichier libadd.a, avec le flag PROT_EXEC
 * pour pouvoir exécuter le code de cette zone mémoire.
 * Affecter à un pointeur f de type int (*)(int, int) l'adresse de
 * cette zone mémoire.
 *
 * Afficher le résultat de l'appel f(32, 10) et admirer la magie...
 *
 * Vérifier que vous pouvez lancer plusieurs processus
 * exécutant use simultanément (avec ./use & ./use & ...)
 */
int main(int argc, char* argv[]) {

	// int open (const char *filename, int flags[, mode_t mode])
	int file_descriptor = open(FILE, O_RDWR | O_CREAT, 0666);
	if(file_descriptor == -1) {
		perror("Function open()"); // void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	struct stat file_stat;
	// int stat (const char *filename, struct stat *buf)
	int stat_result = stat(FILE, &file_stat);
	if(stat_result == -1) {
		perror("Function open()"); // void perror (const char *message)
		exit(EXIT_FAILURE);
	}

	if(file_stat.st_size != FILE_SIZE) {
		// int ftruncate (int fd, off_t length)
		// The file must be writable by the user to perform this operation (Source : Doc via Eclipse)
		if (ftruncate(file_descriptor, FILE_SIZE) == -1){
			perror("Function ftruncate()");
			exit(EXIT_FAILURE);
		}
	}

	// void * mmap (void *address, size_t length,int protect, int flags, int filedes, off_t offset)
	int (*f) (int,int) = mmap((void *) 0, file_stat.st_size, PROT_READ|PROT_WRITE|PROT_EXEC, MAP_SHARED, file_descriptor, 0);
	if(f == MAP_FAILED) {
		perror("Function mmap()");
		exit(EXIT_FAILURE);
	}

	pid_t fork_result = fork();
	if(fork_result == -1) {
		perror("Function fork()");
		exit(EXIT_FAILURE);
	}

	if( fork_result == 0 ){
		// nt execl (const char *filename, const char *arg0, ...)
		execl("./loadadd","./loadadd", (void *) 0);
		perror("./loadadd");
		exit(EXIT_FAILURE);
	} else {
		wait(NULL);
		printf("f(32, 10) = %d \n", f(32, 10));

		pid_t fork_result2 = fork();
		if(fork_result2 == -1) {
			perror("Function fork()");
			exit(EXIT_FAILURE);
		}

		if( fork_result2 == 0 ){
			// nt execl (const char *filename, const char *arg0, ...)
			execl("./loadmult","./loadmult", (void *) 0);
			perror("./loadmult");
			exit(EXIT_FAILURE);
		} else {
			wait(NULL);
			printf("f(32, 10) = %d \n", f(32, 10));
		}
	}

	return EXIT_SUCCESS;
}

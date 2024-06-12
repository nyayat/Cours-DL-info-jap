/*
 ============================================================================
 Name        : belle_au_bois_dormant.c
 Author      : Leonard (Lenny) NAMOLARU
============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> // pause()
#include <signal.h>
/**
 * Écrire un programme qui dort (en faisant une boucle de pause()
 * par exemple) en ignorant tous les signaux (du moins, ceux qu'il peut
 * ignorer). Vérifier que votre programme se comporte comme attendu en lui envoyant
 * divers signaux avec la commande kill, en terminant par SIGKILL.
 *
 * Modifier votre programme pour qu'il frissonne affiche "Humm..."
 * à  la réception du signal SIGUSR1`, puis termine.
 *
 * Modifier enfin votre programme pour qu'il dorme jusqu'à réception de
 * deux signaux SIGUSR1 : lorsqu'il reçoit un premier baiser
 * signal SIGUSR1, il frissonne affiche "Humm...", puis se rendort
 * et en attend un second pour afficher "Merci mon prince!" (puis terminer).
 */
void handler1 (int sig) {
	static int counter = 0;
	counter++;
	printf ("Humm... \n");

	if (counter == 2) {
		printf ("Merci mon prince \n");
		exit (EXIT_SUCCESS);
	}
	return;
}

int main(void) {

	// SIGUSR1 - à la disposition du programmeur [term : termine le processus]

	// int sigaction( int sig, const struct sigaction *act, struct sigaction *oldact)

	 struct sigaction str;
	 str.sa_handler = SIG_IGN;
	 sigemptyset( &str.sa_mask );
	 str.sa_flags = 0;

	 int i;
	 for(i = 1 ; i <= NSIG ; i++) {
		 sigaction( i, &str, NULL);
	 }

		struct sigaction str2;
		 str2.sa_handler = handler1;
		 sigemptyset( &str2.sa_mask );
		 str2.sa_flags = 0;
	 if( sigaction( SIGUSR1, &str2, NULL) < 0) {
		 perror("sigaction 2");
		 exit(1);
	 }

	// int pause ()
	/**
	 * The pause function suspends program execution until a signal arrives whose action is either to execute a handler
	 * function, or to terminate the process.
	 *
	 * If the signal causes a handler function to be executed, then pause returns. This is considered an unsuccessful return
	 * (since ``successful'' behavior would be to suspend the program forever), so the return value is -1. Even if you specify that
	 * other primitives should resume when a system handler returns , this has no effect on pause; it always fails when a signal is handled.
	 *
	 * Donc :
	 */

	 while (1) {
			pause();
	 }

	return EXIT_SUCCESS;
}

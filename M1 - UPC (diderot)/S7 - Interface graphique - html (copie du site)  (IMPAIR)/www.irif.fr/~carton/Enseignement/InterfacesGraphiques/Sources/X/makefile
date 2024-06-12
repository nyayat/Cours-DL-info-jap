# Time-stamp: <makefile  25 fév 2003 15:03:24>

# L'option -L de gcc permet d'ajouter le répertoire où se trouve
# la librairie X11 dans le cas où se répertoire n'est pas dans la
# liste par défaut.

CC = gcc
CFLAGS = -Wall 

window: window.c
	$(CC) $(CFLAGS) -o window  window.c -L/usr/X11R6/lib/ -lX11

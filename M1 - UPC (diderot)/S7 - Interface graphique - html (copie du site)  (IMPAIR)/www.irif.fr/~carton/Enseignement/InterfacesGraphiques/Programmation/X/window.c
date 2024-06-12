/* Time-stamp: <window.c  25 fév 2003 15:20:55> */

// Création d'une fenêtre et affichage d'un texte 
// quand on clique dans la fenêtre

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>

int main(int argc, char* argv[])
{
  char* displayName = 0;	// Nom du serveur 
  Display* dpy;			// Display proprement dit
  int screen;			// Numéro de l'écran
  Window win;			// Fenêtre 
  GC gc;			// Contexte graphique
  XEvent event;			// Événement
  char name[] = "Hello world !"; // Chaîne à afficher

  // Recherche d'un argument de type -d ou -display
  if (argc == 3 && (!strcmp(argv[1], "-d") || 
		    !strcmp(argv[1], "-display")))
    displayName = argv[2];

  // Connexion au seveur X
  dpy = XOpenDisplay(displayName);
  // Si la connexion échoue, le programme se termine.
  if (dpy == 0) {
    printf("%s : connexion %s impossible\n", argv[0], 
	   XDisplayName(displayName));
    exit(1);
  }

  // Récupération de l'écran et du contexte graphique
  screen = DefaultScreen(dpy);	// Numéro de l'écran
  gc = DefaultGC(dpy, screen);	// Contexte graphique

  // Création de la fenêtre
  win = XCreateSimpleWindow(
	  dpy, 			   // Display
	  DefaultRootWindow(dpy),  // Fenêtre mère
	  0, 0, 300, 100, 	   // Géométrie 
	  10,			   // Largeur du bord
	  BlackPixel(dpy, screen), // Couleur du bord
	  WhitePixel(dpy, screen)  // Couleur du fond
	  );
  if (win == 0) {
    printf("%s : création de fenêtre impossible\n", argv[0]);
    exit(1);
  }

  // Mise en place du titre 
  // Ce titre est stocké sous la forme d'une propriété qui est
  // utilisée par le gestionnaire de fenêtres.  Utiliser xprop
  // pour vérifier que WM_NAME(STRING) = "Hello world !"
  XStoreName(dpy, win, name);

  // Sélection des événements
  // Trois types d'événements sont acceptés par la fenêtre
  XSelectInput(dpy, win, ButtonPressMask | ButtonReleaseMask | ExposureMask);

  // Affichage de la fenêtre
  XMapWindow(dpy, win);

  // Boucle d'attente d'événements
  for (;;) {
    // Récupération de l'événement suivant
    XNextEvent(dpy, &event);
    switch (event.type) {
    case ButtonPress : 
      printf("Événement ButtonPress\n");
      // Affichage de la chaîne
      XDrawString(dpy, win, gc, 100, 50, name, strlen(name));
      break;
    case ButtonRelease : 
      printf("Événement ButtonRelease\n");
      break;
    case Expose : 
      printf("Événement Exposure\n");
      break;
    }
  }
  return 0;
}

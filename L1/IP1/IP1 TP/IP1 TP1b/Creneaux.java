/*
  Un programme simple pour dessiner des creneaux
*/
public class Creneaux{

    // Donnée de configuration du dessin
    public static Turtle turtle;
    static int picture_width  = 1024;
    static int picture_height = 1024;

    public static void main (String[] args) {
	// Configuration de la tortue
	turtle = new Turtle (picture_width,picture_height);
	turtle.setspeed (5000);

	System.out.println("Nombre de boucles ?");
	int j = Integer.parseInt(System.console ().readLine ());

	System.out.println("Nombre de spirales ?");
	int k = Integer.parseInt(System.console ().readLine ());
	
	// Variables hauteur/largeur
	int dimlarg=10;
	int x=-400;
	int y=0;
	int modx=300;
	int mody=0;

	// Ici commence le code pour dessiner
	for (int a=0;a<k;a++){
	   int largeur=250;
	   turtle.up();
	   turtle.moveto(x,y);
	   turtle.down();
	   for(int i=0;i<j;i++){
	      turtle.setheading (0);
	      turtle.forward (largeur);
	      turtle.setheading (90);
	      turtle.forward (largeur);

	      largeur=largeur-dimlarg;
	      turtle.setheading (180);
              turtle.forward (largeur);
	      turtle.setheading (-90);
	      turtle.forward (largeur);
	    
	      largeur=largeur-dimlarg;
              }
	   x=x+modx;
	   y=y+mody;
	   
	}

	//Code d'attente pour la fermeture de la fenetre
	turtle.exitonclick ();
    }

}

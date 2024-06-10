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
	turtle.setspeed (100);

	// Ici commence le code pour dessiner
	turtle.up();
	turtle.moveto(-400,0);
	turtle.down();

	for(int i=0;i<2;i=i+1){
	    turtle.setheading (0);
	    turtle.forward (40);
	    turtle.setheading (90);
	    turtle.forward (40);
	    turtle.setheading (0);
	    turtle.forward (40);
	    turtle.setheading (-90);
	    turtle.forward (40);
	}

	//Code d'attente pour la fermeture de la fenetre
	turtle.exitonclick ();
    }

}

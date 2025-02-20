/*
  Un programme simple pour dessiner
*/
public class Dessine{

    // Donnée de configuration du dessin
    public static Turtle turtle;
    static int picture_width  = 1024;
    static int picture_height = 1024;

    public static void main (String[] args) {
	// Configuration de la tortue
	turtle = new Turtle (picture_width,picture_height);
	turtle.setspeed (300);

	// Ici commence le code pour dessiner
	turtle.setheading (0);
	turtle.forward (100);
	turtle.setheading (270);
	turtle.forward (100);
	turtle.setheading (180);
	turtle.forward (100);
	turtle.setheading (90);
	turtle.forward (100);
	turtle.setheading (45);
	turtle.forward (71);
	turtle.setheading (315);
	turtle.forward(71);
	
	turtle.up();
	turtle.moveto(150,0);
	turtle.down();
	turtle.setheading (0);
	turtle.forward (100);
	turtle.setheading (270);
	turtle.forward (100);
	turtle.setheading (180);
	turtle.forward (100);
	turtle.setheading (90);
	turtle.forward (100);
	turtle.setheading (45);
	turtle.forward (71);
	turtle.setheading (315);
	turtle.forward(71);

	//Code d'attente pour la fermeture de la fenetre
	turtle.exitonclick ();
    }

}

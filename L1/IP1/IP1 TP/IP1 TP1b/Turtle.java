/*
  Cette classe implémente une version simplifiée de la tortue du
  langage de programmation LOGO. Il ne s'agit pas de code que vous
  nous demandons de lire et encore moins de savoir écrire car il
  sort du programme d'IP1. Cependant, rien ne vous empêche d'y
  jeter un oeil par curiosité.

*/
public class Turtle {
    /*
      Une tortue sera représentée par une *instance* de la *classe*
      Turtle. Les mots "instance" et "classe" sont caractéristiques de
      la programmation orientée objet telle qu'on la pratique en JAVA.
      (Encore une fois, cette forme de programmation n'est pas celle
      que l'on apprend en IP1 mais dans le cours POO en L2.)

      Chaque tortue a une position et une orientation dont les valeurs
      courantes sont stockées dans les attributs d'instance de type
      int "x", "y" et "angle". Par ailleurs, lorsqu'elle se déplace,
      la tortue laisse une trace. Pour se faire, on peut imaginer
      qu'elle tient un crayon dans son bec. Parfois, on ne souhaite
      rien dessiner pendant le déplacement : il suffit de "lever le
      crayon". L'état crayon levé ou baissé est aussi stocké dans un
      attribut, mais il s'agit d'un attribut de type boolean
      cette fois-ci, l'attribut "is_down". Enfin, la tortue a aussi
      une vitesse: c'est l'attribut de type int nommé "speed" qui
      la représente.
     */

    // Position courante
    public int x;
    public int y;

    // Orientation courante
    public int angle;

    // Crayon levé ou baissé
    public boolean is_down;

    // Vitesse de déplacement de la tortue
    public int speed;
	
    // initialisation
    public Turtle(int xwidth, int ywidth){
		StdDraw.setCanvasSize(xwidth, ywidth);
		StdDraw.setXscale(-xwidth/2,xwidth/2);
		StdDraw.setYscale(-ywidth/2,ywidth/2);
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(StdDraw.BLUE);
		this.is_down = true;
		this.x = 0;
		this.y = 0;
		this.speed = 0;
		this.angle = 0;
    }

    // Déplace la tortue en (x,y) sans changer son orientation
    public void moveto (int x2, int y2) {
		if (this.is_down) {
			if(this.speed==0) StdDraw.line(this.x, this.y, x2, y2); //sans animation
			else animate_draw_line (x2, y2); //avec une animation
		}
		// Met à jour les coordonnées de la tortue.
		this.x = x2;
		this.y = y2;
    }

    public void animate_draw_line (int x2, int y2) {
		double xTotal = (double)(x2 - this.x);
		double yTotal = (double)(y2 - this.y);
		double total_move=Math.sqrt(xTotal*xTotal + yTotal*yTotal);
		if (total_move == 0) return;
		double step_length_approx = 4;
		int nb_steps = (int) Math.ceil (total_move/step_length_approx);
		double nextx = (double) this.x;
		double nexty = (double) this.y;
		for (int i = 1;i <= nb_steps; i++) {
			double lastx = nextx;
			double lasty = nexty;
			double f = ((double)i) / ((double) nb_steps);
			nextx = ((double) this.x) + (double)(xTotal * f);
			nexty = ((double) this.y) + (double)(yTotal * f);
			StdDraw.line (lastx,lasty,nextx,nexty);
			StdDraw.show();
			StdDraw.pause ((int)(10. / ((double)this.speed)));
		}
    }

    // Avance la tortue de "distance" pixels.
    public void forward (double distance){
		double alpha = (double)this.angle * Math.PI / 180;
		int nextx = this.x + (int)(Math.cos(alpha) * (double)distance);
		int nexty = this.y + (int)(Math.sin(alpha) * (double)distance);
		moveto (nextx, nexty);
    }

    // lève le crayon
    public void up (){
		this.is_down = false;
    }

    // baisse le crayon
    public void down (){
		this.is_down = true;
    }


    // change l'angle d'orientation de la tortue
    public  void setheading(int angle){
		this.angle = angle;
    }

    // tourne la tortue vers la gauche
    public void left(int angle){
		this.angle += angle;
    }

    // tourne la tortue vers la droite
    public void right(int angle){
		this.angle -= angle;
    }

    // change la vitesse de la tortue
    public void setspeed(int speed){
		this.speed = speed;
    }

    // attend un clic souris et termine
    public void exitonclick(){
		while (!StdDraw.mousePressed());
		System.exit(0);
    }
}

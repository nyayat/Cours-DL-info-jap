/* Pour pouvoir générer des nombres aléatoires */
import java.util.Random;

public class Alea {

    /***********************************/
    /* Ne modifiez pas le code suivant */
    /***********************************/

    /* La procédure suivante renvoie un entier tiré au hasard entre min et max. */
    public static int randInt (int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    static Random rand = new Random ();

    /*********************************/
    /* Fin du code à ne pas modifier */
    /*********************************/


    /* Écrivez vos fonctions ici */
    public static int de(){
	return(randInt(1,6));
    }

    public static void yams(){
	int x=randInt(1,6);
	int y=randInt(1,6);
	int z=randInt(1,6);
	String ans="";
	if (x>=y && x>=z){
	    if (y>=z){
		ans=(z+", "+y+", "+x);
	    }
	    else {
		ans=(y+", "+z+", "+x);
	    }
	}

	else if (y>=x && y>=z){
	    if (x>=z){
		ans=(z+", "+x+", "+y);
	    }
	    else {
		ans=(x+", "+z+", "+y);
	    }
	}

	else if (z>=y && z>=x){
	    if (y>=x){
		ans=(x+", "+y+", "+z);
	    }
	    else {
		ans=(y+", "+x+", "+z);
	    }
	}
	System.out.println(ans);
    }
    
    
    public static void main(String[] args) {

        /* Écrivez vos tests */
	// System.out.println(de());
	yams();

    }
}

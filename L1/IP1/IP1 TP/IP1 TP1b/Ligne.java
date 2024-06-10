/*

   Ce programme affiche une ligne avec 10 dieses

*/
public class Ligne{
    // Le point d'entrée du programme.
    public static void main (String[] args) {
	System.out.println("Tapez un nombre au clavier puis Entree");
	int x = Integer.parseInt(System.console ().readLine ());
	for(int i=0;i<x;i=i+1){
	    System.out.println("#");
	}
	System.out.println();
    }
}


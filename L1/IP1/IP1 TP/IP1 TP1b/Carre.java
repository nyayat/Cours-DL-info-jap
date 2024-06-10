/*

   Ce programme lit un entier tapé au clavier et l'affiche

*/
public class Carre{
    // Le point d'entrée du programme.
    public static void main (String[] args) {
	// On affiche la premiere ligne 
	for(int i=0;i<30;i=i+1){
	    System.out.print("D");
	}
	System.out.println();

	// On cree une ligne vide
	String ch = "A";

	for(int i=0;i<13;i=i+1){
	    ch = ch + "#";
	}

	ch = ch + "B";

	// On affiche les 8 lignes vides
	for(int i=0;i<13;i=i+1){
	    System.out.println(ch);
	}

	//On affiche la derniere ligne
	// On affiche la premiere ligne 
	for(int i=0;i<30;i=i+1){
	    System.out.print("C");
	}
	System.out.println();
    }
}

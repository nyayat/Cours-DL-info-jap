/*

   Ce programme affiche un message de politesse.

*/

/*

   Voici le nom du programme. On doit toujours définir
   une classe X dans un fichier nommé X.java

*/
public class HelloWorld {
    // Le point d'entrée du programme.
    public static void main (String[] args) {	
	// affichage
	System . out . println ( " What is your name ? " ) ;
	// demande nom et l'affecte à la variable name
	String name = System . console () . readLine () ;
	//affichage
	System . out . println ( " What is your first name ? " ) ;
	// demande nom et l'affecte à la variable fname
	String fname = System.console().readLine();
	// Une unique commande est exécutée.
	System.out.println ("Hello " + fname +  " " + name +  "!");
    }
}

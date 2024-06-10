// classe Test pour TP4bis - IP2
//
// Université de Paris, 2019/2020

public class Test {
	
	public static void lignehoriz() {
		System.out.println("------------------");
	}
	
	public static void main(String[] args) {

//		test Espion, question 2
		Espion james = new Espion("Bond");
		Espion austin = new Espion("Powers", false);
		Espion lana = new Espion("Kane");
		
		/*System.out.println("Test affichage espions");
		lignehoriz();
		System.out.println(james.description());
		System.out.println(austin.description());
		System.out.println(lana.description());
		lignehoriz();*/
		
//		test Maison
//		decommenter pour tester la question 3
		
		Maison alpha = new Maison(austin);
		Maison beta = new Maison(james);
		Maison gamma = new Maison(lana);
		
		System.out.println(alpha.description());
		System.out.println(beta.description());
		System.out.println(gamma.description());

// fin question 3
		
//		test reseau
//		decommenter pour tester la question 7
		
                System.out.println("Test reseau");
		Reseau monres = new Reseau("monReseau", gamma);
		monres.ajouterEspion(james);
		monres.ajouterEspion(austin);
		monres.afficher();
		lignehoriz();
		
//		test ajouter espion qui est déjà là
		/*System.out.println("Test ajouter un espion qui est déjà dans le réseau");
		lignehoriz();
		monres.ajouterEspion(james);
		lignehoriz();*/
		
//		crééz un réseau avec les maisons dans l'ordre c->b->a 
		
		Reseau monres2 = new Reseau("monReseau2", gamma);
//		*****************
		/*gamma.setLigne(beta);
        beta.setLigne(alpha);
		monres2.ajouterEspion(lana);
		//monres2.ajouterEspion(austin);
                
//		*****************
		monres2.afficher();
		lignehoriz();*/


// fin question 7		
		
//		test insertion  
//		decommenter après question 8
	
 		System.out.println("Test insertion");
		lignehoriz();
		Espion hercule = new Espion("Poirot");
		monres.insertion(hercule);
		monres.afficher();
		lignehoriz(); 

// fin question 8

//		test desertion
//		decommenter après question 9
		
  		/*System.out.println("Test desertion");
		lignehoriz();
		monres2.desertion(james);
		monres2.afficher();
		lignehoriz();*/

// fin question 9

//		test enlever 
//		decommenter après question 10
		
  		/*System.out.println("Test enlever");
		lignehoriz();
		monres2.enlever(austin);
		monres2.afficher();
		lignehoriz();
		
		monres2.ajouterEspion(austin);
		System.out.println("On rajoute austin:");
		monres2.afficher();
		
		lignehoriz();*/

// fin question 10

//		test premierLoyal (question 11)
//		A COMPLETER VOUS-MEME

		

//		test nettoyer
//		decommenter après Q12
		
		
  		/*System.out.println("Test nettoyer");
		lignehoriz();
		Reseau monresdes = monres2.nettoyer();
		
		monres2.afficher();
		lignehoriz();
		monresdes.afficher();
		lignehoriz();
		
//		test reseau vide
//		après Q12
		
		System.out.println("Test nettoyer réseaux vides");
		lignehoriz();
		Reseau vid = new Reseau("EMPTYNET");
		Reseau vid2 = vid.nettoyer();
		vid.afficher();
		lignehoriz();
		vid2.afficher();
		lignehoriz();*/

		
// fin question 12

	} // end main
} // end class

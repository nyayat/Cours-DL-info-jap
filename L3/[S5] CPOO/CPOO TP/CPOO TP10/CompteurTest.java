//3.
public class CompteurTest{
    private final Compteur compteur=new Compteur();
    
    synchronized void incrementerTest(){
        compteur.incrementer();
        System.out.println(compteur.getCompte()+" obtenu après incrémentation.");
    }
    
    synchronized void decrementerTest(){
        while(compteur.getCompte()<=0){
            try{
                wait(1);
            }
            catch(InterruptedException e){}
        }
        compteur.decrementer();
        System.out.println(compteur.getCompte()+" obtenu après décrémentation.");
    }
}
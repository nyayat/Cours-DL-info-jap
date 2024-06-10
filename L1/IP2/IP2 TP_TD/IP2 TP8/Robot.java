public class Robot {
	/*J'ai pensé qu'il serait plus judicieux de laisser tous les attributs en public, du fait qu'on a l'attribut courant
	  et ainsi on traite à chaque fois le courant (courant devient le pointeur et plus seulement un "premier". */
	  
    //1.1
    static int nbRob=0;
    final int id;
    String texte;
    int np;  //nombre de caractères restant à prononcer
    
    //1.2
    Robot(String t){
        this.texte=t;
        this.id=nbRob++;
        this.np=t.length();
    }
    
    //1.3
    void description(){
        System.out.println("Robot no."+this.id+" dit \""+this.texte+"\"");
    }
    
    //3.1
    boolean finiDeParler(){
        return(this.np==0);
    }
    
    //3.2
    void parle(int n){
        n=(this.np<n)? this.np : n;  //pour éviter dépassement à la boucle suivante
        for(int i=0; i<n; i++){
            System.out.print(this.texte.charAt(this.texte.length()-this.np+i));
        }
        System.out.println();
        this.np-=n;
    }
}
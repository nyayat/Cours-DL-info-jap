public class Pot {
    //1.
    Confiture Pot;
    int g; //masse du pot
    //3.
    final int k;  //numéro du pot
    static int num=0;
        
    //1.
    Pot(Confiture c, int g){
        Pot=c;
        this.g=g;
        num++;
        this.k=num;
    }
    
    //2.
    String description() {
    	return("Pot n°"+this.k+", "+this.g+"g de confiture de "+Pot.afficheFruit()); //3. : numéro
    }
        
    //4.
    static int lastNum() {
       	return num;
    }
}
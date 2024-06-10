//Exercice 5

//5.1
public class Spectacle {
    private Groupe[]groupes;
    
    //5.2
    Spectacle(int n){
        groupes=new Groupe[n];
    }
    
    //5.3
    int [] effectifs(){
    	int nombre=this.groupes.length;
        int[]res=new int[nombre];
        for(int i=0; i<nombre; i++){
            res[i]=this.groupes[i].getLong();
        }
        return res;
    }
}
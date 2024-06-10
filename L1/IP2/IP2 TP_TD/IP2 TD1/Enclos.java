public class Enclos {
    
    ///1.
    int pop;  //population actuelle dans l'enclos
    Animal[]e;
    
    public Enclos(int cap){
        e=new Animal[cap];
    }
    
    //2.
    public boolean ajout(Animal a){
        if(this.pop<this.e.length){
            for(int i=0; i<this.e.length; i++){
                if(this.e[i]==null){
                    this.e[i]=a;
                    this.pop++;
                    return true;
                }
            }
        }
        return false;
    }
    
    public void affiche(){
        for(int i=0; i<e.length; i++){
            if(e[i]!=null){
                System.out.println(Animal.description(e[i]));
            }
            else{
                System.out.println("rien");
            }
        }
    }
}
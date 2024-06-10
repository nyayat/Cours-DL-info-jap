//Exercice 1
public class Robot{
    private final char nom;//lettre entre ’a’ et ’z’
    private int energie;
    private final String texte; //ce qu’il doit dire
    
    public Robot(char nom, String paroles){
        this.nom = nom;  // on donne une énergie entre 10 et 20
        energie = 10 + (int)(Math.random() * 11);
        texte = paroles;
    }
    
    //1.1
    String description(){
        return ("Robot "+this.nom+" dit "+this.texte+" quand il parle et a "+this.energie+" points d'énergie.");
    }
    
    //1.2
    boolean nomCorrect(){
        char[]alpha={'a','z','e','r','t','y','u','i','o','p','q','s','d','f','g','h','j','k','l','m','w','x','c','v','b','n'};
        for(int i=0; i<alpha.length; i++){
            if(this.nom==alpha[i]){
                return true;
            }
        }
        return false;
    }
    
    //1.3
    char getNom(){
        return this.nom;
    }
    
    int getEnergie(){
        return this.energie;
    }
    
    String getTexte(){
        return this.texte;
    }
    
    //3.5
    void chante(){
        for(int i=0; i<(int)this.nom-96; i++){
            System.out.print(this.texte);
        }
        System.out.println();
        this.energie-=10;
        if(this.energie<0){
            this.energie=0;
        }
    }
}
public class Media {
    //1.1 & 1.2
    final private String titre;
    private static int numero=0;
    final private int registerNum;
    
    Media(String titre){
        this.titre=titre;
        this.registerNum=numero++;
    }
    
    String getTitre(){
        return this.titre;
    }
    
    int getNumero(){
        return this.registerNum;
    }
    
    //1.3
    public String toString(){
        return("Le média numéro "+this.registerNum+" a pour titre "+this.titre+". ");
    }
    
    //1.4 & 4.6
    boolean plusPetit(Media doc){
        //1.4
        //return this.registerNum<doc.registerNum;
        
        //4.6
        return (this.ordreMedia()<=doc.ordreMedia() && this.registerNum<doc.registerNum);
    }

    //4.3
    boolean plusPetit(Livre doc){
        if(!(this instanceof Livre)) return true;
        return this.registerNum<doc.getNumero();
    }
    
    //4.6
    int ordreMedia(){
        if(this.getClass()==Livre.class) return 0;
        if(this.getClass()==Dictionnaire.class) return 1;
        if(this.getClass()==DictionnaireBilingue.class) return 2;
        if(this.getClass()==BandeDessinee.class) return 3;
        if(this.getClass()==Manga.class) return 4;
        return 5;
    }
}
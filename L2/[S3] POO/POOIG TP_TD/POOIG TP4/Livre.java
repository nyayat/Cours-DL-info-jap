public class Livre extends Media {
    //2.1
    final protected String auteur;
    final private int pages;
    
    Livre(String titre, String auteur, int pages){
        super(titre);
        this.auteur=auteur;
        this.pages=pages;
    }
    
    //2.2
    public String toString(){
        String res=super.toString()+"Son auteur s'appelle "
                +this.auteur+" et l'ouvrage a "+this.pages+" pages.";
        return res;
    }
    
    //4.1
    /*boolean plusPetit(Media doc){
        if(!(doc instanceof Livre)) return false;
        return this.getNumero()<doc.getNumero();
    }*/
}
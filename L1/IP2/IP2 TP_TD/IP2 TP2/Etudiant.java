public class Etudiant {
    final String prenom;  //prenom
    final String nom;  //nom
    double note;  //note de l'étudiant sur 20
    
    static int nombreDEtudiants=0;  //nombre d'étudiants dans la promo
    static double sommeDesNotes=0;  //somme des notes des étudiants
    
    //3.
    public Etudiant(String prenom, String nom, double note){
        this.prenom=prenom;
        this.nom=nom;
        this.note=note;
        nombreDEtudiants++;
        sommeDesNotes+=note;
    }
    
    //6.
    public void afficher(){
        System.out.println(this.nom+" "+this.prenom+" : "+this.note);
    }
    
    //8.
    public boolean estAdmis(){
        if(this.note<10){
            return false;
        }
        return true;
    }
    
    //9.
    public static double moyenne(){
        double res=sommeDesNotes/nombreDEtudiants;
        return res;
    }
    
    //10.
    public boolean meilleurQueLaMoyenne(){
        return this.note>moyenne();
    }
    
    //11.
    public void modifierNote(double nouvelleNote){
        sommeDesNotes+=-this.note+nouvelleNote;
        this.note=nouvelleNote;
    }
}
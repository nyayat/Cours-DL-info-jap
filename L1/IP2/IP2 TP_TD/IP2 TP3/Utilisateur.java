public class Utilisateur {
    //1.
    private String pseudo;
    private String motDePasse;
    private final String adresseMail;
    
    //2.
    Utilisateur(String pseudo, String motDePasse, String adresseMail){
        this.pseudo=pseudo;
        this.motDePasse=motDePasse;
        this.adresseMail=adresseMail;
    }
    
    //3.
    public boolean testMotDePasse(String mdp){
        return (mdp.equals(this.motDePasse));
    }
    
    public void changerMotDePasse(String motDePasse, String nouveauMotDePasse){
        if(testMotDePasse(motDePasse)){
            this.motDePasse=nouveauMotDePasse;
        }
    }
    
    //4.
    public String getPseudonyme(){
        return this.pseudo;
    }
    
    public void setPseudonyme(String pseudo, String nouveauPseudo, String mdp, String mail){
	if(pseudo.equals(this.pseudo) && mdp.equals(this.motDePasse) && mail.equals(this.adresseMail)){
	    this.pseudo=nouveauPseudo;
	}
    }
    
    //Annexe pour les test
    public void getMDP(){
        System.out.println(this.motDePasse);
    }
}
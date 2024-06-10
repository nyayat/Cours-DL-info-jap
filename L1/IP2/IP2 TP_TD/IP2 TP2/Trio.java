public class Trio {
    //1.
    Etudiant[]membres;
    
    //2.
    public Trio(Etudiant e1, Etudiant e2, Etudiant e3){
        membres=new Etudiant[3];
        membres[0]=e1;
        membres[1]=e2;
        membres[2]=e3;
    }
    
    //4.
    public Etudiant premier(){
        Etudiant max=membres[0];
        for(int i=1; i<membres.length; i++){
            if(max.note<membres[i].note){
                max=membres[i];
            }
        }
        return max;
    }
    
    //5.
    public int classement(String prenom, String nom){
        boolean correcte=false;  //flag pour vérifier si bien dans le trio
        int i=0;
        while(i<membres.length && !correcte){
            if(membres[i].prenom.equals(prenom) && membres[i].nom.equals(nom)){
                correcte=!correcte;
            }
            else{
                i++;
            }
        }
        if(correcte){  //on le place en premier si dans le trio
            Etudiant tmp=membres[0];
            membres[0]=membres[i];
            membres[i]=tmp;
            i=membres.length;  //recycle variable pour classement et positionne l'étudiant en dernier
            for(int j=1; j<membres.length; j++){
                if(membres[0].note>=membres[j].note){
                    i--;  //quand il y a égalité ou note supérieur, avance d'un rang
                }
            }
            return i;
        }
        return 0;
    }
    
    //Quand il y a égalité entre deux étudiants, ces deux étudiants ont le même rang
    
    //6.
    public double moyenne(){
        double res=0;
        for(Etudiant e : membres){
            res+=e.note;
        }
        res/=membres.length;
        return res;
    }
    
    public boolean meilleurQueLaMoyenne(){
        return moyenne()>Etudiant.moyenne();
    }
}
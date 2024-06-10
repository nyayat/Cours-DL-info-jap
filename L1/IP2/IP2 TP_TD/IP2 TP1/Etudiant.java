package tp1;

public class Etudiant {
    String nom, prenom;  //les nom et prenom de l’etudiant
    int num;  //le numero d’etudiant
    int note;  //la note de l’etudiant (sur 20)
    
    //1.
    public Etudiant(String name, String firstName, int number, int mark){
        nom=name;
        prenom=firstName;
        num=number;
        note=mark;
    }
    
    //2.
    public static void afficher(Etudiant etu){
        System.out.println("Nom " + etu.nom
                + " Prénom " + etu.prenom
                + " (Numéro d'étudiant " + etu.num
                + " ) : Note " + etu.note);
    }
    
    //4.
    public static boolean estAdmis(Etudiant etu){
        if(etu.note<10){
            return false;
        }        
        return true;
    }
    
    //5.
    public static String mention(Etudiant etu){
        String res="Note invalide";
        if(etu.note<=20 && etu.note>=0){
            if(etu.note>=16){
                res="Très bien";
            }
            else if(etu.note>=14){
                res="Bien";
            }
            else if(etu.note>=12){
                res="Assez bien";
            }
            else if(etu.note>=10){
                res="Passable";
            }
            else if(etu.note>=0){
                res="Ajourné";
            }
        }
        return res;
    }
}

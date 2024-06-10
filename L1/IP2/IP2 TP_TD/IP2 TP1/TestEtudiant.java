package tp1;

public class TestEtudiant {
    
    //3.
    public static void main(String[]args){
        Etudiant etu1=new Etudiant("Dupont", "Jean", 21796432, 111);
        Etudiant etu2=new Etudiant("Dupont", "Jean", 21796432, 16);
        Etudiant etu3=new Etudiant("Dupont", "Jean", 21796432, 14);
        Etudiant etu4=new Etudiant("Dupont", "Jean", 21796432, 12);
        Etudiant etu5=new Etudiant("Dupont", "Jean", 21796432, 10);
        Etudiant etu6=new Etudiant("Dupont", "Jean", 21796432, 1);
        Etudiant.afficher(etu2);
        //on attend : Nom Dupont Prénom Jean (Numéro d'étudiant 21796432 ) : Note 16
        
        //4.
        System.out.println(Etudiant.estAdmis(etu6));  //on attend : false
        System.out.println(Etudiant.estAdmis(etu5));  //on attend : true
        
        //5.
        System.out.println(Etudiant.mention(etu1));  //on attend : Note invalide
        System.out.println(Etudiant.mention(etu2));  //on attend : Très bien
        System.out.println(Etudiant.mention(etu3));  //on attend : Bien
        System.out.println(Etudiant.mention(etu4));  //on attend : Assez bien
        System.out.println(Etudiant.mention(etu5));  //on attend : Passable
        System.out.println(Etudiant.mention(etu6));  //on attend : Ajourné
    
    }

}


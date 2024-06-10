public class Test {
    public static void main(String[]args){
        //2.    
            /*Un élément static est attaché à une classe et non pas à une instance, 
                elle peut donc s'exécuter sans instance.
            */

            //final caractérise un champ de valeur inchangeable après initialisation.    

            /*Les attributs sont déclarés public par défaut,
                donc sont accessible depuis n'importe quelle classe.
            */

        //4.
        Etudiant e1=new Etudiant("Luke", "Skywalker", 8.25);
        Etudiant e2=new Etudiant("Leia", "Organa", 11.75);
        
        //5.
        Etudiant e3=new Etudiant("Roucool", "Tori", 19.5);
        
        System.out.println("nb d'étudiants : "+Etudiant.nombreDEtudiants);
        //affcihe nb d'étudiants : 2 (3 avec ?5)
        System.out.println("somme des notes  : "+Etudiant.sommeDesNotes);
        //affiche somme des notes : 20 (39.5 avec ?5)
        
        
        //7.
        e1.afficher();  //affiche Skywalker Luke : 8.25
        e2.afficher();  //affiche Organa Leia : 11.75
        
        //8.
        System.out.println(e1.estAdmis());  //affiche false
        System.out.println(e2.estAdmis());  //affiche true;
        
        //9.
        System.out.println(Etudiant.moyenne());  //affiche 13.1666666
        
        //10.
        System.out.println(e1.meilleurQueLaMoyenne());  //affiche false
        System.out.println(e3.meilleurQueLaMoyenne());  //affiche true;
        
        //11.
        e2.modifierNote(19.5);
        e2.afficher();  //affiche Organa Leia : 19.5
        System.out.println("moyenne : "+Etudiant.moyenne());
        //affiche moyenne : 15.75
        
        //----------------------------------------------------------------//
        
        //3.
        Trio trinite=new Trio(e1, e2, e3);
        
        //4.
        trinite.premier().afficher();
        /*affiche Organa Leia : 19.5 
            et non pas la note de Roucool car Leia se situe avant Roucool dans le tableau
        */
        
        //5.
        System.out.println(trinite.classement("Leia", "Organa"));  //affiche 1
        System.out.println(trinite.classement("Roucool", "Tori"));  //affiche 1
        System.out.println(trinite.classement("Luke", "Skywalker"));  //affiche 3
        System.out.println(trinite.classement("Lelouch", "V.Britannia"));  //affiche 0
        
        //6.
        System.out.println(trinite.moyenne());  //affiche 15.75
        System.out.println(trinite.meilleurQueLaMoyenne());  //affiche false (car égalité)
    }
}
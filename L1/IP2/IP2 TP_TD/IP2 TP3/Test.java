public class Test {
    public static void main(String[]args){
        //Exercice 1
        //5.
        Utilisateur u1=new Utilisateur("Akabane","Karma","akabane[AT]karma.xyz");
        System.out.println(u1.testMotDePasse("Karma"));  //affiche : true
        System.out.println(u1.testMotDePasse("karma"));  //affiche : false
        
        u1.changerMotDePasse("Karma", "karma");
        u1.getMDP();  //affiche : karma
        u1.changerMotDePasse("Karma", "Karma");
        u1.getMDP();  //affiche : karma
        
        u1.setPseudonyme("Akabane", "Koro-sensei", "Karma", "akabane[AT]karma.xyz");
        System.out.println(u1.getPseudonyme());  //affiche : Akanabe
        u1.setPseudonyme("Akabane", "Koro-sensei", "karma", "akabane[AT]karma.xyz");
        System.out.println(u1.getPseudonyme());  //affiche : Koro-sensei
        
        //Exercice 2
        Message m1=new Message(u1,"Hello world");
        
        //Exercice 3
        //6.
        Salon s1=new Salon();
        Utilisateur u2=new Utilisateur("Shiota","Nagisa","shiota[AT]nagisa.xyz");
        Utilisateur u3=new Utilisateur("Koro","Sensei","koro[AT]sensei.xyz");
        
        s1.ajouterUtilisateur(u1);
        s1.ajouterUtilisateur(u2);
        System.out.println(s1.estPresent(u1));  //affiche : true
        System.out.println(s1.estPresent(u3));  //affiche : false
        
        Message m2=new Message(u2, "Sayonara, Koro-sensei");
        Message m3=new Message(u3, "Watashi wo koroshinasai");
        s1.ajouterMessage(m1);
        s1.ajouterMessage(m2);
        s1.ajouterMessage(m3);  //ne l'ajoute pas car u3 pas n'est pas un participant du salon
        
        s1.afficher();
        /* affiche : 
            Koro-sensei : Hello world
            Shiota : Sayonara, Koro-sensei
        */
        
        //7.
        s1.exclusUtilisateur(u1);
        System.out.println(s1.estPresent(u1));  //affiche : false
        s1.afficher();  //affiche : Shiota : Sayonara, Koro-sensei
        
        //Exercice 4
        Chat c=new Chat();
        Salon s2=new Salon();
        Utilisateur u4=new Utilisateur("Miyamizu", "Mitsuha", "miyamizu[AT]mitsuha.xyz");
        Utilisateur u5=new Utilisateur("Tachibana", "Taki", "tachibana[AT]taki.xyz");
        Utilisateur u6=new Utilisateur("Miyamizu","Yotsuha","miyamizu[AT]yotsuha.xyz");
        s2.ajouterUtilisateur(u4);
        s2.ajouterUtilisateur(u5);
        s2.ajouterUtilisateur(u6);
        s2.ajouterUtilisateur(u1);
        c.ajouterSalon(s1);
        c.ajouterSalon(s2);
        
        System.out.println(c.estPresent(u3));  //affiche : false
        System.out.println(c.estPresent(u1));  //affiche : true;
        System.out.println(c.estPresent(u5));  //affiche : true
        
        s1.ajouterUtilisateur(u5);
        Message m4=new Message(u5, "Asa,");
        s2.ajouterMessage(m4);
        s1.ajouterMessage(m4);
        Message m5=new Message(u5, "me ga sameruto");
        s2.ajouterMessage(m5);
        Message m6=new Message(u5, "nazeka naiteiru");
        s2.ajouterMessage(m6);
        System.out.println(c.nombreMessages(u5));  //affiche : 4
        
        System.out.println(c.bavard().getPseudonyme());  //affiche : Tachibana
        s1.ajouterMessage(m2);
        s1.ajouterMessage(m2);
        s1.ajouterMessage(m2);
        s1.ajouterMessage(m2);
        System.out.println(c.nombreMessages(u2));  //affiche : 5
        System.out.println(c.bavard().getPseudonyme());  //affiche : Shiota
    }
}
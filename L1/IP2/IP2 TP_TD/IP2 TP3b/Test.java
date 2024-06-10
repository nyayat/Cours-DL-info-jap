public class Test {
    //1.
    public static void main(String[]args){
        //5.
        Tache courses=new Tache("faire les courses");
        Tache mains=new Tache("se laver les mains");
        Tache cuisine=new Tache("faire la cuisine");
        Tache manger=new Tache("manger");
        courses.declareNecessairePour(cuisine);
        mains.declareNecessairePour(cuisine);
        cuisine.declareNecessairePour(manger);
        
        courses.afficher();  
        //affiche : Tâche 1 : faire les courses. Est nécessaire avant la (ou les) tâche(s) 3. Dépend de 0 tâche(s).
        System.out.println(courses.estExecutable());  //affiche : true
        
        mains.afficher();  
        //affiche : Tâche 2 : se laver les mains. Est nécessaire avant la (ou les) tâche(s) 3. Dépend de 0 tâche(s).
        System.out.println(mains.estExecutable());  //affiche : true
        
        cuisine.afficher();  
        //affiche : Tâche 3 : faire la cuisine. Est nécessaire avant la (ou les) tâche(s) 4. Dépend de 2 tâche(s).
        System.out.println(cuisine.estExecutable());  //affiche : false
        
        manger.afficher();  
        //affiche : Tâche 4 : manger. Dépend de 1 tâche(s).
        System.out.println(manger.estExecutable());  //affiche : false
        
        System.out.println("-------------------------------------");
        
        //12.
        Agent a2=new Agent("Agent");
        System.out.println(a2.empty());  //affiche : true
        Tache t5=new Tache("faire les courses",a2);
        Tache t6=new Tache("cuisiner",a2);
        t5.enleveNecessairePourBis();
        System.out.println(a2.empty());  //affiche : false
        
        //13.
        for(int i=0; i<a2.tachesExecutables.length; i++){
            a2.tachesExecutables[i].afficher();
        }
        //affiche : Tâche 5 : faire les courses.  Dépend de 0 tâche(s).
        
        //14.
        //t5.faire();
    }
}
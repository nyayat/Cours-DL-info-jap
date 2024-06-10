import java.util.HashSet;
import java.util.Set;

public class Main{
	
    public static void testAutomate1(){
	Etat[] etats = new Etat[3];
	etats[0] = new Etat(0,false);
	etats[1] = new Etat(1,false);
	etats[2] = new Etat(2,true);
	etats[0].ajouteTransition('a', etats[1]);
	etats[0].ajouteTransition('a', etats[2]);
	etats[1].ajouteTransition('a', etats[1]);
	etats[1].ajouteTransition('c', etats[1]);
	etats[1].ajouteTransition('b', etats[2]);
	Automate a = new Automate(etats[0]);
	System.out.println(a);

	String s = "aaab";
	System.out.println("Mot " + s + " accepté ? " + a.accepte(s));

        Set<Etat> s1 = new HashSet<Etat>();
	s1.add(etats[0]);
	Set<Etat> s2 = new HashSet<Etat>();
	s2.add(etats[1]);
	s2.add(etats[2]);
	Set<Etat> s3 = new HashSet<Etat>();
	s3.add(etats[2]);
	s3.add(etats[1]);
	s3.add(etats[2]);
	
	System.out.println(" s1 == s2 ? " + s1.equals(s2));
	System.out.println(" s2 == s3 ? " + s2.equals(s3));
    }

    //1.
    public static void testAutomate2(){
	// écrire ici le premier automate de l'énoncé	
        Etat[]etats=new Etat[11];
        etats[0]=new Etat(0, false);
        etats[1]=new Etat(1, false);
        etats[2]=new Etat(2, false);
        etats[3]=new Etat(3, false);
        etats[4]=new Etat(4, false);
        etats[5]=new Etat(5, false);
        etats[6]=new Etat(6, false);
        etats[7]=new Etat(7, false);
        etats[8]=new Etat(8, false);
        etats[9]=new Etat(9, false);
        etats[10]=new Etat(10, true);
        etats[0].ajouteTransition('a', etats[0]);
        etats[0].ajouteTransition('b', etats[0]);
        etats[0].ajouteTransition('a', etats[1]);
        etats[1].ajouteTransition('a', etats[2]);
        etats[1].ajouteTransition('b', etats[2]);
        etats[2].ajouteTransition('a', etats[3]);
        etats[2].ajouteTransition('b', etats[3]);
        etats[3].ajouteTransition('a', etats[4]);
        etats[3].ajouteTransition('b', etats[4]);
        etats[4].ajouteTransition('a', etats[5]);
        etats[4].ajouteTransition('b', etats[5]);
        etats[5].ajouteTransition('a', etats[6]);
        etats[5].ajouteTransition('b', etats[6]);
        etats[6].ajouteTransition('a', etats[7]);
        etats[6].ajouteTransition('b', etats[7]);
        etats[7].ajouteTransition('a', etats[8]);
        etats[7].ajouteTransition('b', etats[8]);
        etats[8].ajouteTransition('a', etats[9]);
        etats[8].ajouteTransition('b', etats[9]);
        etats[9].ajouteTransition('a', etats[10]);
        etats[9].ajouteTransition('b', etats[10]);
	Automate a1 = new Automate(etats[0]);
	System.out.println(a1);
    }

    public static void testAutomate3(){
	// écrire ici le second automate de l'énoncé
        //On simplifie l'automate parce que voilà 31 états pareils :))))
        Etat[]etats=new Etat[5];
        etats[0]=new Etat(0, false);
        etats[1]=new Etat(1, false);
        etats[2]=new Etat(2, false);
        etats[3]=new Etat(3, false);
        etats[4]=new Etat(4, true);
        etats[0].ajouteTransition('a', etats[0]);
        etats[0].ajouteTransition('b', etats[0]);
        etats[0].ajouteTransition('a', etats[1]);
        etats[0].ajouteTransition('b', etats[1]);
        etats[1].ajouteTransition('a', etats[1]);
        etats[1].ajouteTransition('b', etats[1]);
        etats[1].ajouteTransition('a', etats[2]);
        etats[1].ajouteTransition('b', etats[2]);
        etats[2].ajouteTransition('a', etats[2]);
        etats[2].ajouteTransition('b', etats[2]);
        etats[2].ajouteTransition('a', etats[3]);
        etats[2].ajouteTransition('b', etats[3]);
        etats[3].ajouteTransition('a', etats[3]);
        etats[3].ajouteTransition('b', etats[3]);
        etats[3].ajouteTransition('a', etats[4]);
        etats[3].ajouteTransition('b', etats[4]);
        Automate a2=new Automate(etats[0]);
        System.out.println(a2);
    }

    public static void main(String[] args){
	//testAutomate1();
        
        //1.
	//testAutomate2();
	//testAutomate3();
        
    }
}

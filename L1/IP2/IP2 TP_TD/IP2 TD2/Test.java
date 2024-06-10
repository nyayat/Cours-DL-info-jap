public class Test {
    public static void main(String[] args) {
	/*Exercice 1.1*/
	//4.
	Confiture c0=new Confiture ("framboiz", 50, 100);
	//System.out.println(c0.description());
		
	//5.
	//System.out.println(c0.gcal(33));
	
	//7.
	/*Confiture c1=new Confiture("fraise", 50, 120);
	Confiture c2=new Confiture("fraise", 50, 120);
	System.out.println(c1.egal(c2));
	System.out.println(c1==c2);*/
	//System.out.println(c1.fruit); COMPILATION PAS AUTORISEE
	
	//9.
	/*System.out.println(c0.afficheFruit());
	System.out.println(c0.description());
	c0.modifCal(5000);
	System.out.println(c0.description());*/
	
	//11.
	/*System.out.println(c0.description());
	c0.modifProp(0);
	System.out.println(c0.description());
	c0.modifProp(100);
	System.out.println(c0.description());
	c0.modifProp(70);
	System.out.println(c0.description());*/
	
	/*Exercice 1.2*/
	//5.
	Pot p1=new Pot(c0,10);
	Pot p2=new Pot(c0,10);
	Pot p3=new Pot(c0,10);
	Pot p4=new Pot(c0,10);
	System.out.println(p1.description());
	System.out.println(p2.description());
	System.out.println(p3.description());
	System.out.println(p4.description());
        System.out.println(Pot.lastNum());
        
        Binome b1=new Binome("Lelouch","Roucool");
        b1.plusPetitEver.affiche();
        b1.change2("C.C.");
        b1.plusPetitEver.affiche();
    }
}
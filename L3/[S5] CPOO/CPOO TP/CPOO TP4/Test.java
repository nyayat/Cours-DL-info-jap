import java.awt.geom.Point2D;

public class Test{
    public static void main (String[] args){
        //1.
        /*Personne yan=new Personne("Jurski");
        Enseignant isabelle=new Enseignant("Fagnot", "CPOO5");
        Personne aldric=new Enseignant("Degorre", "CPOO5");
        isabelle.chante();
        //yan.enseigne();  //yan n'est pas un enseignant
        aldric.chante();
        ((Enseignant)aldric).enseigne();
        Personne[] jury={yan, isabelle, aldric, new Enseignant("Shen", "CPOO5"),
            (Personne)new Enseignant("Fantome", "CPOO5")};
        for(Personne p:jury) p.presenteToi();*/
        
        //2.
        /*A a=new C();
        Y x=new Y();
        a.f((X)x);//B et X
        a.f(x);//C et Y
        */
        
        //4.
        Parallelogram par=new Parallelogram(new Point2D.Double(10,15),
                new Point2D.Double(5,6), new Point2D.Double(8,20));
        Shape2D figure=new Shape2DFondColore(new Shape2DLargeurBord(par));
        
        //5.
        //System.out.println((int)true);//erreur de compilation
        System.out.println((int)'a');
        System.out.println((byte)'a');
        System.out.println((byte)257);
        System.out.println((char)98);
        System.out.println((double)98);
        System.out.println((char)98.12);
        System.out.println((long)98.12);
        //System.out.println((boolean)98);//erreur de compilation
        
    }
}
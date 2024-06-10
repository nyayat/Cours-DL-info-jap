public class Test {
    public static void main(String[]a){
        //1.-6.
        Rectangle r1=new Rectangle (0, 0, 5.7, 6.2);
        Ellipse e1=new Ellipse(5, 2, 3.5, 2.1);
        Triangle t1=new Triangle(1, 5, 5.7, 6.2);
        Carre sq1=new Carre(6, 6, 6);
        Circle c1=new Circle(9, 8, 7);
        Figure tmp;  //pour sauvegarder la déformation des figures
        
        /*r1.affiche();
        System.out.println(r1.surface());
        r1.deplacement(1, 1);
        r1.affiche();
        System.out.println(r1.surface());
        tmp=r1.deformation(5.7, 6.2);  //carré
        tmp.affiche();
        System.out.println(tmp.surface());
        tmp=r1.deformation(1, 0.5);  //rectangle
        tmp.affiche();
        System.out.println(tmp.surface());*/
        
        /*e1.affiche();
        System.out.println(e1.surface());
        e1.deplacement(1, 1);
        e1.affiche();
        System.out.println(e1.surface());
        tmp=e1.deformation(2.1, 3.5);  //circle
        tmp.affiche();
        System.out.println(tmp.surface());
        tmp=e1.deformation(1, 0.5);  //ellipse
        tmp.affiche();
        System.out.println(tmp.surface());*/
        
        
        /*t1.affiche();
        System.out.println(t1.surface());
        t1.deplacement(1, 1);
        t1.affiche();
        System.out.println(t1.surface());
        tmp=t1.deformation(1, 0.5);
        tmp.affiche();
        System.out.println(tmp.surface());*/
        
        /*sq1.affiche();
        System.out.println(sq1.surface());
        sq1.deplacement(1, 1);
        sq1.affiche();
        System.out.println(sq1.surface());
        tmp=sq1.deformation(2, 1);  //rectangle
        tmp.affiche();
        System.out.println(tmp.surface());
        tmp=sq1.deformation(0.5, 0.5);  //carré
        tmp.affiche();
        System.out.println(tmp.surface());*/
        
        /*c1.affiche();
        System.out.println(c1.surface());
        c1.deplacement(1, 1);
        c1.affiche();
        System.out.println(c1.surface());
        tmp=c1.deformation(1, 0.5);  //ellipse
        tmp.affiche();
        System.out.println(tmp.surface());
        tmp=c1.deformation(0.5, 0.5);  //circle
        tmp.affiche();
        System.out.println(tmp.surface());*/
        
        //System.out.println(r1.estDistantDe(e1));
        
        
        //7.-10.
        /*int[]t1={2, 4, 1, 9};
        EntierTriable et1=new EntierTriable(t1);
        System.out.println(et1);
        Triable.triBulles(et1);
        System.out.println(et1);*/
        
        /*String[]t2={"001", "111", "010", "110", "11", "10", "011"};
        TriBinaire tb1=new TriBinaire(t2);
        System.out.println(tb1);
        Triable.triBulles(tb1);
        System.out.println(tb1);*/
        
        /*String[]t3={"a", "abc", "a", "ab", "b", "abb"};
        Dictionnaire d1=new Dictionnaire(t3);
        System.out.println(d1);
        Triable.triBulles(d1);
        System.out.println(d1);*/
    }
}
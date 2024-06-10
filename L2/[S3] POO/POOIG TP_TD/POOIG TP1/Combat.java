//5.5
public class Combat {
    public static void main(String[]a){
        Informations inf=new Informations("Gauvain",12,12,1);
        Personnage p1=new Personnage(inf);

        Informations inf2=new Informations("Lancelot",10,10,2);
        Personnage p2=new Personnage(inf2);

        //p1.lutteIt(p2);
        p1.lutteRec(p2);
    }
}
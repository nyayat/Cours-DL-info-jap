import java.util.Random;

public class Main {
    public static void main(String[]args){
        testPl(20, 20, 5, 5);
    }
    
    public static void test(int largeur, int longueur, int x0, int y0, int o0, int nb){
        Grille g=new Grille(longueur, largeur);
        Fourmi f=new Fourmi(x0, y0, o0, g);
        int i=0;
        g.dessineAvecFourmi(f);
        System.out.println();
        while(i<nb && !f.unMouvement()){
            g.dessineAvecFourmi(f);
            System.out.println();
            i++;
        }
    }
    
    static Random rand=new Random();
    
    public static void testPl(int largeur, int longueur, int nbmvt, int nbF){
        Grille g=new Grille(longueur, largeur);
        Fourmi[]t=new Fourmi[nbF];
        for(int i=0; i<nbF; i++){
            t[i]=new Fourmi(rand.nextInt(longueur+1),
                    rand.nextInt(largeur+1),
                    rand.nextInt(4),
                    g);
        }
        int i=0;
        boolean horsG=false;
        while(i<nbmvt && !horsG){
            g.dessine();
            System.out.println();
            for(int j=0; j<nbF; j++){
                horsG=horsG || t[j].unMouvement();
            }
            i++;
        }
    }
}

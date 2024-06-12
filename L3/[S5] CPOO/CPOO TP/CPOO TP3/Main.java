import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        //1.2
        /*Scanner sc=new Scanner(System.in);
        System.out.println("Choose from :\n1. Random number\n"
            +"2. Arithmetic sequence\n3. Geometric sequence\n4. Fibonnacci");
        Generateur res;
        switch(sc.nextInt()){
            case 1:
                System.out.println("From 0 to ?");
                res=GenLib.aleaInt(sc.nextInt());
                System.out.print(res.suivant());
                break;
            case 2:
                System.out.println("Common difference ?");
                res=GenLib.suiteAri(sc.nextInt());
                printSequence(res);
                break;
            case 3:
                System.out.println("Common ratio ?");
                res=GenLib.suiteGeo(sc.nextInt());
                printSequence(res);
                break;
            case 4:
                res=GenLib.fibo();
                printSequence(res);
                break;
        }
        System.out.println();*/
        
        
        //2.5
        /*String[] mots=new String[10];
        for(int i=0; i<10; i++) mots[i]=Integer.toString(GenLib.aleaInt(10).suivant());
        SequenceMots s=new SequenceMots(mots);
        s.affiche();
        s.triBulle();
        s.affiche();*/
    }
    
    //1.2
    public static void printSequence(Generateur toPrint){
        for(int i=0; i<10; i++) System.out.print(toPrint.suivant()+" ");        
    }
}
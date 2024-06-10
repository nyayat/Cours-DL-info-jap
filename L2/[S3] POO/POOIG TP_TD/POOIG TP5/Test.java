import java.util.Scanner;

public class Test {
    public static void main(String[]a){
        //4.
        Formateur f1=new Formateur("D:/textes/texte");  //emplacement du fichier
        Formateur f2=new Formateur("D:\\textes\\texteBis");  //emplacement du fichier
        Formateur f3=new Formateur("D:\\textes\\vide");  //emplacement du fichier
        Formateur f4=new Formateur("D:\\textes\\videBis");  //emplacement du fichier
        
        /*f1.read();
        f1.print();
        System.out.println("----------");
        f2.read();
        f2.print();
        System.out.println("----------");
        f3.read();
        f3.print();
        System.out.println("----------");
        f4.read();
        f4.print();
        System.out.println("----------");*/
        
        //5.
        FormateurLimite fL1=new FormateurLimite("D:/textes/texte", 7);
        FormateurLimite fL2=new FormateurLimite("D:/textes/texteBis", 50);
        FormateurLimite fL3=new FormateurLimite("D:/textes/vide", 7);
        FormateurLimite fL4=new FormateurLimite("D:/textes/videBis", 7);
        
        /*fL1.read();
        fL1.print();
        System.out.println("----------");
        fL2.read();
        fL2.print();
        System.out.println("----------");
        fL3.read();
        fL3.print();
        System.out.println("----------");
        fL4.read();
        fL4.print();
        System.out.println("----------");*/
        
        //10.
        /*fL1.read();
        fL1.printJustifie();
        System.out.println("----------");
        fL2.read();
        fL2.printJustifie();
        System.out.println("----------");
        fL3.read();
        fL3.printJustifie();
        System.out.println("----------");
        fL4.read();
        fL4.printJustifie();
        System.out.println("----------");*/
        
        //11.
        /*fL1.read();
        fL1.printJustifieBis();
        System.out.println("----------");
        fL2.read();
        fL2.printJustifieBis();
        System.out.println("----------");
        fL3.read();
        fL3.printJustifieBis();
        System.out.println("----------");
        fL4.read();
        fL4.printJustifieBis();
        System.out.println("----------");*/
    }
}
import java.util.Scanner;

public class Test {
    public static void main(String[]a) {
        //5.
        /*Memoire m = new Memoire(3);
        m.inspecte();
        m = m.getS();
        m.setV(-42);
        m.inspecte();
        m = m.getS();
        m.setV(11);
        m.inspecte();*/

        //12.
        //brainf_ck(",>,[-<+>]<.");
        //brainf_ck(",>,[-<->]<");

        //14.
        brainf_ck(">>,>,<[>[<<+<+>>>-]<<[>>+<<-]>-]>[-]<<[-]<");
    }

    //6.
    static void brainf_ck(String programme) {
        Memoire mem=new Memoire(8);
        for(int i=0; i<programme.length(); i++) {
            switch(programme.charAt(i)) {
                //7.
                case '>':
                    mem=mem.getS();
                    break;
                case '<':
                    mem=mem.getP();
                    break;
                //8.
                case '+':
                    mem.incremente();
                    break;
                case '-':
                    mem.decremente();
                    break;
                //9.
                case '.':
                    System.out.println(mem.getV());
                    break;
                //10.
                case ',':
                    Scanner sc=new Scanner(System.in);
                    System.out.println("UN NOMBRE STP.");
                    int nb=sc.nextInt();
                    mem.setV(nb);
                    break;
                //11.
                case '[':
                    if(mem.getV()==0) {
                        //11.
                        //int tmp=fermantCorrespondant(programme, i);
                        //13.
                        int tmp=fermantCorrespondant_bis(programme, i);
                        if(tmp==-1){
                            System.out.println("Erreur de crochets.");
                        }
                        else{
                            i=tmp;
                        }
                    }
                    break;
                case ']':
                    //11.
                    //int tmp=ouvrantCorrespondant(programme, i)-1;
                    //13.
                    int tmp=ouvrantCorrespondant_bis(programme, i)-1;
                    if(tmp==-1){
                        System.out.println("Erreur de crochets.");
                    }
                    else{
                        i=tmp;
                    }
                    break;	
            }
            mem.inspecte();
        }
    }

    //11.
    static int fermantCorrespondant(String programme, int depart) {//on suppose que depart est correct
        int res=depart+1;
        while(programme.length()>res && programme.charAt(res)!=']') {
            res++;
        }
        if(res>programme.length()) {
            res=-1;  //erreur
        }
        return res;
    }

    static int ouvrantCorrespondant(String programme, int depart) {//même remarque
        int res=depart-1;
        while(0<=res && programme.charAt(res)!='[') {
            res--;
        }
        return res;
    }

    //13.
    static int fermantCorrespondant_bis(String programme, int depart) {
        int i=depart+1;
        int k=0;  //le nombre de '[' rencontrés pendant la recherche
        while(i<programme.length()) {
            if(programme.charAt(i)=='['){k++;}
            else if(k!=0 && programme.charAt(i)==']'){k--;}
            else if(k==0 && programme.charAt(i)==']'){return i;}
            i++;
        }
        return -1;  //~erreur
    }
   
    static int ouvrantCorrespondant_bis(String programme, int depart) {
        int i=depart-1;
        int k=0;  //le nombre de ']' rencontrés pendant la recherche
        while(i>0) {
            if(programme.charAt(i)==']') {k-=-1;}
            else if(k!=0 && programme.charAt(i)=='[') {k+=-1;}
            else if(k==0 && programme.charAt(i)=='[') {return i;}
            i+=-1;
        }
        return -1;//~erreur
    }
}
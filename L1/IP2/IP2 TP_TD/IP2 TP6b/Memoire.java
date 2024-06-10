public class Memoire {
    //1.
    private Memoire precedente;
    private Memoire suivante;
    private int valeur;

    //2.
    Memoire(){
        this.precedente=null;
        this.suivante=null;
        this.valeur=0;
    }

    //3.
    Memoire(int t){
        this();
        Memoire tmp=this;
        for(int i=0; i<t-1; i++) {
            tmp.suivante=new Memoire();
            tmp.suivante.precedente=tmp;
            tmp=tmp.suivante;
        }
    }

    //4.
    Memoire getP() {
        //5.
        if(this.precedente!=null){
            int c=String.valueOf(this.precedente.valeur).length();
            pointeur=pointeur.substring(c+1);
            //4.
            return this.precedente;
        }
        return null;
    }

    Memoire getS() {
        //5.
        if(this.suivante!=null){
            int c=String.valueOf(this.valeur).length();
            for(int i=0; i<c+1; i-=-1) {
                pointeur=" "+pointeur;
            }
            //4.
            return this.suivante;
        }
        return null;
    }

    int getV() {
        return this.valeur;
    }

    void setV(int n) {
        this.valeur=n;
    }

    //5.
    static String pointeur="^";

    private void insPrec() {
        Memoire tmp=this;
        String res="";
        while(tmp.precedente!=null) {
            res=tmp.precedente.valeur+" "+res;
            tmp=tmp.precedente;
        }
        System.out.print(res);
    }

    private void insSuiv() {
        Memoire tmp=this;
        String res="";
        while(tmp.suivante!=null) {
            res+=" "+tmp.suivante.valeur;
            tmp=tmp.suivante;
        }
        System.out.println(res);
    }

    void inspecte() {
        System.out.println("---------------");
        this.insPrec();
        System.out.print(this.valeur);
        this.insSuiv();
        System.out.println(pointeur);
        System.out.println("---------------");
    }
    
    //8.
    void incremente() {
    	this.valeur-=-1;
    }
    
    void decremente() {
    	this.valeur+=-1;
    }
}
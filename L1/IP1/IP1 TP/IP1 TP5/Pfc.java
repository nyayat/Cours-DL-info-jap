import java.util.Random;
import java.util.Scanner;

public class Pfc{

    public static String quelCoup(int k){
	String res="";
	if(k==0 || k==1){
	    res="Pierre";
	}
	else if(k>=2 && k<=5){
	    res="Feuille";
	}
	else if(k==6 || k==7){
	    res="Ciseaux";
	}
	else if(k==8){
	    res="Lézard";
	}
	else{
	    res="Spock";
	}
	return res;
    }
    
    public static String tirage (){
        int k=randRange(0,5);
	String res=quelCoup(k);
	return (res);

    }

    public static String tirageraté (){
        int k=randRange(0,10);
	String res=quelCoup(k);
	return (res);

    }

    public static String coupJoueur(){
	String c=System.console().readLine();
	if (!(c.equals("Ciseaux")||c.equals("Feuille")||c.equals("Pierre")||c.equals("Lézard")||c.equals("Spock"))){
	    c="Erreur.";
	}
	return c;
    }

    public static String uneManche(){
	String j=coupJoueur();
	String o=tirage();
	String res=o+"\nGagné";
	if (j.equals("Erreur.")){
	    res=o+"\nImpossible";
	}
	else if (j.equals(o)){
	    res=o+"\nNul";
	}
	else if (j.equals("Pierre") && o.equals("Feuille") || j.equals("Feuille") && o.equals("Ciseaux") || j.equals("Ciseaux") && o.equals("Pierre") || o.equals("Lézard") && j.equals("Papier") || o.equals("Lézard") && j.equals("Spock") || o.equals("Pierre") && j.equals("Lézard") || o.equals("Ciseaux") && j.equals("Lézard") || o.equals("Spock") && j.equals("Pierre") || o.equals("Spock") && j.equals("Ciseaux") || o.equals("Papier") && j.equals("Spock")){
	    res=o+"\nPerdu";
	}
	return res;
    }

    
    public static String manche(){
	String res="J";
	String j=coupJoueur();
	String o=tirage();
	if (j.equals("Erreur.")){
	    res="Impossible";
	}
	else if (j.equals(o)){
	    res="E";
	}
	else if (j.equals("Pierre") && o.equals("Feuille") || j.equals("Feuille") && o.equals("Ciseaux") || j.equals("Ciseaux") && o.equals("Pierre") || o.equals("Lézard") && j.equals("Papier") || o.equals("Lézard") && j.equals("Spock") || o.equals("Pierre") && j.equals("Lézard") || o.equals("Ciseaux") && j.equals("Lézard") || o.equals("Spock") && j.equals("Pierre") || o.equals("Spock") && j.equals("Ciseaux") || o.equals("Papier") && j.equals("Spock")){
	    res="O";
	}
	return res;
    }

    public static void chifoumi(int n){
	String res="";
	String res2="Nul";
	int j=0;
	int o=0;
        for (int i=0; i<n; i++){
	    String M=manche();
	    res=res+M;
	    if(M.equals("O")){
		o++;
	    }
	    else if(M.equals("J")){
		j++;
	    }
	}
	if (o>j){
	    res2="Vainqueur : Ordinateur\n"+o;
	}
	else if (j>o){
	    res2="Vainqueur : Joueur\n"+j;
	}
	System.out.println(res+"\n"+res2);
    }


    public static void chifoumi2(){
        int n=readInt();
	String res="";
	String res2="Nul";
	int j=0;
	int o=0;
        for (int i=0; i<n; i++){
	    String M=manche();
	    res=res+M;
	    if(M.equals("O")){
		o++;
	    }
	    else if(M.equals("J")){
		j++;
	    }
	}
	if (o>j){
	    res2="Vainqueur : Ordinateur\n"+o;
	}
	else if (j>o){
	    res2="Vainqueur : Joueur\n"+j;
	}
	System.out.println(res+"\n"+res2);
    }


    public static void chifoumi3(){
        int n=readInt();
	String res="";
	String res2="Nul";
	int j=0;
	int o=0;
        while(o<n && j<n){
	    String M=manche();
	    res=res+M;
	    if(M.equals("O")){
		o++;
	    }
	    else if(M.equals("J")){
		j++;
	    }
	}
	if (o>j){
	    res2="Vainqueur : Ordinateur\n"+o;
	}
	else if (j>o){
	    res2="Vainqueur : Joueur\n"+j;
	}
	System.out.println(res+"\n"+res2);
    }

    public static String manche2(){
	String res="J";
	String j=tirageraté();
	String o=tirage();
	if (j.equals("Erreur.")){
	    res="Impossible";
	}
	else if (j.equals(o)){
	    res="E";
	}
	else if (j.equals("Pierre") && o.equals("Feuille") || j.equals("Feuille") && o.equals("Ciseaux") || j.equals("Ciseaux") && o.equals("Pierre") || o.equals("Lézard") && j.equals("Papier") || o.equals("Lézard") && j.equals("Spock") || o.equals("Pierre") && j.equals("Lézard") || o.equals("Ciseaux") && j.equals("Lézard") || o.equals("Spock") && j.equals("Pierre") || o.equals("Spock") && j.equals("Ciseaux") || o.equals("Papier") && j.equals("Spock")){
	    res="O";
	}
	return res;
    }

    public static void chifoumi4(){
        int n=readInt();
	String res="";
	String res2="Nul";
	int j=0;
	int o=0;
        while(o<n && j<n){
	    String M=manche2();
	    res=res+M;
	    if(M.equals("O")){
		o++;
	    }
	    else if(M.equals("J")){
		j++;
	    }
	    System.out.println(M);
	}
	if (o>j){
	    res2="Vainqueur : Ordinateur\n"+o;
	}
	else if (j>o){
	    res2="Vainqueur : Joueur\n"+j;
	}
	System.out.println(res+"\n"+res2);
    }
    
    
    public static void main(String[] args) {
	chifoumi4();
    }

    static Random rand = new Random () ;
    public static int randRange ( int a , int b ) {
	return (rand.nextInt(b-a)+a);
    }

    public static int readInt(){
	Scanner sc=new Scanner (System.in);
	return sc.nextInt();
    }
}

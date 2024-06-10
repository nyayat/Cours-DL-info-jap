import java.util.Random;

public class TP8{
    //Ex 1
    public static int[][] CreateGraph (int n){
	int [][] res=new int [n][n];
	for(int i=0; i<n; i++){
	    for(int j=i+1; j<n; j++){
		if(i!=j){
		    res[i][j]=randRange(0,2);
		    res[j][i]=res[i][j];
		}
		else{
		    res[i][j]=0;
		}
	    }
	}
	return res;
    }

    public static int friends_nbr(int[][] R, int a){  //affiche nombre d'amis de a
	int s=0;
	for(int i=0; i<R.length; i++){
	    if(R[a][i]==1){
		s++;
	    }
	}
	return s;
    }

    public static int[]friends(int[][]R, int a){  //affiche amis de a
	int[]res={};
	for(int i=0; i<R.length; i++){
	    if(R[a][i]==1){
		res=insertEnd(res,i);
	    }
	}
	return res;
    }

    public static int[]pop(int[][]R){  //fabriquer un tableau avec le nombre d'amis de chacun
	int[]tmp=new int[R.length];
	for(int i=0; i<R.length; i++){
	    tmp[i]=friends_nbr(R,i);
	}
	return tmp;
    }

    public static int[]popular(int [][]R){  //trie par popularite
	int[]res={0};
	int[]tmp=pop(R);
	for(int i=1; i<tmp.length; i++){  //pour classer toutes les personnes dans le reseaux
	    int j=res.length;  //position maximale possible
	    while(j>0 && tmp[i]>tmp[res[j-1]]){  //compare avec les valeurs deja dans tableau reponses
		j--;
	    }
	    res=insert(res,j,i);
	}
	return res;
    }

    public static int [] common_friends(int[][]R, int a, int b){  //affiche ami en commun de a et b
	int[]res={};
	for(int j=0; j<R.length; j++){
	    if(R[a][j]==1 && R[b][j]==1){
		res=insertEnd(res,j);
	    }
	}
	return res;
    }

    public static int [][] add_user(int[][]R, int[]t){  //rajoute t dans le reseau
	int[][]res=new int [R.length+1][R.length+1];
	for(int i=0; i<R.length; i++){
	    for(int j=0; j<R.length; j++){
		res[i][j]=R[i][j];
	    }
	}
	for(int i=0; i<R.length; i++){
	    res[R.length][i]=t[i];
	    res[i][R.length]=t[i];
	}
	return res;
    }
	    
    public static String [] popularN(int[][]R, String[]noms){
	int[]tmp=popular(R);
	String [] res=new String[tmp.length];
	for(int i=0; i<tmp.length; i++){
	    res[i]=noms[tmp[i]];
	}
	return res;
    }
    

    //Ex 2
    public static int[] invite(int[][]R, int a){
	int[]res=friends(R,a);
	int[]tmp=friends(R,a);  //necessaire pour trouver les amis des amis de a
	for(int i=0; i<tmp.length; i++){
	    for(int j=0; j<tmp.length; j++){
		if (R[tmp[i]][j]==1 && !search(res,j) && j!=a){
		    res=insertEnd(res,j);
		}
	    }
	}
	return res;
    }
		
    public static int[] invite_strict(int[][]R, int a){
	int[]res={};
	for(int i=0; i<R.length; i++){
	    int s=0;
	    for(int k=0; k<R.length; k++){
		s=s+R[i][k]*R[k][a];
	    }
	    if(s==2){
		res=insertEnd(res,i);
	    }
	}
	return res;
    }

    public static String[] inviteN(int[][] R, String[] n, int a){
	int[] t=invite(R,a);
	String[]res=new String[t.length];
	for(int i=0; i<t.length;i++){
	    res[i]=n[t[i]];
	}
	return res;
    }

    public static String[] strict_inviteN(int[][] R, String[] n, int a){
        int[] t=strict_invite(R,a);
	String[]res=new String[t.length];
	for(int i=0; i<t.length;i++){
	    res[i]=n[t[i]];
	}
	return res;
    }
	    
    //Tests
    public static void main(String[]args){
	String[] noms = {"0","1","2","3","4"};
	int[][]R=CreateGraph(10);
	IntInt(R);
	System.out.println();
	//System.out.println(friends_nbr(R,0));
	//Int(friends(R,0));
	/*System.out.println();
	  Int(pop(R));*/
	//Int(popular(R));
	//Int(common_friends(R,0,1));
	/*int[]t={0,1,1,0,0};
	  IntInt(add_user(R,t));*/
	/*System.out.println();
	  String(popularN(R,noms));*/
	//Int(invite(R,0));
	Int(invite_strict(R,0));
	
    }

    
    //Affichage de tableaux
    public static void IntInt(int[][]t){
	for(int i=0; i<t.length; i++){
	    System.out.print("{");
	    for(int j=0; j<t.length; j++){
		System.out.print(t[i][j]+".");
	    }
	    System.out.println("}");
	}
    }

    public static void Int(int[]t){
	System.out.print("{");	
	for(int i=0; i<t.length; i++){
	    System.out.print(t[i]+".");
	}
	System.out.println("}");
    }

    public static void String(String[]t){
	System.out.print("{");	
	for(int i=0; i<t.length; i++){
	    System.out.print(t[i]+".");
	}
	System.out.println("}");
    }

    //Insertion dans les tableaux
    public static int[] insertEnd(int[]t, int x){
	int [] res=new int [t.length+1];
	for(int i=0; i<t.length; i++){
	    res[i]=t[i];
	}
	res[t.length]=x;
	return res;
    }

    public static int[] insert(int[] t, int p, int x){
	int [] res=new int[t.length+1];
	res[p]=x;	
	for(int i=0; i<t.length; i++){
	    if(i<p){
		res[i]=t[i];
	    }
	    else{
		res[i+1]=t[i];
	    }
	}
	return res;
    }

    public static String[] insertEndS(String[] tab, String x){
	String []res=new String[tab.length+1];
	for (int i=0;i<tab.length;i++){
	    res[i]=tab[i];
	}
	res[res.length-1]=x;
	return res;
    }

    public static String[] insertS(String[] tab, int p, String x){
	String []res=new String[tab.length+1];
	for(int i=0; i<tab.length; i++){
	    if(i<p){
		res[i]=tab[i];
	    }
	    else if (i>=p){
		res[i+1]=tab[i];
	    }
	    res[p]=x;
	}
	return res;
    }

    //Fonction de recherche
    public static boolean search(int[]tab, int x){
	boolean b=false;
	for(int i=0; i<tab.length; i++){
	    if(tab[i]==x){
		b=true;
	    }
	}
	return b;
    }

    //Fonction random
    static Random rand = new Random () ;
    public static int randRange (int a ,int b) {
	return (rand.nextInt(b-a)+a);
    }
}

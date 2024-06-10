import java.util.*;
import java.io.*;
import java.util.Random;

public class Labyrinthe{
    //Cette fonction prend en argument le nom d'un fichier
    //contenant la description d'un labyrinthe
    //et renvoie la liste de liste d'entiers correspondante
    public static int[][]chargeLabyrinthe(String nomFichier){
	int[][]labyrinthe={};
	try{
	    Scanner sc=new Scanner(new File(nomFichier)).useDelimiter("\n");
	    int c=0;
	    //On compte le nombre de lignes
	    while(sc.hasNext()){
		c=c+1;
		String tmp=sc.next();
	    }
	    labyrinthe=new int[c][];
	    sc=new Scanner(new File(nomFichier)).useDelimiter("\n");
	    int i=0;
	    while(sc.hasNext()){
		String ligne=sc.next();
		String[] splitLigne=ligne.split(",");
		labyrinthe[i]=new int[splitLigne.length];
		for(int j=0;j<splitLigne.length;j=j+1){
		    labyrinthe[i][j]=Integer.parseInt(splitLigne[j]);
		}
		i=i+1;
	    }
	    
	}
	catch(Exception e){
	    System.out.println("Probleme dans la lecture du fichier "+nomFichier);
	    e.printStackTrace();
	}
	return labyrinthe;
    }

    public static void afficheLab(int[][]lab){
	for(int i=0; i<lab.length; i++){
	    for(int j=0; j<lab[i].length; j++){
		if(lab[i][j]==0){
		    System.out.print("X ");
		}
		else{
		    System.out.print("  ");
		}
	    }
	    System.out.println("|");
	}
	for(int i=0; i<lab.length; i++){
	    System.out.print("--");
	}
	System.out.println();
    }

    public static int[][] copieLab(int[][]lab){
	int[][]res=new int[lab.length][lab[0].length];
	for(int i=0; i<lab.length; i++){
	    for(int j=0; j<lab[i].length; j++){
		res[i][j]=lab[i][j];
	    }
	}
	return res;
    }

    public static int caseHaut(int[][]lab, int l, int c){
	int res=-1;
	if(l>0 && l<lab.length && c>-1 && c<lab.length){
	    res=lab[l-1][c];
	}
	return res;
    }
    
    public static int caseBas(int[][]lab, int l, int c){
	int res=-1;
	if(l>-1 && l<lab.length-1 && c>-1 && c<lab.length){
	    res=lab[l+1][c];
	}
	return res;
    }
    public static int caseDroite(int[][]lab, int l, int c){
	int res=-1;
	if(l>-1 && l<lab.length && c>-1 && c<lab.length-1){
	    res=lab[l][c+1];
	}
	return res;
    }
    public static int caseGauche(int[][]lab, int l, int c){
	int res=-1;
	if(l>-1 && l<lab.length && c>0 && c<lab.length){
	    res=lab[l][c-1];
	}
	return res;
    }

    public static int[][] voisinsLibres(int[][]lab, int l, int c){  //voisins qui contiennent des 1
	int comp=0;
	if(caseHaut(lab,l,c)==1){
	    comp++;
	}
	if(caseBas(lab,l,c)==1){
	    comp++;
	}
	if(caseDroite(lab,l,c)==1){
	    comp++;
	}
	if(caseGauche(lab,l,c)==1){
	    comp++;
	}
	int[][]res=new int[comp][2];
	comp--;
	if(caseHaut(lab,l,c)==1){
	    res[comp][0]=l-1;
	    res[comp][1]=c;
	    comp--;
	}
	if(caseBas(lab,l,c)==1){
	    res[comp][0]=l+1;
	    res[comp][1]=c;
	    comp--;
	}
	if(caseDroite(lab,l,c)==1){
	    res[comp][0]=l;
	    res[comp][1]=c+1;
	    comp--;
	}
	if(caseGauche(lab,l,c)==1){
	    res[comp][0]=l;
	    res[comp][1]=c-1;
	}
	return res;
    }

    public static void changeVoisins(int[][]lab, int l, int c, int i){
	int[][]tmp=voisinsLibres(lab,l,c);
	for(int j=0; j<tmp.length; j++){
	    lab[tmp[j][0]][tmp[j][1]]=i+1;
	}
    }
    
    public static void etapeParcours(int[][]lab, int i){
	for(int k=0; k<lab.length; k++){
	    for(int j=0; j<lab[k].length; j++){
		if(lab[k][j]==i){
		    changeVoisins(lab, k, j, i);
		}
	    }
	}
    }

    public static boolean finParcours(int[][]lab){
	if(lab[lab.length-1][lab[0].length-1]!=1){
	    return true;
	}
	boolean b=true;
        for(int i=0; i<lab.length; i++){
	    for(int j=0; j<lab[i].length; j++){
		if(lab[i][j]!=1){
		    b=b&&(voisinsLibres(lab,i,j).length>0);
		}
	    }
	}
	return b;
    }

    public static int parcours(int[][]lab){
	lab[0][0]=2;
	int i=2;
	while(!finParcours(lab)){
	    etapeParcours(lab, i);
	    i++;
	}
	int res=-1;
	if(lab[lab.length-1][lab[0].length-1]>1){
	    res=i-1;
	}
	return res;
    }

    //-------------------------------------------------------------------------------

    static Random rand = new Random ();
    public static int randInt (int min, int max){  // entre [min;max]
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static boolean finParcours2(int[][]lab){
	if(lab[lab.length-1][lab[0].length-1]==1){
	    for(int i=0; i<lab.length; i++){
		for(int j=0; j<lab[i].length; j++){
		    if(lab[i][j]==0 && voisinsLibres(lab,i,j).length<1){
			return false;
		    }
		}
	    }
	    return true;
	}
	return false;
    }

    public static int parcours2(int[][]lab){
	lab[0][0]=2;
	int i=2;
	while(lab[lab.length-1][lab[0].length-1]==1){
	    etapeParcours(lab,i);
	    i++;
	    if(!(search(lab, i-1))){
		lab[lab.length-1][lab[0].length-1]=0;
		i=0;
	    }
	}
	return (i-1);
    }

    public static boolean search(int[][]lab, int i){
	for(int j=0; j<lab.length; j++){
	    for(int k=0; k<lab[0].length; k++){
		if(lab[j][k]==i){
		    return true;
		}
	    }
	}
	return false;
    }
    
    public static int[][] genereLab(int n, int m, int a){
	int[][]res=new int[n][m];
	res[0][0]=1;
	while(!finParcours2(res)){
	    for(int i=0; i<a; i++){
		int t1=randInt(0,res.length-1);
		int t2=randInt(0,res[0].length-1);
		if(res[t1][t2]==0){
		    res[t1][t2]=1;
		}
	    }
	}
	return res;
    }

    //---------------------------------------------------------------
	        
    public static void main(String[] args){
	/*int[][]lab=chargeLabyrinthe("labyrinthe1.csv");
	afficheLab(lab);
	//printIntInt(voisinsLibres(lab,0,0));
	System.out.println(parcours(lab));*/
	
	int[][]lab=genereLab(30,30,1);
	afficheLab(lab);
	System.out.println(parcours2(lab));
	//printIntInt(lab);
    }

    public static void printIntInt(int[][]t){
	for(int i=0; i<t.length; i++){
	    for(int j=0; j<t[i].length; j++){
		if(t[i][j]<10){
		    System.out.print(t[i][j]+"  ");
		}
		else if(t[i][j]<100){
		    System.out.print(t[i][j]+" ");
		}
	    }
	    System.out.println();
	}
    }
    
}

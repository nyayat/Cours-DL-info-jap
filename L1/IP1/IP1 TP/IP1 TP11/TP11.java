import java.util.Random;
public class TP11{

    public static int[][] grilleSimple (){
	int[][]grille={{1,2,3,4,5,6,7,8,9},{4,5,6,7,8,9,1,2,3},{7,8,9,1,2,3,4,5,6},
		       {2,3,1,5,6,4,8,9,7},{5,6,4,8,9,7,2,3,1},{8,9,7,2,3,1,5,6,4},
		       {3,1,2,6,4,5,9,7,8},{6,4,5,9,7,8,3,1,2,},{9,7,8,3,1,2,6,4,5}};
	return grille;
    }
	
    public static void  afficheGrille (int[][] grille){
	for(int i=0; i<9; i++){
	    for(int j=0; j<9; j++){
		System.out.print(grille[i][j]+" ");
		if(j==2 || j==5){
		    System.out.print(" ");
		}
	    }
	    System.out.println();
	    if(i==2 || i==5){
		System.out.println();
	    }
	}
    }

    public static void echangeLignes (int[][] grille, int line1, int line2){
        for(int i=0; i<9; i++){
	    int tmp=grille[line1][i];
	    grille[line1][i]=grille[line2][i];
	    grille[line2][i]=tmp;
	}
    }

    public static void echangeColonnes (int[][] grille, int col1, int col2){
	for(int i=0; i<9; i++){
	    int tmp=grille[i][col1];
	    grille[i][col1]=grille[i][col2];
	    grille[i][col2]=tmp;
	}
    }

    /////////////////////////////////////////////////////////////////////////////////

    public static Random rand = new Random ();
    public static int randRange (int a, int b){
	return rand.nextInt (b-a)+a;
    }

    public static void echangeLignesAleatoire (int[][] grille){
	int i=randRange(0,3);
	int j=randRange(0,3);
	int k=randRange(0,3);
	echangeLignes(grille,i+3*j,k+3*j);
    }

    public static void echangeColonnesAleatoire (int[][] grille){
	int i=randRange(0,3);
	int j=randRange(0,3);
	int k=randRange(0,3);
	echangeColonnes(grille,i+3*j,k+3*j);
    }

    /////////////////////////////////////////////////////////////////////////////////
    
    public static int[][] produitGrillePleineAleatoire(int n){
	int[][] grille=grilleSimple();
	for(int i=0; i<n; i++){
	    int a=randRange(0,2);
	    if(a==0){
		echangeLignesAleatoire(grille);
	    }
	    else{
		echangeColonnesAleatoire(grille);
	    }
	}
	return grille;
    }

    public static int[][] produitSudoku (int n, int m){
	int[][]grille=produitGrillePleineAleatoire(n);
	int k=0;
	while(k<m){
	    int i=randRange(0,9);
	    int j=randRange(0,9);
	    if(grille[i][j]!=0){
		grille[i][j]=0;
		k++;
	    }
	}
	return grille;
    }

    /////////////////////////////////////////////////////////////////////////////////

    public static boolean isP(int[]t, int a){
	for(int i=0; i<t.length; i++){
	    if(t[i]==a){
		return true;
	    }
	}
	return false;
    }

    public static int[] aide (int[][] grille,  int i, int j){
	if(grille[i][j]!=0){
	    int[]res1={grille[i][j]};
	    return res1;
	}
        int[]tmp=new int[9];
	int c=0;
	for(int k=0; k<9; k++){
	    if(grille[k][j]!=0 && !isP(tmp, grille[k][j])){  //vérification de la colonne
		tmp[c]=grille[k][j];
		c++;
	    }
	    if(grille[i][k]!=0 && !isP(tmp, grille[i][k])){  //vérification de la ligne
		tmp[c]=grille[i][k];
		c++;
	    }
	}
	i=3*(i/3);  //initialise i à la première ligne du carré
	j=3*(j/3);  //initialise j à la première colonne du carré
	for(int l=i; l<i+3; l++){  //vérification du carré
	    for(int k=j; k<j+3; k++){
		if(grille[l][k]!=0 && !isP(tmp, grille[l][k])){
		tmp[c]=grille[l][k];
		c++;
		}
	    }
	}
	int[]res=new int[9-c];
	c=0;
	for(int k=1; k<10; k++){
	    if(!isP(tmp,k)){
		res[c]=k;
		c++;
	    }
	}
	return res;
    }

    public static void printArray(int[]t){
	for(int i=0; i<t.length; i++){
	    System.out.print(t[i]+" ");
	}
	System.out.println();
    }
	    
    public static void main (String[] args){
	/*int[][]grille=grilleSimple();
	//echangeLignes(grille,0,4);
	//echangeColonnes(grille,0,4);
	//echangeLignesAleatoire(grille);
	//echangeColonnesAleatoire(grille);
	afficheGrille(grille);*/
	
	//afficheGrille(produitGrillePleineAleatoire(5));

	int[][]grille=produitSudoku(0,20);
	afficheGrille(grille);
	printArray(aide(grille,0,0));
    }
}

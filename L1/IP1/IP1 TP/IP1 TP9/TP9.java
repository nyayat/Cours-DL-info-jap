import java.util.Random ;

public class TP9 {

    public static Random rand = new Random ();
    
    public static int randRange (int a, int b){
	return rand . nextInt (b-a)+a;
    }

    public static int [][] gridComp = new int [10][10];
    public static int [][] gridPlay = new int [10][10];

    //Ex 1//////////////////////////////////////////////////////////////////
    
    public static boolean posOk (int [][] grille , int l, int c, int d, int t){
	boolean b=false;
	if (d==1 && c+t<10){
	    b=true;
	    for(int i=0; i<t; i++){
		if(grille[l][c+i]!=0){
		    b=false;
		}
	    }
	}
	else if (d==2 && l+t<10){
	    b=true;
	    for(int i=0; i<t; i++){
		if(grille[l+i][c]!=0){
		    b=false;
		}
	    }
	}
	return b;
    }

    public static void initGridComp (){
	int l=randRange(0,10);
	int c=randRange(0,10);
	int d=randRange(1,3);
	int[][]bateaux={{5,1},{4,2},{3,3},{3,4},{2,5}};
	for(int i=0; i<bateaux.length; i++){
	    while(!posOk(gridComp,l,c,d,bateaux[i][0])){
		l=randRange(0,10);
		c=randRange(0,10);
		d=randRange(1,3);
	    }
	    for(int j=0; j<bateaux[i][0]; j++){
		if(d==1){
		    gridComp[l][c+j]=bateaux[i][1];
		}
		else{
		    gridComp[l+j][c]=bateaux[i][1];
		}
	    }
	}
    }

    public static void printGrid(int[][]grille){
	System.out.println("   A B C D E F G H I J");
	for(int i=1; i<11; i++){
	    System.out.print(i+" ");
	    if(i<10){
		System.out.print(" ");
	    }
	    for(int j=0; j<10; j++){
		System.out.print(grille[i-1][j]+" ");
	    }
	    System.out.println();
	}
    }

    //Ex 2//////////////////////////////////////////////////////////////////

    public static void initGridPlay(){
	int[][]bateaux={{5,1},{4,2},{3,3},{3,4},{2,5}};
	String [] colonne={"A","B","C","D","E","F","G","H","I","J"};
	String [] nomBateaux={"porte-avions", "croiseur", "contre-torpilleurs", "sous-marin", "torpilleur"};
	for(int i=0; i<nomBateaux.length; i++){
	    System.out.println("Saisir une lettre (colonne) entre A et J pour le "+nomBateaux[i]+" : ");
	    String sc=readString();
	    int c=0;
	    while(!(colonne[c].equals(sc)) && c<11){
		c++;
	    }
	    System.out.println("Saisir un nombre (ligne) entre 1 et 10 pour le "+nomBateaux[i]+" : ");
	    int l=readInt();
	    l--;
	    System.out.println("Mettre en horizontal(1) ou en vertical(2) : ");
	    int d=readInt();
	    while(!posOk(gridPlay,l,c,d,bateaux[i][0])){
		System.out.println("Erreur : le "+nomBateaux[i]+" ne rentre pas dans la grille.");
		System.out.println("Saisir une lettre (colonne) pour le "+nomBateaux[i]+" : ");
		sc=readString();
		c=0;
		while(!(colonne[c].equals(sc)) && c<11){
		    c++;
		}
		System.out.println("Saisir un nombre (ligne) pour le "+nomBateaux[i]+" : ");
		l=readInt();
		l--;
		System.out.println("Mettre en horizontal(1) ou en vertical(2) : ");
		d=readInt();
	    }
	    for(int j=0; j<bateaux[i][0]; j++){
		if(d==1){
		    gridPlay[l][c+j]=bateaux[i][1];
		}
		else{
		    gridPlay[l+j][c]=bateaux[i][1];
		}
	    }
	}
    }
    
    public static String readString(){
	return System.console().readLine();
    }
    public static boolean isInt (String s){
	return s. matches("\\d+");
    }
    public static int readInt(){
	while (true) {
	    String s = readString();
	    if (isInt(s)){
		return Integer.parseInt(s);
	    }
	}
    }

    //Ex 3//////////////////////////////////////////////////////////////////

    public static boolean hasDrowned(int[][]grid, int bat){
	boolean b=true;
	int i=0;
	while (b && i<grid.length){
	    for(int j=0; j<grid.length; j++){
		if(grid[i][j]==bat){
		    b=false;
		}
	    }
	    i++;
	}
	return b;
    }

    public static void oneMove(int[][]grid, int l, int c){
	String [] nomBateaux={"porte-avions", "croiseur", "contre-torpilleurs", "sous-marin", "torpilleur"};
	int bat=grid[l][c];	
	if(bat!=0 && bat!=6){
	    grid[l][c]=6;
	    if(hasDrowned(grid, bat)){
		System.out.println("Coule : "+ nomBateaux[bat-1]);
	    }
	    else{
		System.out.println("Touche : "+ nomBateaux[bat-1]);
	    }
	}
	else{
	    System.out.println("A l'eau");
	}
    }

    public static int[] playComp(){
	int [] res={randRange(0,10),randRange(0,10)};
	return res;
    }

    public static boolean isOver(int[][]grid){
	boolean b=true;
	int i=0;
	while (b && i<10){
	    for(int j=0; j<10; j++){
		if(grid[i][j]!=0 && grid[i][j]!=6){
		    b=false;
		}
	    }
	    i++;
	}
	return b;
    }

    //Ex 4//////////////////////////////////////////////////////////////////

    public static void play(){
	String [] colonne={"A","B","C","D","E","F","G","H","I","J"};
	initGridComp();
	initGridPlay();
	boolean flagV=false;  //victoire si true
	int[]tourPC=new int[2];
	while(!flagV){
	    tourPC=playComp();
	    System.out.println("\n-----------------------------------------\ntour du pc : " + colonne[tourPC[1]] + "," + (tourPC[0]+1));
	    oneMove(gridPlay,tourPC[0],tourPC[1]);
	    if(isOver(gridPlay)){
		flagV=true;
		System.out.println("Victoire du pc");
	    }
	    if(!flagV){	    
		System.out.println("\n-----------------------------------------\nSaisir une lettre (colonne) entre A et J : ");
		String sc=readString();
		int c=0;
		while(!(colonne[c].equals(sc)) && c<11){
		    c++;
		}
		System.out.println("Saisir un nombre (ligne) entre 1 et 10 : ");
		int l=readInt();
		l--;
		while(c>9 || l>9){
		    System.out.println("Erreur : le mouvement n'est pas autorise");
		    System.out.println("Saisir une lettre (colonne) entre A et J correctement : ");
		    sc=readString();
		    c=0;
		    while(!(colonne[c].equals(sc)) && c<11){
			c++;
		    }
		    System.out.println("Saisir un nombre (ligne) entre 1 et 10 correctement : ");
		    l=readInt();
		    l--;
		}
		System.out.println("\ntour du joueur : " + colonne[c] + "," + (l+1));
		oneMove(gridComp,l,c);
	    }
	    if(isOver(gridComp)){
		flagV=true;
		System.out.println("Victoire du joueur");
	    }
	}
    }

    ////////////////////////////////////////////////////////////////////////
    
    public static void main (String[] args) {
	/*initGridPlay();
	System.out.println("Grille du joueur : ");
	printGrid(gridPlay);
	initGridComp();
	System.out.println("\nGrille de l'ordinateur : ");
	printGrid(gridComp);*/
	play();
	
    }
    
}


import java.util.*;
import java.io.*;

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


    
    public static void main(String[] args){
	int[][]lab=chargeLabyrinthe("labyrinthe1.csv");
    }

}

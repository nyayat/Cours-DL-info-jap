import java.io.IOException;

public class Display {

    public static int[][] negatif(int[][]t){
	int[][]res=new int[t.length][t[0].length];
	for(int i=0; i<t.length; i++){
	    for(int j=0; j<t[i].length; j++){
		res[i][j]=255-t[i][j];
	    }
	}
	return res;
    }

    public static int[][] flip(int[][]t){  //inversion des lignes
	int[][]res=new int[t.length][t[0].length];
	for(int i=0; i<t.length; i++){
	    for(int j=0; j<t[i].length; j++){
		res[i][j]=t[t.length-i-1][j];
	    }
	}
	return res;
    }

    ///////////////////////////////////////////////////////////////////////////

    public static int valAbs(int n){
	if(n<0){
	    n=-n;
	}
	return n;
    }

    public static int[][] bord(int[][]t){
	int[][]res=new int[t.length][t[0].length];
	for(int x=1; x<t.length; x++){
	    for(int y=1; y<t[x].length; y++){
		int delta=valAbs(t[x-1][y]-t[x][y])+valAbs(t[x][y-1]-t[x][y]);
		if(delta>50){
		    res[x][y]=0;
		}
		else{
		    res[x][y]=255;
		}
	    }
	}
	return res;
    }
    ///////////////////////////////////////////////////////////////////////////

    public static void drawASCII(int[][]t){
	for(int i=0; i<t.length; i++){
	    for(int j=0; j<t[0].length; j++){
		if(t[i][j]<26){
		    System.out.print("@");
		}
		else if(t[i][j]<51){
		    System.out.print("%");
		}
		else if(t[i][j]<76){
		    System.out.print("#");
		}
		else if(t[i][j]<101){
		    System.out.print("*");
		}
		else if(t[i][j]<126){
		    System.out.print("+");
		}
		else if(t[i][j]<151){
		    System.out.print("=");
		}
		else if(t[i][j]<176){
		    System.out.print("-");
		}
		else if(t[i][j]<201){
		    System.out.print(":");
		}
		else if(t[i][j]<226){
		    System.out.print(".");
		}
		else{
		    System.out.print(" ");
		}
	    }
	    System.out.println();
	}
	System.out.println();
    }

    //////////////////////////////////////////////////////////////////////////////

    public static int nbPixInf(int[][]t, int a){
	int res=0;
        for(int i=0; i<t.length; i++){
	    for(int j=0; j<t[i].length; j++){
		if(t[i][j]<=a){
		    res++;
		}
	    }
	}
	return res;
    }

    public static int[][] egalise(int[][]t){
	int nbPix=t.length*t[0].length;
	int[][]res=new int[t.length][t[0].length];
	for(int i=0; i<t.length; i++){
	    for(int j=0; j<t[i].length; j++){
		res[i][j]=(255*nbPixInf(t,t[i][j]))/nbPix;
	    }
	}
	return res;
    }
    ///////////////////////////////////////////////////////////////////////////

    public static int[][] melange(int[][]t){
	int nbl=t.length;
	int nbc=t[0].length;
	int[][]res=new int[nbl][nbc];
	if(nbl%2==1){  //pour nb de lignes impair
	    for(int i=0; i<nbc; i++){
		res[nbl-1][i]=t[nbl-1][i];
	    }
	    nbl--;
	}
	if(nbc%2==1){  //pour nb de colonnes impair
	    for(int i=0; i<nbl; i++){
		res[i][nbc-1]=t[i][nbc-1];
	    }
	    nbc--;
	}
	for(int i=0; i<nbl; i=i+2){
	    for(int j=0; j<nbc; j=j+2){
		res[i][j]=t[i][j+1];
		res[i][j+1]=t[i+1][j+1];
		res[i+1][j+1]=t[i+1][j];
		res[i+1][j]=t[i][j];
	    }
	}
	return res;		
    }

    /*  i,j  |  i,j+1
      ---------------- rotation dans le sens trigo
       i+1,j | i+1,j+1 */

    ///////////////////////////////////////////////////////////////////////////

    public static int[][] compress(int[][]t){  //on ne prend pas en compte n ou m impair :)
	int[][]res=new int [t.length/2][t[0].length/2];
	for(int i=0; i<t.length; i=i+2){
	    for(int j=0; j<t[0].length; j=j+2){
		res[i/2][j/2]=(t[i][j+1]+t[i+1][j+1]+t[i+1][j]+t[i][j])/4;
	    }
	}
	return res;
    }

    public static int[][] compressBis(int[][]t){  //on prend en compte n ou m impair
	int nbl=t.length;
	int nbc=t[0].length;
	int[][]res=new int [nbl/2+nbl%2][nbc/2+nbc%2];
	for(int i=0; i<nbl; i=i+2){
	    for(int j=0; j<nbc; j=j+2){
		if(i==nbl-1 && j==nbc-1){  //j=colonne impair et i=ligne impair
		    res[i/2][j/2]=t[i][j];
		}
		else if (j==nbc-1){  //j=colonne impair juste
		    res[i/2][j/2]=(t[i][j]+t[i+1][j])/2;
		}
		else if (i==nbl-1){  //i=ligne impair juste
		    res[i/2][j/2]=(t[nbl-1][j]+t[nbl-1][j+1])/2;
		}
		else{
		    res[i/2][j/2]=(t[i][j+1]+t[i+1][j+1]+t[i+1][j]+t[i][j])/4;
		}
	    }
	}
	return res;
    }
	
    ///////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) throws IOException {
	String filename="/home/shana/TP/overexposed.jpg";
	String filename2="/home/shana/TP/image3.jpg";
	int[][]t=getGray(filename);

	//saveGray(bord(t), filename2);

	//drawASCII(t);
	
	/*int[][]t={{0,0},{255,255}};
	  printArrayArray(egalise(t));*/

	/*int [][] exemple={{0,1},{2,3}};
	int [][] exemple={{0,128,255},{127,255,255},{255,255,255}};
	printArrayArray(melange(exemple));*/

	/*int [][] exemple1 = {
	    {0 , 100, 200, 255, 100},
	    {0 , 100, 200, 255, 150},
	    {0 , 100, 200, 255, 100},
	    {0 , 100, 200, 255, 110},
	    {10, 100, 100, 210, 150}};
	    printArrayArray(compressBis(exemple));*/

	
    }

    ///////////////////////////////////////////////////////////////////////////

    public static void printArrayArray(int[][]t){
	for(int i=0; i<t.length; i++){
	    for(int j=0; j<t[i].length; j++){
		System.out.print(t[i][j]+" ");
	    }
	    System.out.println();
	}
	System.out.println();
    }
    
  public static int [][] getGray(String filename) throws IOException {
      Picture p = new Picture(filename);
      return p.getGray();
  }

  public static void drawGray(int [][] gray) {
    Picture p = new Picture(gray);
    p.draw();
  }

  public static void saveGray(int [][] gray, String filename) throws IOException {
    Picture p = new Picture(gray);
    p.save(filename);
  }
}

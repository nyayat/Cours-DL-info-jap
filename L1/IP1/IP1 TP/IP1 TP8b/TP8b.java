public class TP8b{
    //Ex 1
    public static void printLines(int[][]t){
	for(int i=0; i<t.length; i++){
	    for(int j=0; j<t[i].length; j++){
		System.out.print(t[i][j]+" ");
	    }
	    System.out.println();
	}
    }

    public static int[][] cutIntArrayArray(int[][] arr, int n){
	int[][]res=new int[n][];
	for (int i=0; i<n; i++){
	    res[i]= new int [arr[i].length];
	    for(int j=0; j<arr[i].length;j++){
		res[i][j]=arr[i][j];
	    }
	}
	return res;
    }

    //Ex 2
    public static int sumArrayOfArrays(int[][] t){
	int s=0;
	for (int i=0;i<t.length;i++){
	    for (int j=0;j<t[i].length;j++){
		s=s+t[i][j];;
	    }
	}
	return s;
    }

    public static boolean holdsOddInt(int[][] t){
	boolean b=false;
	int i=0;
	while(!b && i<t.length){
	    for (int j=0;j<t[i].length;j++){
		if (t[i][j]%2==1){
		    b=true;
		}
	    }
	    i++;
	}
	return b;
    }

    public static int numberOfOneDigitInt(int[][] t){
	int res=0;
	for (int i=0;i<t.length;i++){
	    for (int j=0;j<t[i].length;j++){
		if (t[i][j]/10==0 && t[i][j]>=0){
		    res++;
		}
	    }
	}
	return res;
    }

    public static int[][] positionArray(int[][] t){
	int c=0;  //maximum de tableaux necessaires
	int m=0;  //compteur de tableau
	for (int i=0;i<t.length;i++){
	    for (int j=0;j<t[i].length;j++){
		c++;
	    }
	}
	int [][] res=new int[c][2];
	for (int i=0;i<t.length;i++){
	    for (int j=0;j<t[i].length;j++){
		if (t[i][j]%9==0){
		    res[m][0]=i;
		    res[m][1]=j;
		    m++;
		}
	    }
	}
	res=cutIntArrayArray(res,m);
	return res;
    }

    public static int sumOfEvenInt(int[][] t){
	int s=0;
	for (int i=0;i<t.length;i++){
	    for (int j=0;j<t[i].length;j++){
		if(t[i][j]%2==0){
		    s=s+t[i][j];
		}
	    }
	}
	return s;
    }

    public static int[] rowSums(int[][] t){
	int[] res=new int[t.length];
	for (int i=0;i<t.length;i++){
	    int s=0;
	    for (int j=0;j<t[i].length;j++){
		s=s+t[i][j];
	    }
	    res[i]=s;
	}
	return res;
    }

    public static int[] rowWiseCount(int n, int[][] t){
	int[] res=new int[t.length];
	for (int i=0;i<t.length;i++){
	    int c=0;
	    for (int j=0;j<t[i].length;j++){
		if(t[i][j]>n){
		    c++;
		}
	    }
	    res[i]=c;
	}
	return res;
    }

    public static int maxCol(int[][] t){
	int max=t[0].length;
	for (int i=1;i<t.length;i++){
	    if(max<t[i].length){
		max=t[i].length;
	    }
	}
	return max;
    }
    
    public static int[] columnSums(int[][] t){
	int[] res=new int[maxCol(t)];
	for (int i=0;i<t.length;i++){
	    for (int j=0;j<t[i].length;j++){
		res[j]=res[j]+t[i][j];
	    }
	}
	return res;
    }

    //Ex 3
    public static boolean holdsCharlie(String[][] t){
	boolean b=false;
	int i=0;
	while(!b && i<t.length){
	    for (int j=0;j<t[i].length;j++){
		if (t[i][j].equals("Charlie")){
		    b=true;
		}
	    }
	    i++;
	}
	return b;
    }

    public static boolean holdsE(String[][] t){
	boolean b=false;
	int i=0;
	while(!b && i<t.length){
	    for (int j=0;j<t[i].length;j++){
		for(int k=0;k<t[i][j].length();k++){
		    if (t[i][j].charAt(k)=='e' || t[i][j].charAt(k)=='E'){
			b=true;
		    }
		}
	    }
	    i++;
	}
	return b;
    }

    public static int[][] fWordPositions(String[][] t, String s){
	int c=0;
	int m=0;
	for (int i=0;i<t.length;i++){
	    for (int j=0;j<t[i].length;j++){
		c++;
	    }
	}
	int [][] res=new int[c][2];
	for (int i=0;i<t.length;i++){
	    for (int j=0;j<t[i].length;j++){
		if (t[i][j].equals(s)){
		    res[m][0]=i;
		    res[m][1]=j;
		    m++;
		}
	    }
	}
	res=cutIntArrayArray(res,m);
	return res;
    }

    
       //Ex 4
    public static int[][] squareOfZeros(int n){
	int[][] res=new int[n][n];
	return res;
    }

    public static int[][] rectOfInt(int n, int p){
	int[][] res=new int[n][p];
	int k=1;
        for (int i=0;i<n;i++){
	    for (int j=0;j<p;j++){
		res[i][j]=k;
		k++;
	    }
	}
	return res;
    }

    public static int[][] triangleOfInt(int n){
	int [][]res=new int[n][];
	int k=1;
	for(int i=0; i<n; i++){
	    res[i]=new int[i+1];
	    for(int j=0; j<res[i].length; j++){
		res[i][j]=k;
		k++;
	    }
	}
	return res;
    }

    public static int[][] target (int n){
	int [][] res =new int[n][n];
	int k=0;  //pour reduire le carre a modifier petit a petit
	int I=n/2+n%2;  //intervalle des valeurs possibles -1 a mettre dans le carre
	for(int N=0; N<I; N++){
	    for(int i=k; i<n-k; i++){
		res[i][n-N-1]=N+1;  //pour modifier derniere colonne
		res[n-N-1][i]=N+1;  // pour modifier derniere ligne
		res[i][N]=N+1;  //pour modifier premiere colonne
		res[N][i]=N+1;  //pour modifier premiere ligne
	    }
	    k++;
	}
        return res;
    }

    //Ex 5
    public static int[][] pascal(int sz){
	int[][] res=new int[sz+1][];
	for(int n=0; n<res.length;n++){
	    res[n]=new int [n+1];
	    res[n][0]=1;
	    res[n][n]=1;
	    if(n>1){
		for(int k=1;k<n;k++){
		    res[n][k]=res[n-1][k-1]+res[n-1][k];
		}
	    }
	}
	return res;
    }

    public static int[] fiboClassique(int n){
	int[] res=new int[n];
	res[0]=1;
	res[1]=1;
	for (int i=2;i<n;i++){
	    res[i]=res[i-1]+res[i-2];
	}
	return res;
    }

    public static int pow(int x, int p){
	int res=1;
	for (int i=0;i<p;i++){
	    res=res*x;
	}
	return res;
    }
    
    public static int [][] fiboByDigits(int n){
	int[] fibo=fiboClassique(n);
	int max=fibo[fibo.length-1];
	int l=1;
	while(max!=0){
	    max=max/10;
	    l++;
	}
	int[][] res=new int[l][];
	int a=0;
	for(int i=0; i<l; i++){  //parcourir les lignes du tab rep
	    int c=0;
	    while(c+a<n && fibo[c+a]<pow(10,i+1)){
		c++;
	    }
	    res[i]=new int[c];
	    for(int k=a;k<a+c;k++){
		res[i][k-a]=fibo[k];
	    }
	    a=a+c;
	}
	return res;
    }

    public static int[] cycledesuka(int[][] f){
	int[] res=new int[f.length];
	for (int i=0;i<res.length;i++){
	    res[i]=f[i].length;
	}
	return res;
    }
    
    public static void main (String [] args) {
	//Ex 1
	/*int[][] t={{1, 2, 3}, {4, 5}, {6}};
	  printLines(t);*/
	/*int[][] c={{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}};
	  cutIntArrayArray(c,3);
	  cutIntArrayArray(c,5);*/

	//Ex 2
	/*int[][] t={{1, 2, 3}, {4, 5}};
	  System.out.println(sumArrayOfArrays(t));*/
	/*int[][] t1={{2, 4, 6}, {8, 10}, {12}};
	  int[][] t2={{4, 6, 3}, {2}, {9, 11}};
	  System.out.println(holdsOddInt(t1)+" "+holdsOddInt(t2));*/
	/*int[][] t1={{11, 3, 12}, {1, 100}};
	  int[][] t2={{11, -3, 12}, {1, 100}};
	  System.out.println(numberOfOneDigitInt(t1)+" "+numberOfOneDigitInt(t2));*/
	/*int[][] t={{9,4,27},{81},{3,45}};
	  printLines(positionArray(t));*/
	/*int[][] t={{2,3},{5,8,13,21},{34}};
	  printIntArray(columnSums(t));*/

	//Ex 3
	/*String[][] t1={{"Riri", "Fifi", "Loulou"}, {"Charlie", "Georgio", "Valéry"}, {"Franz"}};
	String[][] t2={{"Riri", "Fifi", "Loulou"}, {"Azozo", "Georgio", "Valéry"}, {"Franz"}};
	System.out.println(holdsCharlie(t1)+" "+holdsCharlie(t2));*/
	/*String[][] t1={{"Il", "abandonna", "son", "roman", "sur", "son", "lit"}, {"Il", "alla", "à", "son", "lavabo"}};
	  String[][] t2={{"Il", "aebandonna", "son", "roman", "sur", "son", "lit"}, {"Il", "alla", "à", "son", "lavabo"}};
	  String[][] t3={{"Il", "aEbandonna", "son", "roman", "sur", "son", "lit"}, {"Il", "alla", "à", "son", "lavabo"}};
	  System.out.println(holdsE(t1)+" "+holdsE(t2)+" "+holdsE(t3));*/
	/*String[][]Chine={{"Il","mangea","son","père","avec","une","fourchette"},{"Son","ami","noir","est","chinois"}};
	  printLines(fWordPositions(Chine,"chien-blond"));*/

	/*//Ex 4
	printLines(squareOfZero(3));
	printLines(rectOfInt(2,3));
	printLines(triangleOfInt(3));*/

	//Ex 5
	/*printLines(pascal(10));*/
	printLines(fiboByDigits(100));
	//printIntArray(fiboClassique(43));
	printIntArray(cycledesuka(fiboByDigits(100)));
    }

    //NAISU
    public static void printIntArray(int[] t){
	System.out.print("{");
	for (int i=0;i<t.length-1;i++){
	    System.out.print(t[i]+",");
	}
	System.out.println(t[t.length-1]+"}");
    }
}
    

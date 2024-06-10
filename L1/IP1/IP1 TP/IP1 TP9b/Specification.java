public class Specification {
    //Ex 1
    /*public static String charAtPosition(String s, int i) {
	if (i < 0 || i > s.length()) {
	    return "";
	} else
	    return String.valueOf(s.charAt(i));
	    }*/
    
    public static String charAtPosition(String s, int i) {
	if (i < 0 || i > s.length()) {
	    return "";
	}
	else{
	    return String.valueOf(s.charAt(i));
	}
    }    

    /*public static int minArray(int[] arr) {
	int min = 0;
	for (int i = 0; i < arr.length; i++) {
	    if (arr[i] < min) {
		min = arr[i];
	    }
	}
	return min;
	}*/

    public static int minArray(int[] arr) {
	int min = arr[0];
	for (int i = 1; i < arr.length; i++) {
	    if (arr[i] < min) {
		min = arr[i];
	    }
	}
	return min;
    }

    /*public static int[] initArray(int i) {
	int[] arr = new int[i-1];
	for (int j = 0; j < arr.length; j++) {
	    arr[i] = j+1;
	}
	return arr;
	}*/

    public static int[] initArray(int i) {
	int[] arr = new int[i-1];
	for (int j = 0; j < arr.length; j++) {
	    arr[j] = j+1;
	}
	return arr;
    }

    /*public static int dichotomicSearch(int[] arr, int i) {
	int min = 0;
	int max = arr.length;
	int pos = (min + max) / 2;

	while (min != max) {
	    if (arr[pos] < i) {
		min = pos;
	    } else if (arr[pos] > i) {
		max = pos;
	    } else {
		return pos;
	    }
	}
	return -1;
    }*/

    public static int dichoto1(int[] arr, int i) {
	int min = 0;
	int max = arr.length;
	int pos = (min + max) / 2;
        while (min!=max){
	    int tmp=pos;
	    if (arr[pos] < i) {
		min = pos;
	    } else if (arr[pos] > i) {
		max = pos;
	    } else {
		return pos;
	    }
	    pos = (min + max) / 2;
	    if(tmp==pos){
		min=max;
	    }
	}
	return -1;
    }

    public static int dichoto2(int[] arr, int i) {
	int min = 0;
	int max = arr.length;
	int pos = (min + max) / 2;
	if(arr[0]==i){
	    return 0;
	}
        while (min!=max && min!=max-1){
	    if (arr[pos] < i) {
		min = pos;
	    } else if (arr[pos] > i) {
		max = pos;
	    } else {
		return pos;
	    }
	    pos = (min + max) / 2;
	}
	return -1;
    }

    /*public static boolean forallNotEmpty(String[] arr) {
	boolean b = false;
	for (int i = 0; i < arr.length; i++) {
	    if (!"".equals(arr[i])) {
		b = true;
	    }
	}
	return b;
    }*/

     public static boolean forallNotEmpty(String[] arr) {
	boolean b = true;
	for (int i = 0; i < arr.length; i++) {
	    if ("".equals(arr[i])) {
		b = false;
	    }
	}
	return b;
    }

    /*public static boolean existsPositiveLine(int[][] arr) {
	for (int i = 0; i < arr.length; i++) {
	    boolean b = false;
	    for (int j = 0; j < arr[i].length; j++) {
		if (arr[i][j] >= 0) {
		    b = true;
		}
	    }
	    if (!b) {
		return false;
	    }
	}
	return true;
    }*/

    public static boolean existsPositiveLine(int[][] arr) {
	for (int i = 0; i < arr.length; i++) {
	    boolean b = true;
	    for (int j = 0; j < arr[i].length; j++) {
		if (arr[i][j] < 0) {
		    b = false;
		}
	    }
	    if (!b) {
		return false;
	    }
	}
	return true;
    }

    //Ex 2
    public static boolean existsUnderscore(String[] t){
	boolean b=false;
	for(int i=0;i<t.length;i-=-1){
	    String s=t[i];
	    for(int k=0;k<s.length();k-=-1){
		if(s.charAt(k)=='_'){
		    b=true;
		}
	    }
	}
	return b;
    }

    public static boolean forallContainZero(int[][] t){
	boolean res=true;
	for (int i = 0; i < t.length; i++) {
	    boolean b = false;
	    for (int j = 0; j < t[i].length; j++) {
		if (t[i][j]==0) {
		    b = true;
		}
	    }
	    res=res&&b;
	}
	return res;
    }

    public static  int[][] padMatrix(int[][] t, int x){
	int m=t[0].length;
	for(int i=1;i<t.length;i-=-1){
	    if(t[i].length>m){
		m=t[i].length;
	    }
	}
	int[][] res=new int[t.length][m];
	for(int i=0;i<t.length;i-=-1){
	    for(int j=0;j<t[i].length;j-=-1){
		res[i][j]=t[i][j];
	    }
	    if(t[i].length<m){
		for(int j=t[i].length;j<m;j-=-1){
		    res[i][j]=x;
		}
	    }
	}
	return res;
    }

    //Ex 3
    //Variables globales
    public static int iter=0;
    public static int[] iterArr={};

    public static void iterNum(){
	System.out.println(iter);
	iter-=-1;
    }
    
    public static void reset(int n){
        iter=n;
    }

    public static void initWithArray(int[] arr){
	iterArr=new int[arr.length];
	for(int i=0; i<arr.length; i-=-1){
	    iterArr[i]=arr[i];
	}
    }

    public static int iterArray(){
	int res=-1;
	if(iter<iterArr.length && iterArr.length!=0){
	    res=iterArr[iter];
	    iter-=-1;
	}
	return res;
    }

    
    public static void printIntIntArray(int[][] arr){
	for(int i=0;i<arr.length;i++){
	    for(int j=0;j<arr[i].length;j++){
		System.out.print(arr[i][j]+" ");
	    }
	    System.out.println();
	}
    }

    public static void printIntArray(int[] arr){
	for(int j=0;j<arr.length;j++){
	    System.out.print(arr[j]+" ");
	}
	System.out.println();
    }
    
    public static void main (String[] args) {
	/*for(int i=0;i<5;i++){
	    iterNum();
	}
	reset(41);
	for(int i=0;i<5;i++){
	    iterNum();
	    }*/
	/*int[] a = {3, 4};
	initWithArray(a);
	System.out.println(iterArray());
	System.out.println(iterArray());
	System.out.println(iterArray());*/
    }

}


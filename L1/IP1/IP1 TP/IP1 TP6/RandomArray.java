import java.util.Random;
public class RandomArray {
 static Random rand = new Random();

/* ************************************************************************** */
  

    //  QUESTION 1 
    public static int[] createRandomArray(int n){
	int [] R = new int [n];
	for (int i=0; i<n; i++){
	    R[i]=rand.nextInt(n);
	}
	return R;
    }
	
    // QUESTION 2
    public static int[] minMaxAverage(int[] a){
	int [] res = {a[0],a[0],a[0]};
	for (int i=1; i<a.length; i++){
	    if(res[1]<a[i]){
		res[1]=a[i];
	    }
	    if(res[0]>a[i]){
		res[0]=a[i];
	    }
	    res [2]= res[2] + a[i];
	}
	res[2]=res[2]/a.length;
	return res;
    }
	    
    // QUESTION 3
    public static int[] occurrences(int[] a){
	int []res = new int [minMaxAverage(a)[1]+1];
	for (int i=0; i<a.length; i++){
	    for (int j=0; j<res.length; j++){
		if(a[i]==j){
		    res[j]++;
		}
	    }
	}
	return res;
    }
    
    // QUESTION 4a
    public static int[] countingSort(int[] a){
	int [] res=new int [a.length];
	int k=0;
	for(int i=0; i<occurrences(a).length; i++){
	    for (int j=0; j<occurrences(a)[i];j++){
		res[k]=i;
		k++;
	    }
	}
	return res;
    }    
	    
    // QUESTION 4b
    public static void countingSort2(int[] a){
	for(int i=0; i<a.length; i++){
	    for(int j=0; j<a.length; j++){
		if(a[i]<a[j]){
		    int p=a[i];
		    a[i]=a[j];
		    a[j]=p;
		}
	    }
	}
    }
  
    public static void printIntArray (int[] a){
	    for (int i = 0; i<a.length ; i++){
	        System.out.print(a[i] + " ");
	    }
	    System.out.println();
    }

    public static boolean intArrayEquals(int [] a, int [] b){
	boolean tf=true;
	if(a.length != b.length){
	    tf=false;
	}
	else{
	    for (int i=0; i<a.length; i++){
		if(b[i]!=a[i]){
		    tf=false;
		}
	    }
	}
	return tf;
    }

    public static void main(String[] args){
        int[] a = createRandomArray(100);
	int[] b = countingSort(a);
        countingSort2(a);
	printIntArray(a);
	printIntArray(b);
	System.out.println(intArrayEquals(a,b));
	
    }
}

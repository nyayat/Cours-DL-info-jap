public class Fibonacci {

    /* Écrivez vos fonctions ici */
    public static int fibo(int n){
	int i=1;
	int j=1;
	int ans=0;
	for (int k=0;k<n-2;k=k+2){
	    i=i+j;
	    j=i+j;
	}
	if (n%2==0) {
	    ans=j;
	}
	else {
	    ans=i;
	}
	return ans;
    }

    public static void main(String[] args) {

        /* Écrivez vos tests */
	System.out.println(fibo(9)+", "+fibo(10));

    }
}

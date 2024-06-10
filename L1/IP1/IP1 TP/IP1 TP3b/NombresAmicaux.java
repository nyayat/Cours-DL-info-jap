public class NombresAmicaux {

    /* Écrivez vos fonctions ici */
    public static int sumDiv(int n){
	int sum=0;
	for (int i=1;i<=n/2;i++){
	    if (n%i==0){
		sum=sum+i;
	    }
	}
	return sum;
    }

    public static boolean isAmi(int n, int m){
	boolean x=false;
	if (sumDiv(n)==m && sumDiv(m)==n){
	    x=true;
	}
	return x;
    }


	    
    public static void main(String[] args) {

        /* Écrivez vos tests ici */
	// 8.3
	/*int max=500;
	for (int i=1; i<max;i++){
	    for (int j=1; j<max; j++){
		if (isAmi(i,j)==true && i!=j)
		    System.out.println(i+", "+j);
	    }
	}*/

	// 8.4
	boolean found=false;
	int min=10000;
	int max=11000;
	while (found==false){
	    for (int i=min; i<max;i++){
		for (int j=min; j<max; j++){
		    if (isAmi(i,j)==true && i!=j){
			System.out.println(i+", "+j);
			found=true;
		    }
		}
	    }
	    max=max+min;
	}
    }
}

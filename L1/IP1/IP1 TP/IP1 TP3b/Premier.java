public class Premier {

    /* Écrivez vos fonctions ici */
    public static boolean iP(int n){
	boolean b=true;
	for (int i=2; i<=Math.sqrt(n);i++){
	    if (n%i==0){
		b=false;
	    }
	}
	if (n==0 || n==1){
	    b=false;
	}
	return b;
    }
	

    public static void main(String[] args) {

        /* Écrivez vos tests ici */
	System.out.println(iP(1));

    }
}

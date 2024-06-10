public class Conditions {

    /* Écrivez vos fonctions ici */
    public static int absolute (int x){
	if (x<0){
	    x=-x;
	}
	return(x);
    }


    public static String solve(int x, int y, int z){
	if (x+y==z){return("x+y=z");}
	else if (x-y==z){return("x-y=z");}
	else if (-x-y==z){return("-x-y=z");}
	else if (-x+y==z){return("-x+y=z");}
	else {return("Rien du tout!");}
    }
	
    public static void main(String[] args) {

        /* Écrivez vos tests et le code à exécuter ici */
	// Ex 4.2
	// System.out.println(absolute(73));
	// System.out.println(absolute(-37));

	// Ex 4.3
	System.out.println(solve(4,5,9));
	System.out.println(solve(4,5,-1));
	System.out.println(solve(4,5,-9));
	System.out.println(solve(4,5,1));
	System.out.println(solve(4,5,42));

    }
}

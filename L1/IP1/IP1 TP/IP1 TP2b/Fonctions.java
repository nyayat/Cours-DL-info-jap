public class Fonctions {

    public static int div10(int x) {
	return(10/x);
    }

    public static int sumproduct(int x, int y, int z) {
	return(x*y+x*z+y*z);
    }

    public static void main(String[] args) {

	System.out.println(sumproduct(2,2+1,div10(2)));
    }
}

public class Moyenne {

    /* Écrivez vos fonctions ici */
    public static void moyenne(int x, int y, int z, int p, int q){
	int sum=x+y+z+q+p;
	System.out.println(sum);
	System.out.println(sum/5);
    }

    public static void main(String[] args) {

        /* Écrivez vos tests et le code à exécuter ici */
	moyenne(10,12,8,9,19);

    }
}

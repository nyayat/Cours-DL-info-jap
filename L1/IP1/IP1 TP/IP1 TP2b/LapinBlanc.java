public class LapinBlanc {

    /* Écrivez votre fonction ici */
    public static void secondes(int s){
	int h=s/3600;
	s=s-h*3600;
	int m=s/60;
	s=s-m*60;
	System.out.println(h+"h"+m+"min"+s+"s");
    }

    public static void main(String[] args) {

        /* Écrivez vos tests */
	secondes(3725);
    }
}

public class Voyelles {

    /* Écrivez vos fonctions ici */
    public static int voy(String s){
	int res=0;
	for (int i=0;i<s.length();i++){
	    String m=String.valueOf(s.charAt(i));
	    if (m.equals("a")||m.equals("i")||m.equals("u")||m.equals("e")||m.equals("o")||m.equals("y")){
		res++;
	    }
	}
	return res;
    }

    public static void main(String[] args) {

        /* Écrivez vos tests ici */
	System.out.println(voy("la particule no est gentille"));
    }
}

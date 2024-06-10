public class Anagrammes {

    /* Écrivez vos fonctions ici */
    public static String suppression(char c, String s){
	boolean x=false;
	String res="";
	for (int i=0;i<s.length();i++){
	    if (x==true || c!=s.charAt(i)){
		res=res+s.charAt(i);
	    }
	    else {
		x=true;
	    }
	}
	return res;
    }

    public static boolean scrabble (String mot, String lettres){
	boolean b= false;
	int lengthatt=lettres.length()-mot.length();
	if (lengthatt>-1){
	    for (int i=0; i<mot.length(); i++){
		lettres=suppression(mot.charAt(i), lettres);
	    }
	    if (lettres.length()==lengthatt){
		b=true;
	    }
	}
	return b;
    }

    public static boolean anagramme (String u, String v){
	boolean b=false;
	if (u.length()==v.length()){
	    b=scrabble(u,v);
	}
	return b;
    }

    public static void main(String[] args) {

        /* Écrivez vos tests ici */
	System.out.println(anagramme("parisien", "aspirine"));
	System.out.println(anagramme("chaise", "disque"));
    }
}

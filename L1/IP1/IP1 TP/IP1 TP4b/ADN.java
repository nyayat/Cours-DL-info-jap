public class ADN {

    /* Écrivez vos fonctions ici */
    public static boolean estADN(String s){
	boolean b=true;
	for (int i=0;i<s.length();i++){
	    if (s.charAt(i)!='A' && s.charAt(i)!='C' && s.charAt(i)!='G' && s.charAt(i)!='T'){
		b=false;
	    }
	}
	return b;
    }

    public static int masseMolaire(String s){
	int a=0;
	int c=0;
	int g=0;
	int t=0;
	if (estADN(s)){
	    for (int i=0; i<s.length(); i++){
		if (s.charAt(i)=='A'){
		    a++;
		}
		else if (s.charAt(i)=='C'){
		    c++;
		}
		else if (s.charAt(i)=='G'){
		    g++;
		}
		else{
		   t++;
		}
	    }
	}
	return (a*135+c*111+g*151+t*126);
    }


    public static String brinComp(String b){
	String bc="";
	if (estADN(b)){
	    for (int i=0;i<b.length();i++){
		if (b.charAt(i)=='A'){
		    bc=bc+'T';
		}
		else if (b.charAt(i)=='T'){
		    bc=bc+'A';
		}
		else if (b.charAt(i)=='C'){
		    bc=bc+'G';
		}
		else{
		    bc=bc+'C';
		}
	    }
	}
	return bc;
    }

    public static boolean sous_sequence(String s, String t){
	boolean b= false;
	for(int i=0; i<t.length()-s.length()+1;i++){
	    if(s.equals(t.substring(i, i+s.length()))){
		b=true;
	    }
	}
	return b;
    }
    
    public static void main(String[] args) {

        /* Écrivez vos fonctions ici */
	System.out.println(sous_sequence("ATC", "GGTATCG"));
	System.out.println(sous_sequence("CG", "AAT"));
    }
}

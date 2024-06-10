class Section1 {

    /* ********************************************************** */
    /* EXERCICE 1 : ENTIERS NON SIGNES                            */
    /* ********************************************************** */


    // QUESTION 1 
    // Déclarez la fonction isBinaryEncoding ci-dessous
    

    // QUESTION 2  
    // Déclarez la fonction powerTwo ci-dessous


    // QUESTION 3
    // Déclarez la fonction decode ci-dessous    


    // QUESTION 4
    // Déclarez la *procédure* encodeAndPrint ci-dessous    


    // QUESTION 5
    // Déclarez la fonction encode ci-dessous    


    // QUESTION 6
    // Ecrivez le test dans la fonction main




    /* ********************************************************** */
    /* EXERCICE 2 : INVERSE                              */
    /* ********************************************************** */


    // QUESTION 1 
    // Déclarez la fonction inverse ci-dessous
    public static String oneComplement(String s){
	String res="";
	for (int i=0;i<s.length();i++){
	    if (s.charAt(i)=='1'){
		res=res+'0';
	    }
	    else if (s.charAt(i)=='0'){
		res=res+'1';
	    }
	    else{
		res=res+s.charAt(i);
	    }
	}
	return res;
    }

    public static String twoComplement(String s){
	int i=s.length()-1;
	String res="";
	while (s.charAt(i)!='1' && i>0){
	    res=s.charAt(i)+res;
	    i--;
	}
	if (i>0){
	    res=oneComplement(s.substring(0,i))+'1'+res;
	}
	else if (s.charAt(0)=='0'){
	    res='0'+res;
	}
	else{
	    res='1'+res;
	}
	return res;
    }
      
    /* ********************************************************** */
    /* FONCTION PRINCIPALE                                        */
    /* ********************************************************** */
    

    public static void main (String []args) {
    // Ecrivez vos tests dans le corps de cette fonction
	System.out.println(twoComplement("01000110"));
    }
    


    /* ********************************************************** */
    /* FONCTIONS AUXILIAIRES                                      */
    /* ********************************************************** */
     

    // caractère à une position donnée
    public static String charAtPos(String s, int i) {
	return String.valueOf(s.charAt(i));
    }

    // test d'égalité entre chaînes de caractères
    public static boolean stringEquals(String s, String t) {
	return s.equals(t);
    }
    

}

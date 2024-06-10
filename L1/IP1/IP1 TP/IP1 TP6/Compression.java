import java.io.*;
public class Compression{
  /* ************************************************************************** */
  
  //  Construction du lexique

    //  QUESTION 1 
    public static boolean isIn (String s, String[] lex){
	boolean b=false;
	for(int i=0;i<lex.length; i++){
	    if(s.equals(lex[i])){
		b=true;
	    }
	}
	return b;
    }

    // QUESTION 2
    public static String[] extendLexicon (String s, String[] lex){
	String[]res=new String[lex.length+1];
	for(int i=0; i<lex.length; i++){
	    res[i]=lex[i];
	}
	res[lex.length]=s;
	return res;
    }
	
    // QUESTION 3
    public static String[] buildLexicon(String[] t){
	String []lex=new String [0];
	for (int k=0; k<t.length; k++){
	    if(isIn(t[k], lex)==false){
		lex=extendLexicon(t[k], lex);
	    }
	}
	return lex;
	    
    }
	
 //  Codage et decodage
  
  //  QUESTION 1 
    public static int getCode(String s, String[] lex){
	int res=-1;
	boolean b=false;
	for (int i=0; i<lex.length; i++){
	    if(s.equals(lex[i]) && b==false){
		res=i;
		b=true;
	    }
	}
	return res;
    }
	
  // QUESTION 2
    public static int[] code (String[] tab, String[] lex){
	int [] res=new int [tab.length];
	for (int i=0; i<tab.length; i++){
	    res[i]=getCode(tab[i],lex);
	}
	return res;
    }

  // QUESTION 3
    public static String[] decode (int[] code, String[] lex){
	String [] res=new String[code.length];
	for (int i=0; i<code.length; i++){
	    res[i]=lex[code[i]];
	}
	return res;	    
    }

    public static void main(String[] args){
	loadText ();
	//System.out.println(text.length+" "+(buildLexicon(text)).length);
	//printStringArray (text);
	String [] lex= buildLexicon(text);
	System.out.println(stringArrayEquals(decode(code(text,lex),lex),text));
    }
	 
      /******************************************/
      /*     Fonctions auxiliaires              */
      /******************************************/
    public static void printStringArray (String[] a){
    	for (int i = 0; i<a.length ; i++){
    	    System.out.print(a[i]+" " );
    	}
    	System.out.println();
    }

    public static boolean  stringArrayEquals (String[] a, String[] b){
    	if (a.length!=b.length) {
    	    return false;
		}
    	for (int i=0; i<a.length; i++){
    	    if (!a[i].equals(b[i])){
    		return false;
    	    }
    	}
    	return true;
    }

    public static void printIntArray (int[] a){
    	for (int i = 0; i<a.length ; i++){
	        System.out.print(a[i] + " ");
    	}
	    System.out.println();
    }


    static String textName = "text.txt";
    static int textSize = 1002;
    static String[] text = new String[textSize];
    //static boolean textLoaded = false;

    public static void loadError () {
	System.out.println("Erreur de chargement du texte");
	System.exit(1);
    }
  
    public static void loadText () {
	try {
	    BufferedReader br = new BufferedReader(new FileReader(textName));
	    for(int j=0;j<textSize; j++){
		String t ="";
		for (int i = br.read(); (char)(i)!=' '&& (char)(i)!='\n'; i=br.read()) {
		    t+=(char)(i);
		}
		text[j]=t;
		
		if (text[j] == null) { return; }
	    }
	}
	//textLoaded = true
	catch (Exception e) { loadError (); }
    }
}

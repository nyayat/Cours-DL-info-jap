public class Conjugaison {

    /* Écrivez vos fonctions ici */
    public static String conj(String v){
	String res="";
	boolean err=false;
	for (int i=0;i<v.length();i++){
	    char c = v.charAt(i);
	    if(c != 'a' && c!='z' && c!='e' && c!='r' && c!='t' && c!='y' && c!='u' && c!='i' && c!='o' && c!='p' && c!='q' && c!='s' && c!='d' && c!='f' && c!='g' && c!='h' && c!='j' && c!='k' && c!='l' && c!='m' && c!='w' && c!='x' && c!='c' && c!='v' && c!='b' && c!='n'){
		res="QUE DES MINUSCULES";
		err=true;
	    }
	}
	if(err==false &&  (v.charAt(v.length()-1)!='r' || v.charAt(v.length()-2)!='e')){
	    res="PREMIER GROUPE";
	    err=true;
	}
	else if (err==false && v.equals("aller")){
	    res="EXCEPTION";
	    err=true;
	}
	else if (err==false){
	    v=v.substring(0,v.length()-1);
	    res="je "+v+"\ntu "+v+"s\nil "+v+"\n";
	    if (String.valueOf(v.charAt(v.length()-2)).equals("g")){
		res=res+"nous "+v+"ons\nvous "+v+"z\nils "+v+"nt";
	    }
	    else {
		v=v.substring(0,v.length()-1);
		res=res+"nous "+v+"ons\nvous "+v+"ez\nils "+v+"ent";
	    }
	}
	return res;
    }

    public static void main(String[] args) {

        /* Écrivez vos tests ici */
	System.out.println(conj("aller"));
	System.out.println();
	System.out.println(conj("DANSer"));
	System.out.println();
	System.out.println(conj("dormir"));
	System.out.println();
	System.out.println(conj("manger"));
	System.out.println();
	System.out.println(conj("danser"));
    }
}

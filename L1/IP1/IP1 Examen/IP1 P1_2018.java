public class P2018{
    public static String foo(String s){
	String a="";
	for(int i=0; i<s.length(); i++){
	    a=a+"A";
	}
	return a;
    }

    public static void rectanglePlein(int li, int col){
	for(int i=0; i<li; i++){
	    for(int j=0; j<col; j++){
		System.out.print("#");
	    }
	    System.out.println();
	}
    }

    public static void rectangleAB(int li, int col){
	for(int i=0; i<li; i++){
	    if(i==0 || i==li-1){
		for(int j=0; j<col; j++){
		    System.out.print("#");
		}
		System.out.println();
	    }
	    else{
		System.out.print("#");
		for(int j=1; j<col-1; j++){
		    System.out.print("$");
		}
		System.out.println("#");
	    }
	}
    }

    public static int nbreTomates(int f, int g){
	int s=g*(f+3);
	return s;
    }

    public static int nbreGraines (int f, int g){
	int s=nbreTomates(f,g)*5*f;
	return s;
    }

    public static int nbreTomAnnee(int f, int g, int n){
	int tom=0;
	for(int i=0; i<n; i++){
	    tom=nbreTomates(f,g);
	    g=nbreGraines(f,g);
	}
	return tom;
    }

    public static void choix(){
	int pr=nbreTomAnnee(2,4,10);
	int de=nbreTomAnnee(4,2,10);
	if(pr>de){
	    System.out.println("Choix 1 meilleur");
	}
	else{
	    System.out.println("Choix 2 meilleur");
	}
    }

    public static int occurrence(String s, char c){
	int res=0;
	for(int i=0; i<s.length(); i++){
	    if(s.charAt(i)==c){
		res++;
	    }
	}
	return res;
    }

    public static boolean present (String s1, String s2){
	boolean b=false;
	int verif=0;
	for(int i=0; i<s2.length(); i++){
	    boolean tmp=false;
	    for(int j=0; j<s1.length(); j++){
		if(s1.charAt(j)==s2.charAt(i)){
		    tmp=true;
		}
	    }
	    if(tmp==true){
		verif++;
	    }
	}
	if(verif==s2.length()){
	    b=true;
	}
	return b;
    }

    public static String battage (String s1, String s2){
	int min=s1.length();
	if(s2.length()<min){
	    min=s2.length();
	}
	String res="";
	for(int i=0; i<min; i++){
	    res=res+s1.charAt(i);
	    res=res+s2.charAt(i);
	}
	if(min==s2.length()){
	    for(int i=min; i<s1.length(); i++){
		res=res+s1.charAt(i);
	    }
	}
	else{
	    for(int i=min; i<s2.length(); i++){
		res=res+s2.charAt(i);
	    }
	}
	return res;
    }
	
    public static int[] ajouteElem (int[]tab, int x){
	boolean b=false;
	int[]res=new int [tab.length+1];
	for(int i=0; i<tab.length; i++){
	    if(tab[i]==x){
		b=true;
	    }
	}
	if(b==true){
	    return tab;
	}
	else{
	    for(int i=0; i<tab.length;i++){
		res[i]=tab[i];
	    }
	    res[tab.length]=x;
	}
	return res;
    }

    public static boolean estEnsemble(int[]tab){
	boolean b=true;
	for(int i=0; i<tab.length; i++){
	    for(int j=i+1; j<tab.length; j++){
		if(tab[i]==tab[j]){
		    b=false;
		}
	    }
	}
	return b;
    }
    public static int cardinal (int []tab){
	int res=-1;
	if(estEnsemble(tab)==true){
	    res=tab.length;
	}
	return res;
    }

    public static int[]intersection(int[]t1, int[]t2){
	int[]vide=new int[0];
	if(estEnsemble(t1)==true && estEnsemble(t2)==true){
	    int c=0;
	    int[]tmp=new int[t1.length+t2.length];
	    for(int i=0; i<t1.length; i++){
		for(int j=0; j<t2.length; j++){
		    if(t1[i]==t2[j]){
			tmp[c]=t1[i];
			c++;
		    }
		}
	    }
	    int[]res=new int[c];
	    for(int i=0; i<c; i++){
		res[i]=tmp[i];
	    }
	    return res;
	}
	return vide;
    }

    public static boolean egal(int []t1, int[]t2){
	boolean b=false;
	int verif=0;
	if(t1.length==t2.length){
	    for(int i=0; i<t1.length; i++){
		boolean tmp=false;
		for(int j=0; j<t2.length; j++){
		    if(t1[i]==t2[j]){
			tmp=true;
		    }
		}
		if(tmp==true){
		    verif++;
		}
	    }
	    if(verif==t1.length){
		b=true;
	    }
	}
	return b;
    }
		    
    public static void print(int[]tab){
	for(int i=0; i<tab.length; i++){
	    System.out.print(tab[i]+" ");
	}
	System.out.println();
    }
	    
    public static void main(String[]t){
	/*System.out.println(foo("100"));
	rectanglePlein(4,3);
	rectangleAB(4,4);*/

	/*System.out.println(nbreTomates(2,3));
	System.out.println(nbreGraines(2,3));
	System.out.println(nbreTomAnnee(2,3,2));
	System.out.println(nbreTomAnnee(2,3,3));
	choix();*/

	/*System.out.println(occurrence("KAYAK", 'A'));
	System.out.println(present("bob l'eponge", "go ne"));
	System.out.println(battage("licence", "informatique"));*/

	int[]T1={2,4,5,1};
	int[]T2={2,4,5,7};
	int[]T3={5,4,5,7};
	int[]T4={};
	int[]T5={3,1,2};
	int[]T6={1,3,4};
	int[]T7={3,1,4};
	int[]T8={8,1,5};
	print(ajouteElem(T1,1));
	print(ajouteElem(T2,1));
	System.out.println(estEnsemble(T1));
	System.out.println(estEnsemble(T3));
	System.out.println(estEnsemble(T4));
	System.out.println(cardinal(T1));
	print(intersection(T1, T5));
	System.out.println(egal(T6,T7));
	System.out.println(egal(T6,T8));
	
    }
}

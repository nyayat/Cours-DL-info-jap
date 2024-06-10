public class TP7b{

    //Exercice 1
    
    public static void letters2word(char[]t){
	for(int i=0; i<t.length; i++){
	    System.out.print(t[i]);
	}
	System.out.println();
    }

    public static void stutterword (char[]tab1, int[]tab2){
	if(tab1.length!=tab2.length){
	    System.out.println("Erreur");
	}
	else{
	    for(int i=0; i<tab1.length; i++){
		for(int j=0; j<tab2[i]; j++){
		    System.out.print(tab1[i]);
		}
	    }
	}
	System.out.println();
    }

    public static char[] word2letters (String s){
	char[]res=new char [s.length()];
	for(int i=0; i<s.length(); i++){
	    res[i]=s.charAt(i);
	}
	return res;
    }

    public static char[] letters(String word){
        char[]tmp=new char [word.length()];
	int c=0;
	for(int i=0; i<word.length(); i++){
	    boolean b=false;
	    for(int j=i+1; j<word.length(); j++){
		if(word.charAt(i)==word.charAt(j)){
		    b=true;
		}
	    }
	    if(b==false){
		tmp[c]=word.charAt(i);
		c++;
	    }
	}
	char[]res=new char[c];
	for(int i=0; i<c; i++){
	    res[i]=tmp [i];
	}
	return res;
    }

    //Exercice 2

    public static boolean search(int[]tab, int x){
	boolean b=false;
	for(int i=0; i<tab.length; i++){
	    if(tab[i]==x){
		b=true;
	    }
	}
	return b;
    }

    public static int[] union(int[]tab1, int[]tab2){
	int[]tmp=new int[tab1.length+tab2.length];
	int c=0;
	for(int i=0; i<tab1.length; i++){
	    tmp[c]=tab1[i];
	    c++;
	}
	for(int i=0; i<tab2.length; i++){
	    if(search(tmp,tab2[i])==false){
		tmp[c]=tab2[i];
		c++;
	    }
	}
	int[]res=new int[c];
	for(int i=0; i<c; i++){
	    res[i]=tmp[i];
	}
	return res;
    }

    public static int[] difference(int[]tab1, int[]tab2){
	int [] tmp=new int[tab1.length+tab2.length];
	int c=0;
	for(int i=0; i<tab1.length; i++){
	    if(search(tab2,tab1[i])==false){
		tmp[c]=tab1[i];
		c++;
	    }
	    if(search(tab1,tab2[i])==false){
		tmp[c]=tab2[i];
		c++;
	    }
	}
	int[]res=new int[c];
	for(int i=0; i<c;i++){
	    res[i]=tmp[i];
	}
	return res;
    }

    //Exercice 3

    public static int position(int[]tab, int x){
	int res=0;
	if(x>tab[0]){
	    res=tab.length;	    
	    for(int i=1; i<tab.length; i++){
		if(x>tab[i-1] && x<tab[i]){
		    res=i;
		}
	    }
	}
	return res;
    }

    public static int[] insert(int[]tab,int pos, int x){
	int[]res=new int[tab.length+1];
	for(int i=0; i<tab.length+1; i++){
	    if(i<pos){
		res[i]=tab[i];
	    }
	    else if(i>pos){
		res[i]=tab[i-1];
	    }
	    else{
		res[i]=x;
	    }
	}
	return res;
    }

    public static int[]sort(int[]tab){
	int[]res={tab[0]};
	for(int i=1; i<tab.length; i++){
	    res=insert(res, position(res,tab[i]), tab[i]);
	}
	return res;
    }

    //Exercice 4

     public static int [] shiftN(int[]tab, int n){
	int [] res=new int[tab.length];
	while(n<0){
	    n=n+tab.length;
	}
	n=n%tab.length;
	for(int i=0; i<tab.length; i++){
	    res[(i+n)%tab.length]=tab[i];
	}
	return res;
    }

    public static boolean equalArray(int[]tab1, int[]tab2){
	boolean b=true;
	for(int i=0; i<tab1.length; i++){
	    if(tab1[i]!=tab2[i]){
		b=false;
	    }
	}
	return b;
    }
    
    public static boolean circulaire(int[]tab1, int[]tab2){
	boolean b=false;
	if(tab1.length==tab2.length){
	    for(int i=1; i<tab1.length; i++){
		if(equalArray(tab1,shiftN(tab2,i))==true){
			b=true;
		}
	    }
	}
	return b;
    }
		
	
    public static void main(String[]t){
	//char[] tab={'p','l','a','c','a','r','d'};
	//letters2word(tab);
	//char[] tab1={'a','b','c','d'};
	//int[] tab2={2,2,3,4};
	//stutterword(tab1,tab2);
	//letters2word(word2letters("placard"));
	//print(letters("electroacoustique"));

	//int[] tab={6,20,12,1000,8};
	//System.out.println(search(tab, 12));
	//System.out.println(search(tab, 50));
	//int [] tab1={6,20,12,1000,8};
	//int [] tab2={2,8,6,7,12};
	//print(difference(tab1, tab2));

	//int[] tab={0,2,4,6,7,8};
	//System.out.println(position(tab,1));
	//System.out.println(position(tab,-5));
	//System.out.println(position(tab,10));
	//int[] tab={2,5,4,3};
	//print(insert(tab, 0, 1));
	//print(insert(tab, 2, 100));
	//int[] tab = {40,1,20,3,8,6};
	//print(sort(tab));

	int[] tab1={1,2,3,4,5};
	int[] tab2={3,4,5,1,2};
	int[] tab3={3,5,4,1,2};
	System.out.println(circulaire(tab1,tab2));
	System.out.println(circulaire(tab1, tab3));
    }

    public static void print(int[]t){
	for(int i=0; i<t.length; i++){
	    System.out.print(t[i]+" ");
	}
	System.out.println();
    }
    
}

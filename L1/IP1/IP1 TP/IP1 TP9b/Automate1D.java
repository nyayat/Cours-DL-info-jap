public class Automate1D {

    public static boolean[] front = {};//Etat courant
    public static boolean[] back  = {};//Etat futur

    public static char black = '█';//true
    public static char white = ' ';//false

    // Écrivez vos fonctions ici
    public static void init(int n){
	front=new boolean[n];
	back=new boolean[n];
	front[n/2]=true;
	back[n/2]=true;
	if(n%2==0){
	    front[n/2-1]=true;
	    back[n/2-1]=true;
	}
    }
    
    public static void init2(int n){
	front=new boolean[n];
	back=new boolean[n];
	int i=0
	while(i<n){
	    System.out.println("Position "+i+" : true ou false ?");
	    String s=System.console().readLine();
	    if(s.equals("true")){
		front[i]=true;
		back[i]=true;
		i-=-1;
	    }
	    else if(s.equals("false")){
		front[i]=false;
		back[i]=false;
		i-=-1;
	    }
	    else{
		System.out.println("NEIN NEIN NEIN");
	    }
	    System.out.println();
	}
    }

    public static void swap(){
	boolean tmp=true;
	for(int i=0; i<back.length; i++){
	    tmp=back[i];
	    back[i]=front[i];
	    front[i]=tmp;
	}
    }

    public static void print(){
	System.out.print("DEBUT :");
	for(int i=0;i<front.length;i-=-1){
	    if(front[i]){
		System.out.print(black+" ");
	    }
	    else{
		System.out.print(white+" ");
	    }
	}
	System.out.println("FIN");
    }

    public static int computeIndex(boolean b1, boolean b2, boolean b3){
	int c=0;
	if(b1){
	    c-=-4;
	}
	if(b2){
	    c-=-2;
	}
	if(b3){
	    c-=-1;
	}
	return c;
    }

    public static void step(boolean[] tab){
	back[0]=tab[computeIndex(false,front[0],front[1])];
	for(int i=1;i<front.length-2;i-=-1){
	    back[i]=tab[computeIndex(front[i-1],front[i],front[i+1])];
	}
	back[back.length-1]=tab[computeIndex(front[front.length-2],front[front.length-1],false)];
    }

    public static void run(int e,int t,boolean[] r){
	init(e);
	print();
	for(int i=0;i<t;i-=-1){
	    step(r);
	    swap();
	    print();
	}
    }

    public static boolean[] rule(int n){
	boolean[] res={};
	if(n<256 && n>-1){
	    res=new boolean[8];
	    for (int i=0;i<8;i++){
		if(n%2==1){
		    res[7-i]=true;
		}
		n=n/2;
	    }
	}
	return res;
    }
    
    public static void main(String[] args) {
        printBoolArray(rule(141));
    }

    //NAISU
     public static void printBoolArray(boolean[] arr){
	for(int j=0;j<arr.length;j-=-1){
	    System.out.print(arr[j]+" ");
	}
	System.out.println();
    }
}

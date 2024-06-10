public class TP6b{
    
    //Exercice 1 : maximum
    
    public static int max(int [] tab){
	int ind=0;
	int max=tab [0];
	for (int i =1; i<tab.length; i++){
	    if(max<tab[i]){
		max=tab[i];
		ind=i;
	    }
	}
	return ind;
    }

    //Exercice 2 : signes

    public static int [] sign (int [] t1, int [] t2){
	int [] R = new int [0];
	if(t1.length==t2.length){
	    int [] S = new int [t1.length];
	    for(int i=0; i<t1.length; i++){
		if(t1[i]*t2[i]<0){
		    S[i]=-1;
		}
		else{
		    S[i]=1;
		}
	    }
	    return S;
	}
	return R;
    }
   
    //Exercice 3 : variance

    public static int abs(int a){
	if(a<0){
	    a=-a;
	}
	return a;
    }

    public static int moyenne (int [] tab){
	int moy=tab[0];
	for (int i=1; i<tab.length; i++){
	    moy=moy+tab[i];
	}
	moy=moy/tab.length;
	return moy;
    }

    public static int variance (int[]tab){
	int var=0;
	for(int i=0; i<tab.length; i++){
	    var=var+abs(tab[i]-moyenne(tab));
	}
	var=var/tab.length;
	return var;
    }

    //Exercice 4 : décalage

    public static int[] shift(int[]tab){
	int [] res=new int[tab.length];
	for (int i=0; i<tab.length-1; i++){
	    res[i+1]=tab[i];
	}
	res[0]=tab[tab.length-1];
	return res;
    }

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

    //Exercice 5 : parité

    public static int [] sort(int[]tab){
	for (int i=0; i<tab.length; i++){
	    for (int j=0; j<tab.length; j++){
		if(tab[i]<tab[j]){
		    int p=tab[i];
		    tab[i]=tab[j];
		    tab[j]=p;
		}
	    }
	}
	return tab;
    }

    public static int compteurP(int[]tab){
	int p=0;
	for(int i=0; i<tab.length; i++){
	    if(tab[i]%2==0){
		p++;
	    }
	}
	return p;
    }

    public static int[] unif(int[]P, int[]Imp){
	int [] res =new int[P.length+Imp.length];
	int p=P.length;
	for(int i=0; i<P.length; i++){
	    res[i]=P[i];
	}
	for(int i=0; i<Imp.length; i++){
	    res[p+i]=Imp[i];
	}
	return res;
    }

    public static int [] paritysort(int[]tab){
	int p=compteurP(tab);
	int []P=new int[p];
	int []Imp=new int[tab.length-p];
	int j=0;  //compteur pour pair
	int k=0;  //compteur pour impair
	for(int i=0; i<tab.length; i++){
	    if(tab[i]%2==0){
		P[j]=tab[i];
		j++;
	    }
	    else{
		Imp[k]=tab[i];
		k++;
	    }
	}
	P=sort(P);
	Imp=sort(Imp);
	int [] res = unif(P,Imp);
	return res;
    }

    //Exercice 6 : Fibonacci

    public static int[] fibonacci(int n){
	int [] res=new int[n];
	res[0]=0;
	res[1]=1;
	for (int k=2; k<n; k++){
	    res[k]=res[k-1]+res[k-2];
	}
	return res;
    }
	    
    //Exercice 7 : crible d'Eratosthène

    public static int[] crible (int n){
	int[]res=new int[n-1];
	for(int i=2; i<=n; i++){
	    res[i-2]=i;
	}
	for(int i=0;i<n-1; i++){
	    if(res[i]!=0){
		for(int k=2; k*(i+2)<=n;k++){
		    res[(i+2)*k-2]=0;
		}
	    }
	}
	return res;
    }

    public static int[] crible2 (int n){
	int[]res=new int[n+1];
	for(int i=2; i<=n; i++){
	    res[i]=i;
	}
	for(int i=2;i<=n; i++){
	    if(res[i]!=0){
		for(int k=2; k*i<=n;k++){
		    res[i*k]=0;
		}
	    }
	}
	return res;
    }

    public static int[]prime(int n){
	int[]Cr=crible(n);
	int compt=0;
	for(int i=0; i<Cr.length; i++){
	    if(Cr[i]!=0){
		compt++;
	    }
	}
	int [] res=new int[compt];
	int j=0;
	for(int i=0; i<Cr.length; i++){
	    if(Cr[i]!=0){
		res[j]=Cr[i];
		j++;
	    }
	}
	return res;
    }

    //
    
    public static void main(String[] args){
	int [] t1 = {1000,1,-1,1,1};
	int [] t2 = {-1,1,-1,-1000};
	int [] t = {1000,1,2,3};
	int[] tab = {1,2,3,4,5,6,8,10};
	//System.out.println(max(tab));
	//printIntArray(sign(t1,t2));
	//System.out.println(variance(t));
	//printIntArray(shift(t));
	//printIntArray(shiftN(t,-3));
	//printIntArray(paritysort(tab));
	printIntArray(fibonacci(15));
	//printIntArray(crible2(100));
	//printIntArray(prime(100));
    }

    public static void printIntArray(int [] tab){
	for (int i=0; i<tab.length; i++){
	    System.out.print(tab[i] + ".");
	}
	System.out.println();
    }
    
}

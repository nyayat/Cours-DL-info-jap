public class P2_2017{

    //1. t1={1,2,3,4,5} et t2={2,1,4,3,5}
    //2. a=f(1)=g(3)=b... boucle infini :)

    //////////////////////////////////////////////////////////

    //1.
    public static int[] suiteU(int n){
	int[]res=new int[n];
	for(int i=0; i<n; i++){
	    if(i==0){
		res[i]=1;
	    }
	    else if(i==1){
		res[i]=2;
	    }
	    else if(i==2){
		res[i]=3;
	    }
	    else{
		res[i]=res[i-3]+res[i-1];
	    }
	}
	return res;
    }

    //2.
    public static int[] serieU(int n){
	int[]res=new int[n];
	for(int i=0; i<n ;i++){
	    if(i==0){
		res[i]=1;
	    }
	    else{
		res[i]=res[i-1]+suiteU(n)[i];
	    }
	}
	return res;
    }

    //3.
    public static boolean presentSerieU(int n, int x){
	int[]tmp=serieU(n);
	int i=0;
	while(n<=tmp[i]){
	    if(tmp[i]==x){
		return true;
	    }
	    i++;
	}
	return false;
    }

    //////////////////////////////////////////////////////////

    //1.
    public static void echange(int[]tab, int p){
	int tmp=tab[p];
	tab[p]=tab[p+1];
	tab[p+1]=tmp;
    }

    //2.
    public static void balayage(int[]tab){  //rotation vers la gauche, erreur dans l'ex du partiel
	for(int i=0; i<tab.length-1; i++){
	    echange(tab, i);
	}
    }

    //3.
    public static void algo(int[]tab){
	for(int i=0; i<tab.length; i++){
	    balayage(tab);
	}
    }

    //4. on a toujours t={4,1,2,3}, algo ne fait rien sur les tableaux... :)

    //////////////////////////////////////////////////////////

    //1.
    public static boolean tousDifferents(int[]t){
	for(int i=1; i<t.length; i++){
	    for(int j=0; j<t.length; j++){
		if(i!=j && t[i]==t[j]){
		    return false;
		}
	    }
	}
	return true;
    }
	    
    //2.
    public static int sommeN(int n){
	int res=0;
	for(int i=1; i<n; i++){  //on ne prend pas en compte n dans la somme (cf question 4)
	    res=res+i;
	}
	return res;
    }

    //3.
    public static int[] sousTab(int[]tab, int p){
	int[]res=new int[tab.length-p-1];
	int c=0;
	for(int i=p+1; i<tab.length; i++){
	    res[c]=tab[i];
	    c++;
	}
	return res;
    }

    //4.
    public static int[][] toutesLesPaires(int[]tab){
	int c=0;
	if(tousDifferents(tab)){
	    c=sommeN(tab.length);
	}
	int[][]res=new int[c][2];
	c=0;
	for(int i=0; i<tab.length; i++){
	    for(int j=i+1; j<tab.length; j++){
		res[c][0]=tab[i];
		res[c][1]=tab[j];
		c++;
	    }
	}
	return res;
    }

    //5.
    public static int[] sommePaire(int[]tab, int x){
	int c=0;
	int[][]paire=toutesLesPaires(tab);
	int[]tmp=new int[paire.length];
	for(int i=0; i<tmp.length; i++){
	    tmp[i]=paire[i][0]+paire[i][1];
	    if (tmp[i]==x){
		c++;
	    }
	}
	if(tousDifferents(tab) && c>0){
	    int[]res=new int[2];
	    boolean flag=false;
	    while(!flag){
		if(tmp[c]==x){
		    res[0]=paire[c][0];
		    res[1]=paire[c][1];
		    flag=!flag;
		}
		else{
		    c++;
		}
	    }
	    return res;
	}
	int[]res=new int[0];
	return res;
    }

    //////////////////////////////////////////////////////////

    //1.
    public static int[][] creerGrille(int n, int m){
	int[][]res=new int[n][m];  //cases noires=0
	return res;
    }

    //2.
    public static void afficheGrille(int[][]gr){
	for(int i=0; i<gr.length; i++){
	    for(int j=0; j<gr[i].length; j++){
		System.out.print(gr[i][j]+" ");
	    }
	    System.out.println();
	}
    }

    //3.
    public static int nouvelleOrientation(int[][]gr, int[]pos, int o){
	if(gr[pos[0]][pos[1]]==0){
	    o--;
	    if(o==-1){
		o=3;
	    }
	}
	else{
	    o++;
	    if(o==4){
		o=0;
	    }
	}
	return o;
    }

    //4.
    public static int[] avanceEtChange(int[][]gr, int[]pos, int o){
	int[]res=new int[2];
	int maxl=gr.length;
	int maxc=gr[0].length;
	if(o==0){
	    res[0]=(pos[0]-1+maxl)%maxl;  //pour les dépassements
	    res[1]=pos[1];
	}
	else if(o==1){
	    res[0]=pos[0];
	    res[1]=(pos[1]+1+maxc)%maxc;
	}
	else if(o==2){
	    res[0]=(pos[0]+1+maxl)%maxl;
	    res[1]=pos[1];
	}
	else{
	    res[0]=pos[0];
	    res[1]=(pos[1]-1+maxc)%maxc;
	}
    	if(gr[pos[0]][pos[1]]==0){
	    gr[pos[0]][pos[1]]=1;
	}
	else{
	    gr[pos[0]][pos[1]]=0;
	}
	return res;
    }

    //5.
    public static void simulation(int k, int n, int m){
	int[][]gr=creerGrille(n,m);
	int[]pos={0,0};
	int o=2;
	for(int i=0; i<k; i++){
	    pos=avanceEtChange(gr,pos,o);
	    o=nouvelleOrientation(gr, pos, o);
	}
	afficheGrille(gr);
    }

    //////////////////////////////////////////////////////////

    public static void printInt(int[]t){
	for(int i=0; i<t.length; i++){
	    System.out.print(t[i]+" ");
	}
	System.out.println();
    }

    public static void main(String[]args){
	//int[]t={4,2,8,5};
	/*echange(t,0);
	  printInt(t);*/
	/*int[]t1={3,2,5,8,4};
	  balayage(t1);
	  printInt(t1);*/
	/*algo(t);
	  printInt(t);*/
	
	//int[]t={12,3,2,8};
	//printInt(sousTab(t,3));
	//afficheGrille(toutesLesPaires(t));
	//printInt(sommePaire(t,1));

	int[][]gr1={{0,0,0,0,0},{0,0,0,0,0},
		    {0,0,0,0,0},{0,0,0,0,0}};
	int[][]gr2={{1,1,1,1},{1,1,1,1},
		    {1,1,1,1},{1,1,1,1}};
	int[]pos={2,3};
	//System.out.println(nouvelleOrientation(gr2,pos,2));
	/*printInt(avanceEtChange(gr1,pos,1));
	  afficheGrille(gr1);*/
	simulation(50,5,5);
    }
}

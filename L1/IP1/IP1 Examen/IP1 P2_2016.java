public class E2016{

    public static int nextHour(int x){
	x=x-x/9;
	return x;
    }

    public static int halfLife(int x){
	int h=0;
	int tmp=x;
	while(tmp>x/2){
	    tmp=nextHour(tmp);
	    h++;
	}
	return h;
    }

    public static boolean enoughprogrammium(int x,int n){
	int tmp=x;
	for(int i=0; i<n; i++){
	    tmp=nextHour(tmp);
	}
	if(tmp>x/2){
	    return true;
	}
	return false;
    }

    ////////////////////////////////////////////////////////////////////////

    public static boolean lessThan(int[]t, int n){
	for(int i=0; i<t.length; i++){
	    if(t[i]>n){
		return false;
	    }
	}
	return true;
    }

    public static boolean decrease(int[]t){
	for(int i=0; i<t.length-1; i++){
	    if(t[i]<t[i+1]){
		return false;
	    }
	}
	return true;
    }

    public static boolean isMedian(int[]t, int m){
	boolean flag=false;
	int inf=0;
	for(int i=0; i<t.length; i++){
	    if(t[i]==m){
		flag=true;
	    }
	    else if(t[i]<m){
		inf++;
	    }
	}
	if(flag && inf==t.length/2){
	    return true;
	}
	return false;
    }

    ////////////////////////////////////////////////////////////////////////

    public static void sumPrevious(int k, int i, int[]f){
	for(int j=0; j<k; j++){
	    f[i]=f[i]+f[i-j-1];
	}
    }

    public static int[] initialize(int n, int k){
	int[]f=new int[n];
	for(int i=0; i<k; i++){
	    f[i]=1;
	}
	return f;
    }

    public static int kbonacci(int n, int k){
	int[]tmp=initialize(n+1,k);
	for(int i=k; i<n+1; i++){
	    sumPrevious(k,i,tmp);
	}
	return tmp[n];
    }

    ////////////////////////////////////////////////////////////////////////

    public static boolean canMove(int n, int[][]doors, int p1, int p2){
	if(n>p1 && n>p2){
	    for(int i=0; i<doors[p1].length; i++){
		if(doors[p1][i]==p2){
		    return true;
		}
	    }
	}
	return false;
    }

    public static void checkPath(int n, int[][]doors, int[]p){
	boolean flag=false;
	if(canMove(n,doors,0,p[0])){
	    flag=true;
	    for(int i=0; i<p.length-1; i++){
		if(!canMove(n,doors,p[i],p[i+1]) && flag){
		    flag=!flag;
		}
	    }
	    if(!canMove(n,doors,p[p.length-1],n-1) && flag){
		flag=!flag;
	    }
	}
	if(flag){
	    System.out.println("Roméo a trouvé Juliette !");
	}
	else{
	    System.out.println("Pauvre Roméo");
	}
    }

    ////////////////////////////////////////////////////////////////////////

    //2.
    public static int[][] makeGrid(){
	int[][]res=new int[8][8];
	for(int i=0; i<8; i++){
	    for(int j=0; j<8; j++){
		res[i][j]=-1;
	    }
	}
	return res;
    }

    //3.
    public static boolean validPos(int r, int c){
	if(r<0 || r>7 || c<0 || c>7){
	    return false;
	}
	return true;
    }

    //4.
    public static void modGrid(int[][]grid, int k, int rBis, int cBis){
	if(validPos(rBis,cBis) && grid[rBis][cBis]==-1){
	    grid[rBis][cBis]=k;
	}
    }

    public static boolean verif(int rBis, int cBis, int r1, int c1){
	if(rBis==r1 && cBis==c1){
	    return true;
	}
	return false;
    }

    public static boolean nextMoves(int[][]grid, int k, int r, int c, int r1, int c1){
        modGrid(grid,k,r-2,c-1);
	modGrid(grid,k,r-2,c+1);
	modGrid(grid,k,r+2,c-1);
	modGrid(grid,k,r+2,c+1);
	modGrid(grid,k,r-1,c-2);
	modGrid(grid,k,r-1,c+2);
	modGrid(grid,k,r+1,c-2);
	modGrid(grid,k,r+1,c+2);
	boolean res=verif(r-2,c-1,r1,c1)||verif(r-2,c+1,r1,c1)
	    ||verif(r+2,c-1,r1,c1)||verif(r+2,c+1,r1,c1)
	    ||verif(r-1,c-2,r1,c1)||verif(r-1,c+2,r1,c1)
	    ||verif(r+1,c-2,r1,c1)||verif(r+1,c+2,r1,c1);
	return res;
    }

    //5.
    public static int[][] searchVal(int[][]grid, int val){
	int[][]tmp=new int[63][2];
	int c=0;
	for(int i=0; i<8; i++){
	    for(int j=0; j<8; j++){
		if(grid[i][j]==val){
		    tmp[c][0]=i;
		    tmp[c][1]=j;
		    c++;
		}
	    }
	}
	int[][]res=new int[c][2];
	for(int i=0; i<c; i++){
	    res[i][0]=tmp[i][0];
	    res[i][1]=tmp[i][1];
	}
	return res;
    }

    public static int nbMoves(int r0, int c0, int r1, int c1){
	if(r0==r1 && c0==c1){  //ATTENTION au cas où case de départ = case d'arrivée (c'est débile oui mais ça peut arriver... )
	    return 0;
	}
	int[][]grid=makeGrid();
	grid[r0][c0]=0;
	int k=1;
	boolean flag = nextMoves(grid,k,r0,c0,r1,c1);
	while(!flag){
	    int[][]tmp=searchVal(grid,k);
	    k++;
	    for(int i=0; i<tmp.length; i++){
		flag=flag||nextMoves(grid,k,tmp[i][0], tmp[i][1], r1, c1);
	    }
	}
	return k;
    }	

    //6.(Bonus)
    public static int nbMoveReal(int r0, int c0, int r1, int c1, boolean[][]taken){
	int res=-1;  //si la case est occupée, alors le programme se termine en renvoyant -1
	if(!taken[r1][c1]){  //sinon, on pourra toujours accéder à la case voulue car les déplacements du cavalier le permettent (on suppose bien évidemment que les pions de l'adversaire ne se déplacent pas pendant notre déplacement donc qu'on peut accéder à toutes les cases libres sans qu'on ne les occupe pendant notre déplacement)
	    int[][]gridTmp=new int[8][8];
	    for(int i=0; i<8; i++){
		for(int j=0; j<8; j++){
		    if(taken[i][j]){
			gridTmp[i][j]=-2;
		    }
		    else{
			gridTmp[i][j]=-1;
		    }
		}
	    }
	    res=nbMoves(r0, c0, r1, c1);
	}
	return res;
    }
	
    
    public static void main(String[]args){
	System.out.println(nbMoves(4,1,2,2));  //résultat attendu : 1
	
	System.out.println(nbMoves(2,3,6,5));  //résultat attendu : 2
	
	boolean t=true;
	boolean f=false;
	boolean[][]taken={{f,f,f,f,f,f,f,f},{f,f,f,f,f,f,f,f},{f,f,t,f,f,f,f,f},{f,f,f,f,f,f,f,f},
			  {f,f,f,f,f,f,f,f},{f,f,f,f,f,f,f,f},{f,f,f,f,f,f,f,f},{f,f,f,f,f,f,f,f}};
	System.out.println(nbMoveReal(4,1,2,2,taken));  //résultat attendu : -1
	
	boolean[][]taken2={{f,f,t,f,f,f,f,f},{f,f,f,f,f,f,f,f},{f,f,f,f,f,f,f,f},{f,f,f,f,f,f,f,f},
			  {f,f,f,f,f,f,f,f},{f,f,f,f,f,f,f,f},{f,f,f,f,f,f,f,f},{f,f,f,f,f,f,f,f}};
	System.out.println(nbMoveReal(4,1,2,2,taken2));  //résultat attendu : 1
    }
}

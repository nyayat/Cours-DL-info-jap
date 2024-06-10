import java.util.Random;

public class P2_2018{

    
    //Exercice 1/////////////////////////////////////////////////////////
    //1. t1={{1,2},{1,2,3},{1,2,3,4}} et t2={0,1,3}, ne modifie pas t1
    //2. a=8, retourne le premier multiple de 4

    
    //Exercice 2/////////////////////////////////////////////////////////
    //1.
    public static boolean retire(int[]t, int num){
	if(t[num]>0){
	    t[num]--;
	    return true;
	}
	return false;
    }

    //2.
    public static boolean plein(int[]t, int cmax){
	for(int i=0; i<t.length; i++){
	    if(t[i]<cmax){
		return false;
	    }
	}
	return true;
    }

    //3.
    public static int[] tiroirsLibres(int[]t, int cmax){
	int[]tmp=new int[t.length];
	int c=0;
	if(!plein(t,cmax)){
	    for(int i=0; i<t.length; i++){
		if(t[i]<cmax){
		    tmp[c]=i;
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

    //4.
    /*public static int[] ajouteElem(int[]t, int cmax, int num){  //la version "fonction"
	int[]res=new int[t.length];
	boolean flag=false;
	for(int i=num; i<t.length; i++){
	    if(t[i]<cmax && !flag){
		res[i]=t[i]+1;
		flag=!flag;
	    }
	    else{
		res[i]=t[i];
	    }
	}
	for(int i=0; i<num; i++){
	    if(t[i]<cmax && !flag){
		res[i]=t[i]+1;
		flag=!flag;
	    }
	    else{
		res[i]=t[i];
	    }
	}
	return res;
	}*/

    public static int[] ajouteElem(int[]t, int cmax, int num){  //plutôt une procédure
	boolean flag=false;
	for(int i=num; i<t.length; i++){
	    if(t[i]<cmax && !flag){
		t[i]+=1;
		flag=!flag;
	    }
	}
	for(int i=0; i<num; i++){
	    if(t[i]<cmax && !flag){
		t[i]+=1;
		flag=!flag;
	    }
	}
	return t;
    }

    //5.
    public static int[] ajoutePlusieursElem(int[]t, int cmax, int[]stock){  //plutôt une procédure
	for(int i=0; i<stock.length; i++){
	    t=ajouteElem(t,cmax,stock[i]);
	}
	return t;
    }

    //Exercice 3/////////////////////////////////////////////////////////
    //1.
    public static int tailleMotPlusLong(String[]dico){
	int res=0;
	for(int i=0; i<dico.length; i++){
	    if(dico[i].length()>res){
		res=dico[i].length();
	    }
	}
	return res;
    }
    
    //2.
    public static String[] motsPremiereLettre(String[]dico, char ch){ //String d'un seul caractère=char... :)
	String[]tmp=new String[dico.length];
	int c=0;
	for(int i=0; i<dico.length; i++){
	    if(dico[i].charAt(0)==ch){
		tmp[c]=dico[i];
		c++;
	    }
	}
	String[]res=new String[c];
	for(int i=0; i<c; i++){
	    res[i]=tmp[i];
	}
	return res;
    }    

    //3.
    public static String[] effaceEspace(String s){
	String stMot="";
	String[]tmp=new String[s.length()/2+1];  //dans le pire des cas, on a lettre|espace|lettre...
	int c=0;
	s+=" ";
	for(int i=0; i<s.length(); i++){
	    if(s.charAt(i)==' '){
		tmp[c]=stMot;
		c++;
		stMot="";
	    }
	    else{
		stMot+=s.charAt(i);
	    }
	}
	String[]res=new String[c];
	for(int i=0; i<c; i++){
	    res[i]=tmp[i];
	}
	return res;
    }
     
    //4.
    public static boolean search(String[]dico, String mot){
	for(int i=0; i<dico.length; i++){
	    if(dico[i].equals(mot)){
		return true;
	    }
	}
	return false;
    }
    
    public static boolean estCorrect(String s, String[]dico){
	String[]tmp=effaceEspace(s);
	boolean res=true;
	for(int i=0; i<tmp.length; i++){
	    res=res && search(dico,tmp[i]);
	}
	return res;
    }

    //5.
    public static void corrige(String s, String[]dico){
	if(estCorrect(s,dico)){
	    System.out.println(s);
	}
	else{
	    String[]tmp=effaceEspace(s);
	    for(int i=0; i<tmp.length; i++){
		if(search(dico,tmp[i])){
		    System.out.print(tmp[i]+" ");
		}
	    }
	    System.out.println();
	}
    }
    

    //Exercice 4/////////////////////////////////////////////////////////
    //1.
    public static int moyenneEtu(int[][]bulletins, int numEtu){
	int res=0;
	int nbMat=bulletins[0].length;
	for(int j=0; j<nbMat; j++){
	    res+=bulletins[numEtu][j];
	}
	res=res/nbMat;
	return res;
    }

    //2.
    public static int passe(int[][]bulletins){
	int res=0;
	for(int i=0; i<bulletins.length; i++){
	    if(moyenneEtu(bulletins,i)>=10){
		res++;
	    }
	}
	return res;
    }

    //3.
    public static int maxMoy(int[][]bulletins){
	int res=0;
	for(int i=0; i<bulletins.length; i++){
	    if(moyenneEtu(bulletins,i)>res){
		res=moyenneEtu(bulletins,i);
	    }
	}
	return res;
    }
    
    public static int[] meilleurs(int[][]bulletins){
	int max=maxMoy(bulletins);
	int[]tmp=new int[bulletins.length];
	int c=0;
	for(int i=0; i<bulletins.length; i++){
	    if(moyenneEtu(bulletins,i)==max){
		tmp[c]=i;
		c++;
	    }
	}
	int[]res=new int[c];
	for(int i=0; i<c; i++){
	    res[i]=tmp[i];
	}
	return res;
    }
    

    //Exercice 5/////////////////////////////////////////////////////////
    //1.
    public static int[][] creerConfiguration(int M, int N){
	int[][]conf=new int[M][N];
	return conf;
    }

    //2.
    public static void afficheConfiguration(int[][]conf){
	for(int i=0; i<conf.length; i++){
	    for(int j=0; j<conf[i].length; j++){
		if(conf[i][j]==0){
		    System.out.print("- ");
		}
		else{
		    System.out.print("O ");
		}
	    }
	    System.out.println();
	}
    }
    
    //3.
    public static boolean insertion(int[][]conf, int numP, int col){  //ATTENTION à la numérotation des lignes...
	if(numP==1){  //piece 1
	    if(conf[0][col]==1){  //toute la colonne est occupée
		return false;
	    }
	    else{  //modif quand on le peut
		boolean flag=false;
		int i=conf.length-1;  //on parcourt depuis le bas
		while(!flag){
		    if(conf[i][col]==0){
			conf[i][col]=1;
			flag=!flag;
		    }
		    i--;
		}
	    }
	}
	else{  //pieces 2, 3 et 4 ont des conditions semblables donc on peut les regrouper ensemble
	    if(col+1>=conf[0].length){  //colonne trop à gauche
		return false;
	    }
	    else{
		if(conf[0][col+1]==1 || conf[0][col]==1 || conf[1][col]==1 || conf[1][col+1]==1){  //cases occupées
		    return false;
		}
		else{  //modif quand on le peut
		    boolean flag=false;
		    int i=conf[0].length-1;  //on parcourt depuis le bas
		    while(!flag){
			if(conf[i-1][col]==0 && conf[i-1][col+1]==0 && conf[i][col]==0 && conf[i][col+1]==0){  //le bloc de 4 cases libre (les deux cases du bas libres comme condition aurait suffit vu que le jeu meurt si une ligne du milieu se remplit)
			    conf[i][col]=1;
			    conf[i][col+1]=1;
			    conf[i-1][col]=1;
			    conf[i-1][col+1]=1;  //on remplit toutes les cases du bloc  d'abord
			    if(numP==2){  //puis on enlève la case qui devrait être libre
				conf[i-1][col+1]=0;
			    }
			    if(numP==3){
				conf[i-1][col]=0;
			    }
			    flag=!flag;
			}
			i--;
		    }
		}
	    }
	}
        return true;
    }
	    
    //4.
    public static int choixPiece(){
	int res=alea(1,5);
	return res;
    }

    //5.
    public static int colonne(int[][]conf, int numP){
	afficheConfiguration(conf);
	System.out.println("la pièce suivante est : " + numP + "\nDans quelle colonne l'insérer ?");
	int col=getInt();
	return col;
    }

    //6.
    public static boolean estSimple(int[][]conf){
	for(int i=0; i<conf[0].length; i++){
	    if(conf[conf.length-1][i]==0){
		return true;
	    }
	}
	return false;  //quand peut être simplifié
    }

    //7.
    public static void supprimeLigne(int[][]conf){
	for(int i=conf.length-1 ;i>=0; i--){
	    for(int j=0; j<conf[0].length; j++){
		if(i==0){
		    conf[i][j]=0;
		}
		else{
		    conf[i][j]=conf[i-1][j];
		}
	    }
	}
    }

    //8.
    public static int simplifie(int[][]conf){  //comment on fait quand la dernière ligne n'est pas remplit mais que celles d'avant si ? :)
	int res=0;
	while(!estSimple(conf)){
	    res++;
	    supprimeLigne(conf);
	}
	return res;
    }

    //9.
    public static void jouer(int M, int N){
	int[][]conf=creerConfiguration(M,N);
	boolean flag=true;  //on peut jouer
	int points=0;
	while(flag){
	    int numP=choixPiece();
	    int col=colonne(conf,numP);
	    flag=insertion(conf,numP,col);
	    points+=simplifie(conf);
	    System.out.println("-------------------------------");  //facultatif : juste pour éclaircir la partie :P
	}
	System.out.println("Vous avez obtenu "+points+" points.");
    }

    //Annexes/////////////////////////////////////////////////////////

    public static int getInt(){
	int res=Integer.parseInt(System.console().readLine());
	return res;
    }
    
    static Random rand=new Random();
    
    public static int alea(int a, int b){
	return (rand.nextInt(b-a)+a);
    }

    public static void printInt(int[]t){
	for(int i=0; i<t.length; i++){
	    System.out.print(t[i]+" ");
	}
	System.out.println();
    }

    public static void printString(String[]t){
	for(int i=0; i<t.length; i++){
	    System.out.print(t[i]+".");
	}
	System.out.println();
    }

    //Tests/////////////////////////////////////////////////////////

    public static void main(String[]args){
	int[]t={1,0,2};
	int[]t2={3,3,3};
	int[]t3={5,4,6,2,3};
	int[]t4={2,2,1};
	int[]t5={3,2,3,1,3};
	int[]t6={3,3,3,3};
	int[]t7={2,2,1};
	int[]tmp={1,0,1};
	int[]tmp2={0,1,0};
	/*System.out.println(retire(t,0));
	  printInt(t);*/
	/*System.out.println(retire(t,1));
	  printInt(t);*/
	/*printInt(tiroirsLibres(t,2));
	  printInt(tiroirsLibres(t2,3));
	  printInt(tiroirsLibres(t3,5));*/
	/*printInt(ajouteElem(t,2,1));
	  printInt(ajouteElem(t4,2,1));
	  printInt(ajouteElem(t5,3,4));
	  printInt(ajouteElem(t6,3,1));*/
	/*printInt(ajoutePlusieursElem(t,2,tmp));
	  printInt(ajoutePlusieursElem(t7,2,tmp2));*/

	String[]D={"monde","le","petit"};
	String[]D2={"monde","le","petit","merci"};
	//System.out.println(tailleMotPlusLong(D));
	//printString(motsPremiereLettre(D2,'m'));
	//printString(effaceEspace("le monde est petit"));
	/*System.out.println(estCorrect("le monde est petit",D2));
	  System.out.println(estCorrect("le monde petit",D2));*/
        //corrige("le monde est petit",D2);

	int[][]bulletins={{0,10},{10,10},{15,20}};
	int[][]bulletins2={{0,10},{10,10},{15,20}, {15,20}};
	/*System.out.println(moyenneEtu(bulletins,0));  //attendu : 5
	  System.out.println(moyenneEtu(bulletins,1));  //attendu : 10
	  System.out.println(moyenneEtu(bulletins,2));  //attendu : 17*/
	//System.out.println(passe(bulletins));  //attendu : 2
	/*printInt(meilleurs(bulletins));  // attendu : 2
	  printInt(meilleurs(bulletins2));  // attendu : 2 3*/

	//jouer(5,5);  //ATTENTION : si on remplit les lignes d'en haut avant la dernière, le jeu est mort :)
    }
}

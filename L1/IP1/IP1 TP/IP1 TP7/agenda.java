import java.io.*;
import java.util.*;

class agenda {

    // parametres 2019
    static int yearLength = 365; // 2019 n'est pas une année bisextile
    static int firstWDay = 1; // 2019 commence un mardi

    /* ***********************************************************/
    /* FONCTION PRINCIPALE                                       */
    /* ***********************************************************/

    public static int compteur (String[]t){
	int c=0;
	for(int i=0; i<t.length; i++){
	    if(!(t[i].equals(""))){
		c++;
	    }
	}
	return c;
    }

    public static void showAgenda2 (String []t, int startDay, boolean reverse){
	if(reverse==true){
	    for(int i=startDay; i>=0; i--){
		if(!(t[i].equals(""))){
		    System.out.println(i+" "+t[i]);
		}
	    }
	}
	else{
	    for(int i=0; i<t.length; i++){
		if(!(t[i].equals(""))){
		    System.out.println(i+" "+t[i]);
		}
	    }
	}
    }

    public static void showAgenda (String []t, String[]wDays){
	System.out.println("Il y a "+compteur(t)+" événements dans l'agenda.");
	for(int i=0; i<t.length; i++){
	    if(!(t[i].equals(""))){
		System.out.print(wDays[(i+4)%7]+" ");
		print(dateOfDayNumber(i));
		System.out.println(" "+t[i]);
	    }
	}
    }
    
    /* ***********************************************************/
    /* CONVERSION DE DATE                                        */
    /* ***********************************************************/

    public static int monthOfDayNumber (int dayNumber) {
	int [] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};  //pour une ann�e non bissextile, sinon remplacer 28 par 29
	int res = 1;
	dayNumber++;
	for (int i = 0; i<12; i++){  
	    if (dayNumber > daysInMonth[i]) {
		dayNumber = dayNumber - daysInMonth[i];
		res++; 
	    }
	}
	return res;
    }

    public static int[] dateOfDayNumber(int dayNumber){
	int [] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};  //pour une ann�e non bissextile, sinon remplacer 28 par 29
	int [] res= new int[2];
	res[1]=monthOfDayNumber(dayNumber+1);
	res[0]=dayNumber+1;
	for(int i=0; i<res[1]-1; i++){
	    res[0]=res[0]-daysInMonth[i];
	}
	if (res[0]==0){
	    res[1]=res[1]-1;
	    res[0]=daysInMonth[res[1]-1];
	}
	return res;
    }

    public static void calender(String[] t){
	String [] wDays = { "Lun " ,"Mar " ,"Mer " ,"Jeu " , "Ven " ,"Sam " ,"Dim " };
	String [] Month={"Janvier","Fevrier", "Mars", "Avril", "Mai", "Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
	for(int i=0;i<yearLength; i++){  //pour tester tous les jours de l'annee
	    int date=dateOfDayNumber(i)[0];  //numero du jour (entre 1 et 31)
	    int mois=dateOfDayNumber(i)[1];  //numero du mois (entre jan et dec)
	    String dat=" "+date+" ";  //valeur a mettre dans le tableau
	    if(date<10){
		dat=" 0"+date+" ";  //mettre un "0" devant les nombres de 1 a 9
	    }
	    if(date==1){  //a chaque debut de mois, initialiser la presentation voulue
		if((i+firstWDay+1)%7!=1 && mois!=1){  //rajoute un saut de ligne quand au mois precedent on n'est pas arrive au bout de la ligne
		    System.out.println();
		}
		System.out.println();
		System.out.println(Month[mois-1]);  //affichage du mois en entete
		System.out.println("---------------------------");
		for (int j=0;j<7;j++){  //affichage des jours de la semaine
		    System.out.print(wDays[j]);
		}
		System.out.println();
		for(int k=0; k<(i+firstWDay)%7; k++){  //mettre autant d'espaces necessaires pour aligner le premier jour avec son jour de semaine
		    System.out.print("    ");
		}
	    }
	    if(t[i].equals("")){  //s'il n'y a pas d'evenement, mettre "... "
		System.out.print("... ");
	    }
	    else{  //s'il y a un evenement, mettre le numero du jour
		System.out.print(dat);
	    }
	    if((i+firstWDay+1)%7==0){  // quand on arrive a dimanche, revenir au debut de la ligne
		System.out.println();
	    }
	}
	System.out.println();
    }

    public static void main (String [] args) {

	String [] t = loadAgenda("agenda.dat");
	String [] wDays = { " Lun " ," Mar " ," Mer " ," Jeu " , " Ven " ," Sam " ," Dim " };
	String [] Month={"Janvier","Février", "Mars", "Avril", "Mai", "Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};

        //showAgenda(t,wDays);
	//showAgenda2(t,87,true);
	//print(dateOfDayNumber(86));
	//print(dateOfDayNumber(0));
	calender(t);
	
    }
    

    /* ***********************************************************/
    /* ANNEXE                                                    */
    /* ***********************************************************/

    public static String [] loadAgenda(String fileName) {
    // Lit un fichier contenant un agenda et le renvoie sous forme
    // de tableau

	String [] res = new String[yearLength];

	for (int i=0;i<yearLength;i++){
	    res[i] = "";
	}

	try { 
	    Scanner sc = new Scanner (new File(fileName)).useDelimiter("\n");
	    while (sc.hasNext()) {
		String [] line = sc.next().split(":");
		int day = Integer.parseInt(line[0]);
		String desc = line[1];
		res[day] = desc;
	    }
	    return res;
	} catch (IOException e) {
	    System.out.println("erreur de lecture de fichier"); 
	    return res;
	}
	
    }

    public static void print(int []t){
	System.out.print(t[0]+"/"+t[1]);
    }

}

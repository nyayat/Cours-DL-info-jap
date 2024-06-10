public class Calendar {
    /* EXERCICE 3 */
	// EXERCICE 3 QUESTION 1 
	public static int diffCounter (int start, int end) {
		// Modifier le code ci-dessous
	    int diff=end-start;
	    if (diff<0) {
		diff=-diff;
	    }
	    return (diff);
	}

	// EXERCICE 3 QUESTION 2
	public static int weekdayOfCounter(int date) {
		// Modifier le code ci-dessous
	    int jour=date%7;
	    return (jour);
	}
    
	/* EXERCICE 4 */
	// EXERCICE 4 QUESTION 1
	public static boolean isLeapYearJulian(int year) {
		// Modifier le code ci-dessous
	    boolean x=false;
	    if (year%4==0) {
		x= true;
	    }
	    return(x);
	}

	// EXERCICE 4 QUESTION 2
	public static int daysInYearJulian(int year) {
		// Modifier le code ci-dessous
	    int nbJours=365;
	    if (isLeapYearJulian(year)==true) {
		nbJours=366;
	    }
		return(nbJours);
	}

	/* EXERCICE 5 */
	public static int daysInMonth(int month, boolean inLeapYear) {
		// Modifier le code ci-dessous
		int days=30;
	        if (month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
		    days=31;
		}
		if (month==2) {
		    if (inLeapYear==true) {
			days=29;
		    }
		    else {
			days=28;
		    }
		}
		return(days);
	}

	/*EXERCICE 6*/
	// Declarez la fonction julianToCounter prenant en paramètre l'année, le mois, et le jour et renvoyant la date correspondante au format "compteur"
    public static int julianToCounter(int year, int month, int day) {
	int Dy=0;
	int Dm=0;
	for (int i=1;i<year;i++) {
	    Dy=Dy+daysInYearJulian(i);
	}
	for (int o=1;o<month;o++) {
	    Dm=Dm+daysInMonth(o, isLeapYearJulian(year));
	}
	day=day+Dm+Dy-2;
        return(day);
    }

	/*EXERCICE 7*/
	// A completer
    public static boolean isLeapYearGregorian(int year) {
	boolean biss=false;
	if ((year%4==0 && year%100!=0)||(year%400==0)) {
	    biss=true;
	}
	return(biss);
    }


    public static int daysInYearGregorian(int year) {
	int nbdays=365;
	if (isLeapYearGregorian(year)==true) {
	    nbdays=nbdays+1;
	}
	return(nbdays);
    }
	/*EXERCICE 9*/
	//A completer
    public static int gregorianToCounter(int year, int month, int day) {
	int Dy=0;
	int Dm=0;
	for (int i=1;i<year;i++) {
	    Dy=Dy+daysInYearGregorian(i);
	}
	for (int o=1;o<month;o++) {
	    Dm=Dm+daysInMonth(o, isLeapYearGregorian(year));
	}
	day=day+Dm+Dy;
        return(day);
    }

	/*EXERCICE 10*/
    public static int weekdayOfGregorian (int year, int month, int day){
	int qj=gregorianToCounter(year, month, day)%7;
	return qj;
    }
	
	
    public static int dayOfSummertime(int year) {
	int d =25;
	for (int i=25;i<32;i++) {
	    if (weekdayOfGregorian(year, 3, i)==0) {
		d=i;
	    }
	}
	return d;
    }
    

	public static void main (String[] args) {

		// ENLEVEZ LES COMMENTAIRES AU FUR ET A MESURE POUR TESTER VOS FONCTIONS
    
	      

		// System.out.println("====Ex10Q2====");
	        // Ecrire le test
		// testInt("gregorianToCounter(1582,10,15)-julianToCounter(1582,10,4)",gregorianToCounter(1582,10,15)-julianToCounter(1582,10,4),1);
		 
		System.out.println("====Ex10Q3====");
		testInt("dayOfSummertime(2020)", dayOfSummertime(2020), 29);
	}


	/*************************************************************/
	/* NE PAS MODIFIER */
	/*************************************************************/
	public static void testInt(String cmd, int exp, int val) {
		System.out.println(cmd + " == " + exp);
		if (exp != val) {
            System.out.println("Ce n'est pas la bonne réponse!");
		}
	}

	public static void testBoolean(String cmd, boolean exp, boolean val) {
		System.out.println(cmd + " == " + exp);
		if (exp != val) {
            System.out.println("Ce n'est pas la bonne réponse!");
		}
	}

}

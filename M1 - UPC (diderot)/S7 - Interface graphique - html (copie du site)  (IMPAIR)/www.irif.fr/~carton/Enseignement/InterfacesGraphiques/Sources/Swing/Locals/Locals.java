// Time-stamp: <Locals.java   5 Jan 2004 19:46:52>

import java.util.Locale;

/**
 * Affichage des différentes localisations possibles
 * @author O. Carton
 * @version 1.0
 */
class Locals {
    /** Affichage d'une Locale */
    static void printLocale(Locale locale) {
	System.out.println("Name : " + locale.getDisplayName());
	System.out.println("\tDisplay Country : " + locale.getDisplayCountry());
	System.out.println("\tDisplay Language : " + locale.getDisplayLanguage());
	
	System.out.println("\tDisplay Variant : " + locale.getDisplayVariant());
	System.out.println("\tCountry : " + locale.getCountry());
	System.out.println("\tLanguage : " + locale.getLanguage());
	System.out.println("\tVariant : " + locale.getVariant());
	System.out.println("\tISO3 Country : " + locale.getISO3Country());
	System.out.println("\tISO3 Language : " + locale.getISO3Language());
    }
    public static void main(String [] args)
    {
	// Locale par défaut
	System.out.println("Locale par défaut");
	printLocale(Locale.getDefault());

	// Locales installées
	System.out.println("Locales installées");
	Locale[] locals = Locale.getAvailableLocales();
	for(int i = 0; i < locals.length; i++)
	    printLocale(locals[i]);
	System.out.println();

	// Countries de la norme ISO
	System.out.print("ISO Countries : ");
	String[] countries = Locale.getISOCountries();
	for(int i = 0; i < countries.length; i++)
	    System.out.print(countries[i] + " ");
	System.out.println();

	// Languages de la norme ISO
	System.out.print("ISO Languages : ");
	String[] languages = Locale.getISOLanguages();
	for(int i = 0; i < languages.length; i++)
	    System.out.print(languages[i] + " ");
	System.out.println();
	    

	
	
    }
}

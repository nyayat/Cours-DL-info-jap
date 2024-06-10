/* suite et fin de la série AppelFonction
 * il est possible de factoriser davantage
 * ce code (avec boucles et/ou méthodes)
 * avant de chercher à le traduire...
 */

class AppelFonction4ATraduire{
    static void m(){
        wee();
        System.out.println("can I get a oooh wee");
        System.out.println("Makeba, makes my body dance for you");
    }
    public static void refrain(){
        m();
        m();
        System.out.println("");
    }
    public static void couplet1(){
        System.out.println("I wanna hear your breath just next to my soul");
        System.out.println("I wanna feel your breasts without any rests");
        System.out.println("I wanna see you sing, I wanna see you fight");
        System.out.println("'Cause you are the real beauty of human rights\n");
    }
    public static void couplet2(){
        System.out.println("Nobody can beat the mama Africa");
        System.out.println("You follow the beat that she’s gonna give you");
        System.out.println("I need a smile you can love");
        System.out.println("Make it go, the sufferation of a thousand more\n");
    }
    public static void wee(){
        System.out.println("Makeba, ma che bella, ooh wee");
    }
    public static void main(String[] args){
        refrain();
        refrain();
        couplet1();
        refrain();
        couplet2();
        refrain();
        refrain();
        refrain();
        for(int i=0; i<4; i++) wee();
    }
}

/* Makeba
 * Jain
 * paroles:
 * * Dambuza Mdledle Nathan
 * * Jeanne Galice
 * * Joseph Mogotsi
 * * Ronnie Modise Majola Sehume
 * * Rufus Khoza
 */



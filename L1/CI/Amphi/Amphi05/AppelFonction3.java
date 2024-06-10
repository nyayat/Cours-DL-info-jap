class AppelFonction3{
    static void m(){
        wee();                                                      // instruction #15000
        System.out.println("can I get a oooh wee");                 // instruction #15001
        System.out.println("Makeba, makes my body dance for you");  // instruction #15002
    }                                                               // instruction #15003: retour
    static void wee(){
        System.out.println("Makeba, ma che bella, ooh wee");        // instruction #5000
    }                                                               // instruction #5001: retour
    public static void main(String[] args){
        for(int i=0;                                                // instruction #0
            i<4;                                                    // instruction #1
            i++){                                                   // instruction #3
            m();                                                    // instruction #2: appel à wee
        }                                                           // instruction #4: fin boucle
    }                                                               // instruction #5: fin main
}

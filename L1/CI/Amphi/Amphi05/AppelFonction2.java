class AppelFonction2{
    static void wee(){
        System.out.println("Makeba, ma che bella, ooh wee");    // instruction #5000
    }                                                           // instruction #5001: retour
    public static void main(String[] args){
        for(int i=0;                                            // instruction #0
            i<4;                                                // instrcution #1
            i++){                                               // instrcution #3
            wee();                                              // instruction #2: appel à wee
        }                                                       // instrcution #4: fin boucle
    }                                                           // instrcution #5: fin main
}

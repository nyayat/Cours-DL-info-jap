class BellaCiao3{
    static void rf(){
        int i;
        System.out.print("O");
        for(i=0; i<5; i++){
            if(i<3) System.out.print(" bella");
            System.out.print(" ciao");
            if(i<4) System.out.print(",");
        }
        System.out.print("\n");
    }
    static void pr(String s){
        System.out.println(s);
    }
    static void bc(String s1,String s2){
        pr(s1);
        rf();
        pr(s1);
        pr(s2);
    }
    public static void main(String[] a){
        bc("Una mattina mi son alzato","E ho trovato l'invasor");
        bc("O partigiano portami via","Che mi sento di morir");
        bc("E se io muoio da partigiano","Tu mi devi seppellir");
        bc("Mi seppellirai lassù in montagna","Sotto l'ombra di un bel fior");
        bc("E le genti, che passeranno","Mi diranno: che bel fior!");
        bc("E quest’è’l fiore del partigiano","Morto per la libertà");
    }
}

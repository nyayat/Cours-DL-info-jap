class EF{
    static int f(int n){//factorielle
        int i=1, r=1;   // 100 (màj variable1) // 101 (màj variable2)
        while(i<=n){    // 102
            r = r*i;    // 103 (màj variable2)
            i = i+1;    // 104 (màj variable1)
        }               // 105
        return r;       // 106: retour
    }                   // 107: sortie
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    static int e(int n, int m){//exponentiation
        int r=1;        // 500
        while(m>0){     // 501
            r = r*n;    // 502 (màj variable1)
            m = m-1;    // 503 (màj argument2)
        }               // 504
        return r;       // 505: retour
    }
    public static void main(String[] args){
        System.out.println("resultat : "+f(5));
            //0: appel  //1: affichage
        System.out.println("resultat : "+e(4,3));
            //2: appel  //3: affichage
    }   // 4: sortie
}

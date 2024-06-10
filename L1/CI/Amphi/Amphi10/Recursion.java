class Recursion{
    static int u(int m, int n){
        if (m==0 || n==0)           // 100
            return 1;               // 101 : retour -- 102: sortie
        return 2 * u(m-1, n+1);     // 103 : appel -- 104 : retour -- 105: sortie
    }
    
    static int v(int n){
        if (n==0) return 1;             // 200 : test -- 201 : retour -- 202 : sortie
        if (n%2==0) return n * v(n/2);  // 203 : test -- 204 : appel -- 205 : retour  -- 206 : sortie
        return v(n/2);                  // 207 : appel -- 208 : retour  -- 209 : sortie
    }
    
    static int w(int n){
        if (n==0) return 1;
        if (n%3==0) return n * w(n/3);
        return 1 + w(n/3);
    }
    
    static int utermaux(int m, int n, int acc){
        if (m==0 || n==0)                   // 1000
            return acc;                     // 1001 : retour -- 1002: sortie
        return utermaux(m-1, n+1, 2 * acc); // 1003 : appel -- 1004 : retour -- 1005: sortie
    }
    static int uterm(int m, int n){
        return utermaux(m,n,1);             // 1500 : appel 1501: retour 1502: sortie
    }
    
    
    
    
    public static void main(String[] a){
        System.out.println(u(3,7)); // 0: appel 1: retour et affichage
        System.out.println(v(51));  // 2: appel 3: retour et affichage
        System.out.println(w(51));  // 4: appel 5: retour et affichage
    }   // 6: sortie
}

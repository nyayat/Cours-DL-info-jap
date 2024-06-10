class Mystery {

/**** Les fonctions à tester ****/

public static String mystere0(String s) {
    if (s.length () == 0) {
        return "";
    }
    else {
        return mystere0(s.substring(1)) + characterAtPos(s,0);
    }
}

public static String mystere1(int n) {
    int a = n / 3600;
    int c = n % 3600;
    int b = c / 60;
    c = c % 60;
    return (Integer.toString(a) + ":"
            + Integer.toString(b) + ":"
            + Integer.toString(c));
}

public static int mystere2(int n) {
    int u = 1;
    for (int x = 1; x <= n; x++) {
        u = u * x;
    }
    return u;
}

public static int mystere3(int n) {
    int m = 0;
    while (1<<(m+1) < n) {
        m = m + 1;
    }
    return m;
}

public static int mystere4(int n) {
    if (n==0) {
        return 2;
    } else {
        return mystere4(n-1)*mystere4(n-1);
    }
}

public static int mystere5(int n) {
    int a = 0;
    int s = 1;
    int t = 1;
    while (s<=n) {
        a = a+1;
        s = s + t + 2;
        t = t + 2;
    }
    return a;
}

public static int mystere6(int n) {
    int b = 2;
    int m = n;
    int r = 1;
    while (m > 0) {
        if (m % 2 == 1) {
            r = r * b;
        }
        b = b * b;
        m = m / 2;
    }
    return r;
}

/**** La fonction principale, pour lancer vos tests ****/

public static void main(String[] args) {
    System.out.println("Pas encore de tests ici...");
}


/**** Les fonctions auxiliaires ****/

public static String characterAtPos(String s, int i) {
    if (i >= 0 && i < s.length ()) {
        return (String.valueOf(s.charAt(i)));
    } else {
        return "";
    }
}

}

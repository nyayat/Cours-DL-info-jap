class Facto{
    static int f(int n){
        int i=1, r=1;
        while(i<=n){
            r = r*i;
            i = i+1;
        }
        return r;
    }
    public static void main(String[] args){
        System.out.println("resultat : "+f(4));
    }
}

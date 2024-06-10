class Fibo{
    public static void main(String[] a){
        int u=0, v=1, w, k=12;
        for(int i=0; i<k; i++){
            w=u+v;
            u=v;
            v=w;
        }
        System.out.println("fibo("+k+")="+v);
    }
}

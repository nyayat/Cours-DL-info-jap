class Fibo{
    public static void main(String[] a){
        int u=0,        // #0 (mem[0])
            v=1,        // #1 (mem[1])
            w,          //    (mem[2])
            k=12;       // #2 (mem[3])
        for(int i=0;    // #3 (mem[4])
                i<k;    // #4
                i++){   // #8
            w=u+v;      // #5
            u=v;        // #6
            v=w;        // #7
        }               // #9 (saut inconditionnel)
        System.out.println(v);  //#10
    }                   // #11 (sortie définitive)
}

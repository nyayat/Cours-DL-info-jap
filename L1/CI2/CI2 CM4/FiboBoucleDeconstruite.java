class FiboBoucleDeconstruite{
    public static void main(String[] a){
        int u=0,                //0 u stocké à mem[0]
            v=1,                //1 v stocké à mem[1]
            w,                  //  w stocké à mem[2]
            k=12;               //2 k stocké à mem[3]
        int i=0;                //3 i stocké à mem[4]
        while(true)
            if(i<k){            //4:saut condtionnel
            w=u+v;              //5
            u=v;                //6
            v=w;                //7
            i++;                //8
        }else break;            //9 saut incond
        System.out.println("fibo("+k+")="+v);   //10
    }                           //11:sortie
}

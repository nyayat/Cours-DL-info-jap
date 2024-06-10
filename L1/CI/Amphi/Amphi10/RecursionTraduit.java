import java.util.*;

class RecursionTraduit{
   public static void main(String[] a){
       Stack<Bloc> p=new Stack<Bloc>();
       int ic=0;
       Bloc b;
       while(true){
            System.out.println(ic+" "+p);
            switch(ic++){
                case 0: p.push(new Bloc2(ic,3,7)); ic=1500; break;          //100 pour u et 1500 pour uterm
                case 1: System.out.println(((BlocR)(p.pop())).val); break;
                /*
                case 2: p.push(new Bloc1(ic,51)); ic=200; break;
                case 3: System.out.println(((BlocR)(p.pop())).val); break;
                case 4: p.push(new Bloc1(ic,51)); ic=300; break;
                case 5: System.out.println(((BlocR)(p.pop())).val); break;
                */
                default: System.exit(0);
                    
                case 100: if(!( ((Bloc2)(p.peek())).arg1==0 || ((Bloc2)(p.peek())).arg2==0 )) ic+=2; break;
                case 101: ((BlocR)(p.peek())).val=1; break;
                case 102: ic=((BlocR)(p.peek())).adr; break;
                case 103: p.push(new Bloc2(ic,((Bloc2)(p.peek())).arg1-1,((Bloc2)(p.peek())).arg2+1)); ic=100; break;
                case 104: b=p.pop(); ((BlocR)(p.peek())).val= 2 * ((BlocR)b).val; break;
                case 105: ic=((BlocR)(p.peek())).adr; break;
                    
                case 200: if(!( ((Bloc1)(p.peek())).arg1==0 )) ic+=2; break;
                case 201: ((BlocR)(p.peek())).val=1; break;
                case 202: ic=((BlocR)(p.peek())).adr; break;
                case 203: if(!( ((Bloc1)(p.peek())).arg1%2==0 )) ic+=3; break;
                case 204: p.push(new Bloc1(ic,((Bloc1)(p.peek())).arg1/2)); ic=200; break;
                case 205: b=p.pop(); ((BlocR)(p.peek())).val= ((Bloc1)(p.peek())).arg1 * ((BlocR)b).val; break;
                case 206: ic=((BlocR)(p.peek())).adr; break;
                case 207: p.push(new Bloc1(ic,((Bloc1)(p.peek())).arg1/2)); ic=200; break;
                case 208: b=p.pop(); ((BlocR)(p.peek())).val= ((BlocR)b).val; break;
                case 209: ic=((BlocR)(p.peek())).adr; break;
                    
                case 1000: if(!( ((Bloc3)(p.peek())).arg1==0 || ((Bloc3)(p.peek())).arg2==0 )) ic+=2; break;
                case 1001: ((BlocR)(p.peek())).val=((Bloc3)(p.peek())).arg3; break;
                case 1002: ic=((BlocR)(p.peek())).adr; break;
                case 1003: p.push(new Bloc3(ic,((Bloc3)(p.peek())).arg1-1,((Bloc3)(p.peek())).arg2+1,2*((Bloc3)(p.peek())).arg3)); ic=1000; break;
                case 1004: b=p.pop(); ((BlocR)(p.peek())).val= ((BlocR)b).val; break;
                case 1005: ic=((BlocR)(p.peek())).adr; break;
                
                case 1500: p.push(new Bloc3(ic,((Bloc2)(p.peek())).arg1,((Bloc2)(p.peek())).arg2,1)); ic=1000; break;
                case 1501: b=p.pop(); ((BlocR)(p.peek())).val= ((BlocR)b).val; break;
                case 1502: ic=((BlocR)(p.peek())).adr; break;
            }
        }
    }
}

class Bloc{     //bloc générique
    int adr;    //adresseDeRetour
    public Bloc(int n){adr=n;}
}

class BlocR extends Bloc{
    int val;    //valeurDeRetour
    public BlocR(int n){super(n);}
}

class Bloc1 extends BlocR{
    int arg1;
    public Bloc1(int n, int a){
        super(n);
        this.arg1=a;
    }
    public String toString(){
        return "{"+adr+","+val+","+arg1+"}";
    }
}

class Bloc2 extends BlocR{
    int arg1,arg2;
    public Bloc2(int n, int a, int b){
        super(n);
        this.arg1=a;
        this.arg2=b;
    }
    public String toString(){
        return "{"+adr+","+val+","+arg1+","+arg2+"}";
    }
}

class Bloc3 extends BlocR{
    int arg1,arg2,arg3;
    public Bloc3(int n, int a, int b, int c){
        super(n);
        this.arg1=a;
        this.arg2=b;
        this.arg3=c;
    }
    public String toString(){
        return "{"+adr+","+val+","+arg1+","+arg2+","+arg3+"}";
    }
}


class FiboTrad{
    public static void main(String[] a){
        int ic=0;                   //compteur d'instructions
        int[] mem=new int[100000];  //mémoire à plat
        while(true)
            switch(ic++){           //ic incrémenté par défaut
                case 0: mem[0]=0; break;
                case 1: mem[1]=1; break;
                case 2: mem[3]=12; break;
                case 3: mem[4]=0; break;
                case 4: if(!(mem[4]<mem[3])) ic=10; break;
                    //saut conditionnel vers le case 10
                case 5: mem[2]=mem[0]+mem[1]; break;
                case 6: mem[0]=mem[1]; break;
                case 7: mem[1]=mem[2]; break;
                case 8: mem[4]++; break;
                case 9: ic=4; break;
                    //saut inconditionnel vers le case 4
                case 10: System.out.println("fibo("+mem[3]+")="+mem[1]); break;
                case 11: System.exit(0);
            }
    }
}

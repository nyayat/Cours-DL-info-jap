class FiboTrad{
    public static void main(String[] a){
        int[] mem=new int[38];   //mémoire à plat
        int ic=0;               //compteur d'instructions
        while(true){
            System.out.println("ic="+ic+" mem: "+mem[0]+" "+mem[1]+" "+mem[2]+" "+mem[3]+" "+mem[4]);
            switch(ic++){
                case 0: mem[0]=0; break;
                case 1: mem[1]=1; break;
                case 2: mem[3]=12; break;
                case 3: mem[4]=0; break;
                case 4: if(mem[4]>=mem[3]) ic+=5; break;    //saut conditionnel vers ic=10
                case 5: mem[2]=mem[0]+mem[1]; break;
                case 6: mem[0]=mem[1]; break;
                case 7: mem[1]=mem[2]; break;
                case 8: mem[4]++; break;
                case 9: ic-=6; break;                       //saut inconditionnel vers ic=4
                case 10: System.out.println(mem[1]); break;
                case 11: System.exit(0);
            }
        }
    }
}

/* màj du 15/02/2019:
 * renumérotation des instructions
 * valeurs relatives des sauts dans le code
 * (et non plus absolues comme en commentaire)
 */

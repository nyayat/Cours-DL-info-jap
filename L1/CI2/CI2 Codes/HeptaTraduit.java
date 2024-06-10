import java.util.*;

class HeptaTraduit{
    public static void main(String[] args){
        Stack<Bloc> p = new Stack<Bloc>();  // pile d'appel
        int[] mem = new int[100];           // mémoire à plat
        int ic = 0;                         // compteur d'instruction
        int reg = 0;                        // registre
        while(true){
            System.out.println(ic+" : "+p);
            switch(ic++){
                case 0: mem[0] = 2049; break;
                case 1: p.push(new Bloc(ic, mem[0]));
                    //préparation: on empile un bloc d'activation
                    //(contenant l'adresse de retour et une valeur pour l'argument)
                        ic = 3000; break;
                    //saut inconditionnel
                case 2: reg = p.pop().ret;
                    //récupération de la valeur de retour dans le registre dédié et suppression du bloc
                    System.out.println(" ("+mem[0]+")_10 = ("+reg+")_7");
                    //suite de l'instruction avec utilisation de reg et affichage
                    break;
                case 3: System.exit(0);
                case 3000: if(!(p.peek().arg < 7)) ic+=2; break;
                    //saut conditionnel
                case 3001: p.peek().ret = p.peek().arg; break;
                    //mise en place de la valeur de retour
                case 3002: ic = p.peek().adr; break;
                    //sortie de la fonction
                case 3003: p.push(new Bloc(ic,p.peek().arg / 7));
                    //préparation à l'appel à la fonction hepta (appel récursif)
                           ic = 3000; break;
                    //saut inconditionnel
                case 3004: reg = p.pop().ret;
                    //récupération de la valeur de retour dans le registre dédié et suppression du bloc
                           p.peek().ret = reg * 10 + p.peek().arg % 7;
                    //mise en place de la valeur de retour
                case 3005: ic = p.peek().adr; break;
                    //sortie de la fonction
            }
        }
    }
}

class Bloc{
    //bloc spécifique à la fonction hepta...
    int adr, arg, ret;
    
    public Bloc(int adr, int arg){
        this.adr = adr;
        this.arg = arg;
    }

    public String toString(){
        return "H{"+adr+", "+arg+", "+ret+"}";//...
    }
}

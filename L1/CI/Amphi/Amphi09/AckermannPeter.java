import java.util.*;

/* mémoïsation toujours: pour choisir une mémoire auxilaire,
 * les deux arguments de ap font penser à un tableau bidimensionnel
 * mais attention ici:
 * l'appel imbriqué sur la deuxième composante (ligne 22)
 * fait que l'on ne peut prédire ici la taille d'un tel tableau
 * (son nombre de "colonnes" serait égal au résultat)
 *
 * on peut utiliser ici une structure de dictionnaire
 * par exemple de type Hash<Integer,Hash<Integer,Integer>>
 * mais Hash<String,Integer> est plus simple à implémenter...
 */

class AckermannPeter{
    static int cr=0,cm=0;
    //version récursive
    static int ap(int m,int n){
        cr++;
        if(m==0) return n+1;
        if(n==0) return ap(m-1,1);
        return ap(m-1,ap(m,n-1));   //!\
    }
    //version récursive avec affichage "arbre" des appels
    static int ap(int m,int n,String s){
        cr++;
        System.out.println(s+"ap("+m+","+n+")");
        if(m==0) return n+1;
        if(n==0) return ap(m-1,1,s+" ");
        return ap(m-1,ap(m,n-1,s+" "),s+" ");
    }
    //version récursive mémoïsée
    static Map<String,Integer> mem;
    //sans affichage
    static int apaux(int m,int n){
        cm++;
        if(!mem.containsKey(""+m+"-"+n)){
            if(m==0) mem.put(""+0+"-"+n,n+1);
            else if(n==0) mem.put(""+m+"-"+0,apaux(m-1,1));
            else mem.put(""+m+"-"+n,apaux(m-1,apaux(m,n-1)));
        }
        return mem.get(""+m+"-"+n);
    }
    static int apmemo(int m,int n){
        mem=new HashMap<String,Integer>();
        return apaux(m,n);
    }
    //avec affichage "arbre des appels"
    static int apaux(int m,int n,String s){
        cm++;
        System.out.println(s+"apm("+m+","+n+")");
        if(!mem.containsKey(""+m+"-"+n)){
            if(m==0) mem.put(""+0+"-"+n,n+1);
            else if(n==0) mem.put(""+m+"-"+0,apaux(m-1,1,s+" "));
            else mem.put(""+m+"-"+n,apaux(m-1,apaux(m,n-1,s+" "),s+" "));
        }
        return mem.get(""+m+"-"+n);
    }
    static int apmemo(int m,int n,String s){
        mem=new HashMap<String,Integer>();
        return apaux(m,n,s);
    }
    //avec affichage du dictionnaire
    static String apmemostr(int m,int n){
        String s="";
        int ap=apmemo(m,n);
        for(int i=0; i<=m; i++){
            for(int j=0; j<ap; j++)
                if(mem.containsKey(""+i+"-"+j)) s+=mem.get(""+i+"-"+j)+" ";
                else s+=" ";
            s+="\n";
        }
        return s;
    }
    public static void main(String[] args){
        int m=3,n=4;
//      System.out.println(apmemostr(m,n));
        System.out.println(ap(m,n,""));
        System.out.println("(appels:"+cr+")\n");
        System.out.println(apmemo(m,n,""));
        System.out.println("(appels:"+cm+")\n");
    }
}

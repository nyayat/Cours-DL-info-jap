public class Main {
    public static void main (String[]args){
        //1.1
        Noeud a=new Noeud(6, null, new Noeud(8));
        Noeud b=new Noeud(9, new Noeud(2), a);
        Noeud c=new Noeud(5, b, null);
        Noeud d=new Noeud(1, new Noeud(4), null);
        Noeud e=new Noeud(7, new Noeud(0), d);
        Noeud f=new Noeud(3, c, e);
        
        //1.2.d
        Arbre a1=new Arbre(f);
        //a1.affichePenche();
        
        //2.1
        //a1.afficheLargeur();
        
        //2.2.a
        //System.out.println(a1.profondeur());
        
        //2.2.d
        a1.afficheTopdown();
        
        /*Noeud z=new Noeud(0, new Noeud(0), new Noeud(0));
        Noeud y=new Noeud(0, new Noeud(0), new Noeud(0));
        Noeud x=new Noeud(0, new Noeud(0), new Noeud(0));
        Noeud w=new Noeud(0, new Noeud(0), new Noeud(0));
        Noeud v=new Noeud(0, y, z);
        Noeud u=new Noeud(0, null, x);
        Noeud t=new Noeud(0, u, v);
        Arbre a2=new Arbre(t);
        a2.afficheTopdown();*/
    }
}
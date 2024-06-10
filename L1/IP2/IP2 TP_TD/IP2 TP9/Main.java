public class Main {
    public static void main (String[]args){
        //2.
        Noeud a=new Noeud(6, null, new Noeud(8));
        Noeud b=new Noeud(9, new Noeud(2), a);
        Noeud c=new Noeud(5, b, null);
        Noeud d=new Noeud(1, new Noeud(4), null);
        Noeud e=new Noeud(7, new Noeud(0), d);
        Noeud f=new Noeud(3, c, e);
        Arbre g=new Arbre(f);
        //g.afficheInfixe();
        
        //3.
        //g.afficheSuffixe();
        //g.affichePrefixe();
        
        //4.
        //System.out.println(g.nbDeNoeuds());
        
        //5.
        //System.out.println(g.somme());
        
        //6.
        //System.out.println(g.profondeur());
		
		//8.
        /*Arbre h=new Arbre(g);
        g.afficheInfixe();
        System.out.println();
        h.afficheInfixe();*/
        
        //7.
        /*System.out.println(g.recherche(0));
        System.out.println(g.recherche(1));
        System.out.println(g.recherche(2));
        System.out.println(g.recherche(3));
        System.out.println(g.recherche(4));
        System.out.println(g.recherche(5));
        System.out.println(g.recherche(6));
        System.out.println(g.recherche(7));
        System.out.println(g.recherche(8));
        System.out.println(g.recherche(9));
        System.out.println(g.recherche(10));*/
        
        //10.
        int[]tab={6, 1, 2, 3, 7, 5};
        Arbre h=new Arbre(tab);
        h.afficheInfixe();
        System.out.println();
        h.affichePrefixe();
    }
}
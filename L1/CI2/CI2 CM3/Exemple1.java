class Exemple1{
    public static void main(String[] args){
        System.out.println(args.length);
        /* code1
         * l'instruction peut se décomposer en deux parties:
         * - partie déclaration (int v;)
         * désormais l'identifiant v est réservé pour une
         * une variable de type int (primitif, entier, 32bits, complément à 2)
         * attention: à ce moment, on ne peut accéder au contenu de v
         * - partie affectation (v=18;)
         * la variable v reçoit la valeur entière 18
         */
        int v=18;
        System.out.println("valeur entière: "+v);
        /* l'identifiant v est interprété de deux façons:
         * - à gauche du symbole =, une occurrence de v désigne
         * le contenant (la boîte, la case mémoire, etc)
         * - ailleurs, une occurrence de v désigne le contenu
         */
        v=v+1;
        System.out.println("valeur entière: "+v);
        /* le type boolean est un autre type primitif...
         * comme v, bool est stockée sur la pile (stack)
         */
        boolean bool=true;
        /* idem: à gauche, le contenant, à droite, le contenu
         */
        bool=!bool;
        System.out.println("valeur booléenne: "+bool);
        
        /* code2
         * tab désigne une variable de type référence
         * (en l'occurrence, tableau d'entiers de type int)
         * comme pour v ou bool, la première instruction (déclaration) ne crée rien
         * la deuxième instruction commande ici l'allocation mémoire:
         * l'opérateur 'new' recherche 3x4=12 octets consécutifs dans
         * le tas (heap), les réserve et renvoie l'adresse du premier octet
         * 
         * la valeur 0 est donnée par défaut à chaque case
         * (ou false pour le type boolean, et null pour tout type référence)
         */
        int[] tab;
        tab = new int[3];
        /* quand on cherche à afficher le contenu d'une variable
         * de type int[], on obtient une chaîne de caractères:
         * [ pour signifier tableau
         * I pour signifier Integer
         * @ pour annoncer une adresse mémoire
         * 8 chiffres hexa donnant l'adresse effective du premier octet
         * (pour un espace d'adressage sur 8x4=32 bits)
         */
        System.out.println("adresse du premier octet : "+tab);
        System.out.println("valeur de la case 0 : "+tab[0]);
        
        /* code3
         * ttab désigne une variable de type tableau de tableaux d'entiers
         * toujours aucune création à ce stade, juste une déclaration
         */
        int[][] ttab;
        /* allocation partielle:
         * ttab référence dès lors un tableau de deux tableaux
         */
        ttab = new int[2][];
        System.out.println("adresse du premier octet : "+ttab);
        System.out.println("ttab[0] en attente, pour l'instant: "+ttab[0]);
        System.out.println("ttab[1] en attente, pour l'instant: "+ttab[1]);
        /* nouvelle allocation:
         * ttab[0] référence un tableau de 5 entiers (par défaut {0,0,0,0,0})
         * puis {0,1,4,9,16} après 5 affectations (via une boucle for)
         */
        ttab[0]=new int[5];
        System.out.println("adresse du premier octet de ttab[0]: "+ttab[0]);
        short i;
        for(i=0; i<5; i++) ttab[0][i]=i*i;
        /* ttab[1] référence le tableau que référence déjà tab:
         * désormais toute modification de ce tableau pourra se faire
         * via au choix les variables tab ou ttab[1]
         */
        ttab[1]=tab;
        System.out.println("adresse du premier octet de ttab[1]: "+ttab[1]);
        System.out.println("tab et ttab[1] référencent-ils le même objet: "+(tab==ttab[1]));
        
        /* code4
         * tttab désigne un tableau de deux (références vers des) objets de type Smurt
         */
        Smurt[] tttab=new Smurt[2];
        System.out.println("adresse du premier octet : "+tttab);
        System.out.println("avant affectation, le premier objet est : "+tttab[0]);
        System.out.println("avant affectation, le second objet est : "+tttab[1]);
        tttab[0] = new Smurt(7,'Z');
        System.out.println("après affectation, le premier objet est : "+tttab[0]);
        System.out.println("son attribut val vaut : "+tttab[0].val);
        System.out.println("son attribut nom vaut : "+tttab[0].nom);
        System.out.println("son identifiant vaut : "+tttab[0].num);
        /* au passage, comme toute variable statique, cpt vit dans
         * la zone des variables statiques (pas très original),
         * zone mémoire distincte de la pile et du tas
         */
    }
}

class Smurt{
    int val;
    char nom;
    int num;
    static int cpt=0;
    Smurt(int val,char nom){
        this.val=val;
        this.nom=nom;
        this.num=++Smurt.cpt;
    }
}


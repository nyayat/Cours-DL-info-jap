public class Echecs {
    //7.1
    boolean joueur;
    
    Echecs(){
        this.joueur=true;
    }
    
    //7.2
    void jouerTour(Deplacement d, boolean joueur, Plateau p){
        Piece piece=p.getCase(d.getX0(),d.getY0()).getPiece();
        if(piece!=null && piece.estValide(d, p) && piece.getCouleur()==joueur){
            p.remplirCase(d.getX1(), d.getY1(), piece);  //si pièce adversaire, on réécrit dessus
            p.videCase(d.getX0(), d.getY0());
            this.joueur=!this.joueur;
        }
    }
    
    public static void main(String[]a){
        //2.
        //Il n'est pas nécessaire de redéfinir toString() dans les sous-classes,
        //car elle est hérédité de la super-classe.
        Roi r1=new Roi(true);
        Roi r2=new Roi(false);
        /*System.out.println(r1);  //affiche roi
        System.out.println(r2);  //affiche Roi*/
        
        //3.2
        /*Case c1=new Case(true, r1);
        Case c2=new Case(true, r2);
        Case c3=new Case(true, null);
        System.out.println(c1);  //affiche r
        System.out.println(c2);  //affcihe R
        System.out.println(c3);  //affiche " "*/
        
        //4.
        Plateau board=new Plateau(4,4);
        //On place à la main quelques pièces pour les vérifications :
        /*Tour t1=new Tour(false);
        Dame d1=new Dame(false);
        Pion p1=new Pion(false);        
        board.remplirCase(0, 0, t1);
        board.remplirCase(0, 1, d1);
        board.remplirCase(0, 2, r2);
        board.remplirCase(1, 1, p1);
        board.afficher();
        System.out.println("---------");
        board.remplirCase(3, 2, r1);
        board.videCase(0, 0);
        board.afficher();*/
        
        //5.A
        /*Deplacement dep1=new Deplacement(1,5,3,3);
        Deplacement dep2=new Deplacement(1,5,1,2);
        Deplacement dep3=new Deplacement(0,0,1,0);
        Deplacement dep4=new Deplacement(0,0,2,1);
        Deplacement dep5=new Deplacement(5,6,1,5);
        System.out.println(dep1.typeDeplacement());  //affiche d
        System.out.println(dep2.typeDeplacement());  //affiche h
        System.out.println(dep3.typeDeplacement());  //affiche v
        System.out.println(dep4.typeDeplacement());  //affiche c
        System.out.println(dep5.typeDeplacement());  //affiche x*/
        
        //5.B
        /*System.out.println(dep1.dist());  //affiche 2
        System.out.println(dep2.dist());  //affiche 3
        System.out.println(dep3.dist());  //affiche 1
        System.out.println(dep4.dist());  //affiche -1
        System.out.println(dep5.dist());  //affiche -1*/
        
        //6.1
        /*Piece piece1=new Piece(true, "a");
        Piece piece2=new Piece(true, "b");
        board.remplirCase(0, 0, piece1);
        Deplacement dep6=new Deplacement(0,0,5,5);
        System.out.println(piece1.estValide(dep6, board));  //affiche false
        
        board.remplirCase(1, 1, piece2);
        Deplacement dep7=new Deplacement(0,0,1,1);
        System.out.println(piece1.estValide(dep7, board));  //affiche false
        
        Deplacement dep8=new Deplacement(0,0,2,2);
        System.out.println(piece1.estValide(dep8, board));  //affiche true*/
        
        //6.2
        /*Pion p1=new Pion(true);
        Pion p2=new Pion(true);
        Pion p3=new Pion(false);
        Pion p4=new Pion(false);
        board.remplirCase(2, 1, p1);
        board.remplirCase(2, 0, p2);
        board.remplirCase(1, 0, p4);
        board.remplirCase(1, 1, p3);
        Deplacement dep9=new Deplacement(2,0,0,0);
        System.out.println(p2.estValide(dep9, board));  //affiche true
        
        Deplacement dep14=new Deplacement(1,0,3,0);
        System.out.println(p4.estValide(dep14, board));  //affiche true
        
        Deplacement dep10=new Deplacement(1,1,2,1);
        System.out.println(p3.estValide(dep10, board));  //affiche false
        
        Deplacement dep11=new Deplacement(1,1,2,2);
        System.out.println(p3.estValide(dep11, board));  //affiche false
        
        Deplacement dep12=new Deplacement(2,0,1,1);
        System.out.println(p2.estValide(dep12, board));  //affiche true
        
        Deplacement dep13=new Deplacement(1,1,3,3);
        System.out.println(p2.estValide(dep13, board));  //affiche false*/
        
        Dame d1=new Dame(true);
        board.remplirCase(2, 2, d1);
        /*Deplacement dep15=new Deplacement(2,2,1,1);  //mêmes tests pour les 7 autres déplacements possibles
        System.out.println(d1.estValide(dep15, board));*/
        
        /*Pion p3=new Pion(false);
        board.remplirCase(1, 1, p3);
        Deplacement dep16=new Deplacement(2,2,0,0);
        System.out.println(d1.estValide(dep16, board));  //affiche false
        
        Pion p5=new Pion(false);
        board.remplirCase(1, 2, p5);
        Deplacement dep17=new Deplacement(2,2,0,2);
        System.out.println(d1.estValide(dep17, board));  //affiche false*/
        
        //7.3
        // :)
    }
}
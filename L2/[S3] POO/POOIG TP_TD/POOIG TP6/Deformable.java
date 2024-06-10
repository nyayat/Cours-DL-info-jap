//5.
public interface Deformable {
    Figure deformation(double coeffH, double coeffV);
    
    //Rectangle, Ellipse et Triangle implémenteront cette interface.
    /*Le type réel dépendra des coefficients de déformation, mais si pour un
        rectangle, on obtient à la fin longueur=largeur, alors la figure sera
        un carré. Idem carré --> rectangle et ellipse <--> circle.*/
}
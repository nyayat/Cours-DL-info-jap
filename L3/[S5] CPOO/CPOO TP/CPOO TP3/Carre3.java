//3.
public class Carre3 extends Rectangle3{
    //3.2
    Carre3(double x0, double y0, double angle, double len){
        super(x0, y0, angle, len, len);
    }
    
    //3.4
    /*En creant un Carre3 de cette maniere, on utilise deux double
        pour representer la longueur du cote, ce qui n est pas necessaire.
    */
    
    //3.6
    /*Si on ne fait d override des methode setLen1 et setLen2, on risque de
        ne changer qu un cote du carre sans changer le deuxieme cote, ce qui
        donne un rectangle alors que l objet est toujours de la classe Carre3.
    */
    
    //3.7
    void setLen(double x){
        super.setLen1(x);
        super.setLen2(x);
    }
    
    void setLen1(double x){
        setLen(x);
    }
    
    void setLen2(double x){
        setLen(x);
    }
    
    //3.8
    /*Le type dynamique du Rectangle3 r est Carre3, donc en modifiant la longueur
        de r, sa largeur se retrouve aussi modifiee.
        On ne respecte donc pas la specification.
    */
    
    //3.9
    /*L inverse ne marcherait pas non plus, car en modifiant la longueur du carre,
        nous devons le faire pour la largeur aussi, ce qui ne doit pas etre le
        cas pour le rectangle.
    */
}
//4.
interface Shape2D{
    //4.1
    /*      Quadrilatere
              Trapeze
          Parallelogramme
        Losange     Rectangle
               Carre
    
        Cependant, Carre ne peut pas heriter a la fois de Losange et Rectangle...
    */
    
    //4.2
    double perimeter();
    double surface();
    
    default boolean checkInvariants(){
        return true;
    }
}
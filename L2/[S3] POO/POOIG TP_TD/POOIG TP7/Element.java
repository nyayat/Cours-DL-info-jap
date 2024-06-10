//1.
abstract class Element {
    public abstract String getType();
    
    public String toString(){
        return "fichier de type "+getType();
    }
}
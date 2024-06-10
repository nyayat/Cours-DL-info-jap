public class Main {
    public static void main(String[]args){
        Enclos e= new Enclos(5);
        Animal a=new Animal("name", 'f', 5, 52, "species");
        e.ajout(a);
        e.affiche();
    }

}

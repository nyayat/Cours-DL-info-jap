public class Roturier extends Personne implements Cloneable {

    public Roturier(String nom, int argent, int pdv) {
        super(nom, argent, pdv);
    }

    //3.1
    public Roturier clone() throws CloneNotSupportedException {
        return (Roturier) super.clone();
    }

    //4.1
    public boolean equals(Object obj) {
        if(this.getClass() == obj.getClass()) {
            Roturier temp = (Roturier) obj;
            if(temp.nom == this.nom && temp.pdv == this.pdv)
                return true;
        }
        return false;
    }
}

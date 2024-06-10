import java.util.LinkedList;

public class Village implements Cloneable  {
    LinkedList<Roturier> habitants;

    public Village(LinkedList<Roturier> h) {
        habitants = h;
    }

    public int volArgent() {
        int somme = 0;
        for (Roturier h : habitants) {
            int vol = h.getArgent()/2;
            if(h.perte(vol)) somme += vol;
        }
        return somme;
    }

    //3.2
    public Village clone() throws CloneNotSupportedException {
        LinkedList<Roturier> habitantsCopy = new LinkedList<Roturier>();
        Village res = (Village) super.clone();
        for(Roturier r : habitants) {
            habitantsCopy.add(r.clone());
        }
        res.habitants = habitantsCopy;
        return res;
    }

    //4.2
    public boolean equals(Object o) {
        if(o instanceof Village) {
            Village v = (Village) o;
            if(v.habitants.size() == this.habitants.size()) {
                for(int i = 0; i < this.habitants.size(); i++) {
                    if (!(this.habitants.get(i).equals(v.habitants.get(i))))
                        return false;
                }
                return true;
            }
        }
        return false;
    }
}

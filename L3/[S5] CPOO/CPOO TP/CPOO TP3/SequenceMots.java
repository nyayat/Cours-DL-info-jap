//2.
public class SequenceMots implements Sequencable{
    //2.4.
    private MotComparable[] sequence;
    
    SequenceMots(String[] s){
        sequence=new MotComparable[s.length];
        for(int i=0; i<sequence.length; i++) sequence[i]=new MotComparable(s[i]);
    }

    public int longueur(){
        return sequence.length;
    }

    public Comparable get(int i){
        return sequence[i];
    }

    public void echange(int i, int j){
        MotComparable tmp=sequence[i];
        sequence[i]=sequence[j];
        sequence[j]=tmp;
    }
}
public class Paire<K,V> implements AUneClef<K>{
    private K clef;
    private V val;
    
    Paire(K c, V v){
        clef=c;
        val=v;
    }
    
    public K getClef(){
        return this.clef;
    }
    
    V getValeur(){
        return this.val;
    }
    
    public String toString(){
        String res="La valeur "+this.val+" est associée à la clef "+this.clef;
        return res;
    }
    
    Paire<V,K> renverse(){
        return new Paire<V,K>(this.val, this.clef);
    }
}
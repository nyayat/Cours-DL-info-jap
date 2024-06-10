import java.lang.reflect.Array;
import java.util.*;

public class TabSet<E> implements Iterable<E>, Set<E>{
    //2.
    private E[]t;
    
    TabSet(int n){
        t=(E[])new Object[n];
    }
    
    //9.
    TabSet(){
        this(10);
    }
    
    //2.
    public Iterator<E> iterator(){
        return new TabIter();
    }
    
    public class TabIter implements Iterator<E>{  //début classe interne
        private int index;

        TabIter(){
            index=-1;
        }

        public boolean hasNext(){
            int temp=1;  //pour ne pas changer la valeur d'index
                      //index commence à -1 et non pas 0, donc temp commence à 1
            while(index+temp<TabSet.this.size()){
                if(TabSet.this.t[index+temp]!=null) return true;
                temp++;  
            }
            return false;
        }

        public E next(){
            if(this.hasNext()){
                while(TabSet.this.t[index+1]==null) index++;
                index++;
                return TabSet.this.t[index];
            }
            return null;
        }
        
        //4.
        public void add(E e){
            for(int i=index+1; i<TabSet.this.size(); i++){
                if(TabSet.this.t[i]==null){
                    TabSet.this.t[i]=e;
                    return;
                }
            }
            throw new IllegalStateException();
        }
        
        //5.
        public void remove(){
            if(index<0) throw new IllegalStateException();
            TabSet.this.t[index]=null;
        }
    }  //fin classe interne TabIter
    
    //3.
    public boolean contains(Object o){
        for(E elem : this.t){
            if(elem!=null && elem.equals((E)o)) return true;
        }
        return false;
    }
    
    public int size(){
        int res=0;
        for(E elem : this.t) res++;
        return res;
    }
    
    public boolean isEmpty(){
        for(E elem : this.t){
            if(elem!=null) return false;
        }
        return true;
    }
    
    //4.
    public boolean add(E e){
        if(e==null)throw new NullPointerException();
        if(!this.contains(e)){
            //9.
            if(!this.contains(null)){  //plus de place
                E[]nouvT=(E[])Array.newInstance(t.getClass().getComponentType(),
                    this.size()*2);
                for(int i=0; i<this.size(); i++) nouvT[i]=this.t[i];
                this.t=nouvT;
            }
            //4.
            TabIter it=(TabIter)iterator();
            it.add(e);
            return true;
        }
        return false;
    }
    
    //5.
    public boolean remove(Object o){
        if(this.contains(o)){
            TabIter it=(TabIter)iterator();
            while(it.hasNext()){
                E temp=it.next();
                if(temp.equals((E)o)){
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }
    
    public void clear(){
        TabIter it=(TabIter)iterator();
        while(it.hasNext()){
            it.next();
            it.remove();

        }
    }
    
    //6.
    public boolean containsAll(Collection<?>c){
        for(Object test : c){
            if(!this.contains(test)) return false;
        }
        return true;
    }
    
    public boolean addAll(Collection<? extends E>c){
        for(E test : c){
            if(!this.add(test)) return false;
        }
        return true;
    }
    
    public boolean removeAll(Collection<?>c){
        for(Object test : c){  //vérifier la disponibilité de tous les éléments
            if(!this.contains(test)) return false;
        }
        for(Object test : c){  //on peut tout supprimer maintenant
            this.remove(test);
        }
        return true;
    }
    
    public boolean retainAll(Collection<?>c){
        for(Object check : this){
            if(!c.contains(check)){
                if(!this.remove(check)) return false;
            }
        }
        return true;
    }

    //8.
    int realSize(){
        int res=0;
        for(E elem : this){
            if(elem!=null) res++;
        }
        return res;
    }
    
    public Object[] toArray() {
        Object[]res=new Object[this.realSize()];
        int ind=0;
        for(E elem : this){
            if(elem!=null){
                res[ind]=elem;
                ind++;
            }
        }
        return res;
    }

    public <T> T[] toArray(T[] a) {
        int freeInd=0;
        for(int i=0; i<a.length; i++){
            if(a[i]==null) freeInd++;
        }
        if(freeInd>this.realSize()){
            freeInd=0;  //on recycle la variable pour l'utiliser en indice dans t
            for(int i=0; i<a.length; i++){
                if(a[i]==null && freeInd<this.size() && t[freeInd]!=null)
                    a[i]=(T)this.t[freeInd++];
            }
            return a;
        }
        T[] nouvA=(T[])Array.newInstance(a.getClass().getComponentType(),
            a.length-freeInd+this.realSize());
        freeInd=0;  //on recycle la varibale pour les indices de nouvA
        for(int j=0; j<a.length; j++){
            if(a[j]!=null) nouvA[freeInd++]=a[j];
        }
        for(int j=0; j<this.size(); j++){
            if(this.t[j]!=null) nouvA[freeInd++]=(T)this.t[j];
        }
        return nouvA;
    }
    
    
}

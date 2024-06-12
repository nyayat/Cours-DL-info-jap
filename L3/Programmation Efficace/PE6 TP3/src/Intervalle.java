public class Intervalle {
    int start, end;

    public Intervalle(int start, int end){
        this.start=start;
        this.end=end;
    }

    public Intervalle add(int start, int end){
        if(start<this.start) this.start=start;
        if(end>this.end) this.end=end;
        return new Intervalle(this.start, this.end);
    }

    public int getLength(){
        return end - start;
    }
}

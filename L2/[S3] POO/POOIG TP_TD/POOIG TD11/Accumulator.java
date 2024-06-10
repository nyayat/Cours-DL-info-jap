public interface Accumulator<S> {
    //1.1
    void accumulate(S acc);
    S read();
    boolean isOver();
}
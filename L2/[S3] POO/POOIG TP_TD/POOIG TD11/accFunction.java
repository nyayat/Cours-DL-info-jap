public interface accFunction<S, T> {
    //1.2
    S apply(S acc, S ext, T donnee);
}
public interface MonObserver {
    void update(MonObservable o, Object arg);
    
    void add(MonObserver o);
}
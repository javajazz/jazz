package jazz;

public interface EventHandler<M> {

    void on(M m, Event e);
}

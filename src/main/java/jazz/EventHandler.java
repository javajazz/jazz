package jazz;

public interface EventHandler<M> {

  void on(final M m, final Event e);
}

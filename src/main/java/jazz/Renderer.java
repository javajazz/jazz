package jazz;

public interface Renderer<M> {

    Picture render(final M model);
}

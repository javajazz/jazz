package jazz;

/**
 * A Renderer takes a model of type M and transforms it into a {@link Picture}.
 * 
 * @author Julian Fleischer
 */
public interface Renderer<M> {

  Picture render(final M model);
}

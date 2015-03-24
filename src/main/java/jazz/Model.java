package jazz;

public interface Model {

    void update(final double time, final double delta);

    void on(final Event e);

    Picture getPicture();
}

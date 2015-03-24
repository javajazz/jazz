package jazz;

public interface UpdateHandler<M> {

    void update(final M model, final double time, final double delta);
}

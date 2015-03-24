package jazz;

public interface UpdateHandler<M> {

    void update(M model, double time, double delta);
}

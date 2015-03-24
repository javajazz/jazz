package jazz;

final public class DelegatingWorld<M> extends World {

    final private UpdateHandler<M> updateHandler;
    final private EventHandler<M> eventHandler;
    final private Renderer<M> renderer;

    final private M model;

    public DelegatingWorld(M m, Renderer<M> r, UpdateHandler<M> u,
            EventHandler<M> e) {
        this.updateHandler = u;
        this.eventHandler = e;
        this.renderer = r;
        this.model = m;
    }

    @Override
    final public void update(double time, double delta) {
        updateHandler.update(model, time, delta);
    }

    @Override
    final public void on(Event e) {
        eventHandler.on(model, e);
    }

    @Override
    final public Picture getPicture() {
        return renderer.render(model);
    }

}

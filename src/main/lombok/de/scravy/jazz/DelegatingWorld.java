package de.scravy.jazz;

import de.scravy.jazz.Event;
import de.scravy.jazz.EventHandler;
import de.scravy.jazz.Picture;
import de.scravy.jazz.Renderer;
import de.scravy.jazz.UpdateHandler;

final public class DelegatingWorld<M> extends World {

  final private UpdateHandler<M> updateHandler;
  final private EventHandler<M> eventHandler;
  final private Renderer<M> renderer;

  final private M model;

  public DelegatingWorld(final M m, final Renderer<M> r,
      final UpdateHandler<M> u,
      final EventHandler<M> e) {
    this.updateHandler = u;
    this.eventHandler = e;
    this.renderer = r;
    this.model = m;
  }

  @Override
  final public void update(final double time, final double delta) {
    updateHandler.update(model, time, delta);
  }

  @Override
  final public void on(final Event e) {
    eventHandler.on(model, e);
  }

  @Override
  final public Picture getPicture() {
    return renderer.render(model);
  }

}

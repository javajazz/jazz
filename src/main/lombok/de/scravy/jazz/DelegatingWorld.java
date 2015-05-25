package de.scravy.jazz;

import lombok.AllArgsConstructor;

@AllArgsConstructor
final public class DelegatingWorld<M> extends World {

  private M model;

  final private Renderer<M> renderer;
  final private UpdateHandler<M> updateHandler;
  final private EventHandler<M> eventHandler;

  @Override
  final public void update(final double time, final double delta) {
    model = updateHandler.update(model, time, delta);
  }

  @Override
  final public void on(final Event e) {
    model = eventHandler.on(model, e);
  }

  @Override
  final public Picture getPicture() {
    return renderer.render(model);
  }

}

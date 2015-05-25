package de.scravy.jazz;

public interface EventHandler<M> {

  M on(final M m, final Event e);
}

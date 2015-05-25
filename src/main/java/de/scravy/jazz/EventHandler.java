package de.scravy.jazz;

import jazz.Event;

public interface EventHandler<M> {

  void on(final M m, final Event e);
}

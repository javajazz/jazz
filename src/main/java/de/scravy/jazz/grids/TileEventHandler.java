package de.scravy.jazz.grids;

import de.scravy.jazz.Event;
import de.scravy.jazz.annotation.Experimental;

@Experimental
public interface TileEventHandler<T> {

  void on(final Event ev, final T tile);

}

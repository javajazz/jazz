package de.scravy.jazz.grids;

import de.scravy.jazz.Event;
import de.scravy.jazz.annotation.Experimental;
import de.scravy.jazz.grids.TileEventHandler;

/**
 * A {@link TileEventHandler} that simply does nothing.
 *
 * @author Julian Fleischer
 *
 * @param <T>
 */
@Experimental
public class DefaultTileEventHandler<T> implements TileEventHandler<T> {

  @Override
  public void on(final Event ev, final T tile) {

  }
}

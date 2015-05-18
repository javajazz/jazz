package jazz.grids;

import jazz.Event;

public interface TileEventHandler<T> {

  void on(final Event ev, final T tile);

}

package jazz.grids;

import jazz.Event;

public interface TileEventHandler<T> {

    void on(Event ev, T tile);

}

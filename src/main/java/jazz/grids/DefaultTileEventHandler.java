package jazz.grids;

import jazz.Event;

/**
 * A {@link TileEventHandler} that simply does nothing.
 * 
 * @author Julian Fleischer
 *
 * @param <T>
 */
public class DefaultTileEventHandler<T> implements TileEventHandler<T> {

    @Override
    public void on(Event ev, T tile) {

    }
}

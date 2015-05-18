package jazz.grids;

import jazz.Picture;

public interface TileDecorator<T> {

  Picture decorate(final T tile, final Picture picture);
}

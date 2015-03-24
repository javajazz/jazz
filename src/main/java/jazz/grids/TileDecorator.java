package jazz.grids;

import jazz.Picture;

public interface TileDecorator<T> {

    Picture decorate(T tile, Picture picture);
}

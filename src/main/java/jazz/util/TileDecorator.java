package jazz.util;

import jazz.Picture;

public interface TileDecorator<T> {

	Picture decorate(T tile, Picture picture);
}

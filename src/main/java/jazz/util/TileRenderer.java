package jazz.util;

import jazz.Picture;

public interface TileRenderer<T> {

	Picture render(T tile, double x, double y, double width, double height);
}

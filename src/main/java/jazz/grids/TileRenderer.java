package jazz.grids;

import jazz.Picture;

public interface TileRenderer<T> {

    Picture render(final T tile, final double x, final double y,
            final double width, final double height);
}

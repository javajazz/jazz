package de.scravy.jazz.grids;

import de.scravy.jazz.Picture;
import de.scravy.jazz.annotation.Experimental;

@Experimental
public interface TileRenderer<T> {

  Picture render(final T tile, final double x, final double y,
      final double width, final double height);
}

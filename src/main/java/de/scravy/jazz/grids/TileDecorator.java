package de.scravy.jazz.grids;

import jazz.Picture;
import de.scravy.jazz.annotation.Experimental;

@Experimental
public interface TileDecorator<T> {

  Picture decorate(final T tile, final Picture picture);
}

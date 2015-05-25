package de.scravy.jazz.grids;

import de.scravy.jazz.Picture;
import de.scravy.jazz.annotation.Experimental;

@Experimental
public interface TileDecorator<T> {

  Picture decorate(final T tile, final Picture picture);
}

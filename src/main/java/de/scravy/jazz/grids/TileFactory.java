package de.scravy.jazz.grids;

import de.scravy.jazz.annotation.Experimental;

/**
 *
 * @since 1.0.0
 * @author Julian Fleischer
 *
 * @param <T>
 */
@Experimental
public interface TileFactory<T> {

  T createTile(final int x, final int y);
}

package jazz.grids;

import de.scravy.jazz.annotation.Experimental;
import de.scravy.jazz.grids.TileFactory;

/**
 * A {@link TileFactory} that always returns the same tile or null.
 *
 * @author Julian Fleischer
 *
 * @param <T>
 *          The type of the tile.
 */
@Experimental
public class DefaultTileFactory<T> implements TileFactory<T> {

  private final T defaultTile;

  /**
   * Creates a tile factory that always returns null.
   */
  public DefaultTileFactory() {
    this.defaultTile = null;
  }

  /**
   * Creates a tile factory that always returns the same tile.
   *
   * @param tile
   *          The default tile that is returned always.
   */
  public DefaultTileFactory(final T tile) {
    this.defaultTile = tile;
  }

  @Override
  public T createTile(final int x, final int y) {
    return this.defaultTile;
  }

}

package jazz.util;

/**
 * A {@link TileFactory} that always returns the same tile or null.
 * 
 * @author Julian Fleischer
 * 
 * @param <T>
 *            The type of the tile.
 */
public class DefaultTileFactory<T> implements TileFactory<T> {

	private final T defaultTile;

	/**
	 * Creates a tile factory that always returns null.
	 */
	public DefaultTileFactory() {
		defaultTile = null;
	}

	/**
	 * Creates a tile factory that always returns the same tile.
	 * 
	 * @param tile
	 *            The default tile that is returned always.
	 */
	public DefaultTileFactory(T tile) {
		this.defaultTile = tile;
	}

	@Override
	public T createTile(int x, int y) {
		return defaultTile;
	}

}

package jazz.grids;

public interface TileFactory<T> {

    T createTile(final int x, final int y);
}

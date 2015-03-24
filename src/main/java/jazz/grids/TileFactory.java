package jazz.grids;

public interface TileFactory<T> {

    T createTile(int x, int y);
}

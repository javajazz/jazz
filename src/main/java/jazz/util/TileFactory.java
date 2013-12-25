package jazz.util;

public interface TileFactory<T> {

	T createTile(int x, int y);
}

package jazz.util;

import jazz.Event;
import jazz.Picture;
import jazz.Vector;
import jazz.pictures.mutable.Pictures;

public class RectangularGrid<T> extends AbstractGrid<RectangularGrid<T>> {

	private final int gridWidth;
	private final int gridHeight;

	private final TileEventHandler<T> tileHandler;
	private final TileRenderer<T> tileRenderer;

	private final T[][] tiles;

	@SuppressWarnings("unchecked")
	public RectangularGrid(
			int width, int height,
			double tileWidth, double tileHeight,
			TileFactory<T> tileFactory,
			TileEventHandler<T> tileHandler,
			TileRenderer<T> tileRenderer) {

		super(width * tileWidth, height * tileHeight);

		gridWidth = width;
		gridHeight = height;

		this.tileHandler = tileHandler;
		this.tileRenderer = tileRenderer;

		this.tiles = (T[][]) new Object[gridWidth][gridHeight];

		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				tiles[x][y] = tileFactory.createTile(x, y);
			}
		}
	}

	public boolean on(Event e) {

		Vector c = getLowerLeftCorner();
		Vector p = e.getPosition();
		int x = (int) (p.x - c.x);
		int y = (int) (p.y - c.y);

		if (x >= 0 && y >= 0 && x < width && y < height) {

			x /= width / gridWidth;
			y /= height / gridHeight;

			tileHandler.on(e, tiles[x][y]);
			return true;
		}
		return false;
	}

	@Override
	Picture getPicture() {
		
		Pictures pictures = new Pictures();
		double tileWidth = getWidth() / gridWidth;
		double tileHeight = getHeight() / gridHeight;
		Vector p = getLowerLeftCorner();
		double posX = p.x + tileWidth / 2;
		double upperY = p.y + tileHeight / 2;

		for (int x = 0; x < gridWidth; x++) {
			double posY = upperY;
			for (int y = 0; y < gridHeight; y++) {
				pictures.add(tileRenderer.render(
						tiles[x][y], posX, posY, tileWidth, tileHeight));
				posY += tileHeight;
			}
			posX += tileWidth;
		}
		return pictures;
	}
}

package jazz.util;

import jazz.Pictures;
import jazz.Point;
import jazz.UnmodifieablePictures;

public class HexagonGrid<T> {

	public static enum Mode {
		A, B, C, D
	}

	private final int gridWidth;
	private final int gridHeight;
	private final T[][] grid;
	private final int size;
	private final Pictures pictures = new Pictures();
	private final boolean orientation;
	private final double side;

	private double height = 0;
	private double width = 0;
	private double posX = 0;
	private double posY = 0;

	public HexagonGrid(int gridWidth, int gridHeight,
			double side, boolean orientation) {
		
		this.orientation = orientation;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.side = side;

		@SuppressWarnings("unchecked")
		T[][] grid = (T[][]) new Object[gridWidth][gridHeight];

		this.grid = grid;
		this.size = gridWidth * gridHeight;

		if (this.orientation) {
			this.width = gridWidth * Math.sqrt(3) * this.side;
			this.height = gridHeight * 2 * this.side;
		} else {
			this.width = gridWidth * 2 * this.side;
			this.height = gridHeight * Math.sqrt(3) * this.side;
		}
	}
	
	void makePictures() {
		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {
				
			}
		}
	}

	public int getSize() {
		return size;
	}

	public HexagonGrid<T> setTile(int x, int y, T tile) {
		grid[x][y] = tile;
		return this;
	}

	public T getTile(int x, int y) {
		return grid[x][y];
	}

	public T getTileAt(double x, double y) {
		return null;
	}

	public T getCoordsFor(double x, double y) {
		return null;
	}

	public T getCoordsFor(Point p) {
		return getCoordsFor(p.x, p.y);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public HexagonGrid<T> setCenterX(double x) {
		posX = x;
		return this;
	}

	public HexagonGrid<T> setCenterY(double y) {
		posY = y;
		return this;
	}

	public HexagonGrid<T> setCenter(double x, double y) {
		setCenterX(x);
		setCenterY(y);
		return this;
	}

	public HexagonGrid<T> setCenter(Point p) {
		setCenter(p.x, p.y);
		return this;
	}

	public double getX() {
		return posX;
	}

	public double getY() {
		return posY;
	}

	public UnmodifieablePictures getPicture() {
		return pictures.getImmutable();
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}
}

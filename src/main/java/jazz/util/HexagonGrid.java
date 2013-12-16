package jazz.util;

import jazz.pictures.ImmutablePictures;
import jazz.pictures.Pictures;

public class HexagonGrid<T> {

	public static enum Mode {
		A, B, C, D
	}
	
	private final Mode mode;
	private final int x;
	private final int y;
	private final T[][] grid;
	private final int size;
	private final Pictures p = new Pictures();
	
	public HexagonGrid(Mode mode, int x, int y) {
		this.mode = mode;
		this.x = x;
		this.y = y;
		
		@SuppressWarnings("unchecked")
		T[][] grid = (T[][]) new Object[x][y];
		
		this.grid = grid;
		this.size = x * y;
	}
	
	public int getSize() {
		return size;
	}
	
	public ImmutablePictures getPicture() {
		return p.getImmutable();
	}
}

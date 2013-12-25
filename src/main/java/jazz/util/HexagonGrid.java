package jazz.util;

import jazz.Picture;
import jazz.pictures.UnmodifieablePictures;
import jazz.pictures.mutable.Pictures;

public class HexagonGrid<T> extends AbstractGrid<HexagonGrid<T>> {

	public static enum Mode {
		/**
		 * First row is offset (even rows are indented) and has one cell less
		 * than the second (odd rows).
		 * 
		 * <pre>
		 *   ^   ^
		 *  / \ / \
		 * v   v   v
		 * |2,1|2,2|
		 * ^   ^   ^
		 *  \ / \ / 
		 *   v   v
		 *   |1,1|
		 *   ^   ^
		 *    \ /
		 *     v
		 * </pre>
		 */
		VERT_FST_OFFSET,

		/**
		 * First row is offset (even rows are indented), and all rows have the
		 * same amount of cells.
		 * 
		 * <pre>
		 *   ^   ^
		 *  / \ / \
		 * v   v   v
		 * |2,1|2,2|
		 * ^   ^   ^
		 *  \ / \ / \ 
		 *   v   v   v
		 *   |1,1|1,2|
		 *   ^   ^   ^
		 *    \ / \ /
		 *     v   v
		 * </pre>
		 */
		VERT_FST_OFFSET_FULL,

		/**
		 * <pre>
		 *     ^
		 *    / \
		 *   v   v
		 *   |2,1|
		 *   ^   ^
		 *  / \ / \
		 * v   v   v
		 * |1,1|1,2|
		 * ^   ^   ^
		 *  \ / \ /
		 *   v   v
		 * </pre>
		 */
		VERT_SND_OFFSET,

		/**
		 * <pre>
		 *     ^   ^
		 *    / \ / \
		 *   v   v   v
		 *   |2,1|2,2|
		 *   ^   ^   ^
		 *  / \ / \ /
		 * v   v   v
		 * |1,1|1,2|
		 * ^   ^   ^
		 *  \ / \ /
		 *   v   v
		 * </pre>
		 */
		VERT_SND_OFFSET_FULL,

		/**
		 * <pre>
		 *   ___       ___
		 *  /   \     /   \
		 * / 2,1 \___/ 2,2 \
		 * \     /   \     /
		 *  \___/ 1,1 \___/
		 *      \     /
		 *       \___/
		 * </pre>
		 */
		HOR_FST_OFFSET,

		/**
		 * <pre>
		 *   ___       ___
		 *  /   \     /   \
		 * / 2,1 \___/ 2,2 \___
		 * \     /   \     /   \
		 *  \___/ 1,1 \___/ 1,2 \
		 *      \     /   \     /
		 *       \___/     \___/
		 * </pre>
		 */
		HOR_FST_OFFSET_FULL,

		/**
		 * <pre>
		 *        ___
		 *       /   \
		 *   ___/ 2,1 \___
		 *  /   \     /   \
		 * / 1,1 \___/ 1,2 \
		 * \     /   \     /
		 *  \___/     \___/
		 * </pre>
		 */
		HOR_SND_OFFSET,

		/**
		 * <pre>
		 *        ___       ___
		 *       /   \     /   \
		 *   ___/ 2,1 \___/ 2,2 \
		 *  /   \     /   \     /
		 * / 1,1 \___/ 1,2 \___/
		 * \     /   \     /
		 *  \___/     \___/
		 * </pre>
		 */
		HOR_SND_OFFSET_FULL
	}

	private final int gridWidth;
	private final int gridHeight;

	private final TileEventHandler<T> tileHandler;
	private final TileRenderer<T> tileRenderer;

	private final T[][] tiles;

	@SuppressWarnings("unchecked")
	public HexagonGrid(int gridWidth, int gridHeight, double side,
			TileFactory<T> tileFactory,
			TileEventHandler<T> tileHandler,
			TileRenderer<T> tileRenderer) {

		super(400, 300);

		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;

		this.tileHandler = tileHandler;
		this.tileRenderer = tileRenderer;

		this.tiles = (T[][]) new Object[gridWidth][gridHeight];

		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				tiles[x][y] = tileFactory.createTile(x, y);
			}
		}
	}

	@Override
	Picture getPicture() {
		Pictures pictures = new Pictures();

		return new UnmodifieablePictures(pictures);
	}
}

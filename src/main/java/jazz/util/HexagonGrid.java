package jazz.util;

import jazz.Event;
import jazz.Picture;
import jazz.pictures.UnmodifieablePictures;
import jazz.pictures.mutable.Pictures;

public class HexagonGrid<T> extends AbstractGrid<HexagonGrid<T>, T> {

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
		 * 2x2:
		 * 
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
		 * 2x2:
		 * 
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
		 * 2x2:
		 * 
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
		 * 2x2:
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
		 * 2x2:
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
		 * 2x2:
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
		 * 2x2:
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

	private final Mode gridMode;
	private final T[][] tiles;

	@SuppressWarnings("unchecked")
	public HexagonGrid(int gridWidth, int gridHeight, double side,
			Mode gridMode,
			TileFactory<T> tileFactory,
			TileEventHandler<T> tileHandler,
			TileRenderer<T> tileRenderer) {

		super(tileHandler, tileRenderer, 400, 300);

		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.gridMode = gridMode;
		this.tiles = (T[][]) new Object[gridWidth][gridHeight];

		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				tiles[x][y] = tileFactory.createTile(x, y);
			}
		}
	}

	@Override
	public Picture getPicture() {
		Pictures pictures = new Pictures();

		switch (gridMode) {
		case HOR_FST_OFFSET:
			
			break;
		case HOR_FST_OFFSET_FULL:
			
			break;
		case HOR_SND_OFFSET:
			
			break;
		case HOR_SND_OFFSET_FULL:
			
			break;
		case VERT_FST_OFFSET:
			
			break;
		case VERT_FST_OFFSET_FULL:
			
			break;
		case VERT_SND_OFFSET:
			
			break;
		case VERT_SND_OFFSET_FULL:
			
			break;
		}
		
		return new UnmodifieablePictures(pictures);
	}

	@Override
	public void on(Event ev) {
		
	}
}

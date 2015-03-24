package jazz.grids;

import jazz.Event;
import jazz.Picture;
import jazz.Vector;
import jazz.pictures.UnmodifieablePictures;
import jazz.pictures.mutable.Pictures;

public class RectangularGrid<T> extends AbstractGrid<RectangularGrid<T>, T> {

    private final int gridWidth;
    private final int gridHeight;

    private final T[][] tiles;

    @SuppressWarnings("unchecked")
    public RectangularGrid(
            final int width, final int height,
            final double tileWidth, final double tileHeight,
            final TileFactory<T> tileFactory,
            final TileEventHandler<T> tileHandler,
            final TileRenderer<T> tileRenderer) {

        super(tileHandler, tileRenderer, width * tileWidth, height * tileHeight);

        gridWidth = width;
        gridHeight = height;

        this.tiles = (T[][]) new Object[gridWidth][gridHeight];

        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                tiles[x][y] = tileFactory.createTile(x, y);
            }
        }
    }

    @Override
    public void on(final Event e) {

        final Vector c = getLowerLeftCorner();
        final Vector p = e.getPosition();
        int x = (int) (p.x - c.x);
        int y = (int) (p.y - c.y);

        if (x >= 0 && y >= 0 && x < width && y < height) {

            x /= width / gridWidth;
            y /= height / gridHeight;

            tileHandler.on(e, tiles[x][y]);
        }
    }

    public T getTileAt(final int x, final int y) {
        return tiles[x][y];
    }

    @Override
    public Picture getPicture() {

        final Pictures pictures = new Pictures();
        final double tileWidth = getWidth() / gridWidth;
        final double tileHeight = getHeight() / gridHeight;

        final Vector p = getLowerLeftCorner();
        double posX = p.x + tileWidth / 2;
        final double upperY = p.y + tileHeight / 2;

        for (int x = 0; x < gridWidth; x++) {
            double posY = upperY;
            for (int y = 0; y < gridHeight; y++) {
                pictures.add(tileRenderer.render(
                        tiles[x][y], posX, posY, tileWidth, tileHeight));
                posY += tileHeight;
            }
            posX += tileWidth;
        }
        return new UnmodifieablePictures(pictures);
    }
}

package jazz.grids;

import jazz.Event;
import jazz.Picture;
import jazz.Vector;
import jazz.pictures.mutable.Pictures;
import jazz.pictures.mutable.Polygon;

public class HexagonGrid<T> {

    private final TileEventHandler<T> tileHandler;
    private final TileDecorator<T> tileDecorator;

    private final int height;
    private final int width;

    private final double a, w, h, w1, w2, h1, h2;

    private final T[][] tiles;

    @SuppressWarnings("unchecked")
    public HexagonGrid(
            final TileFactory<T> tileFactory,
            final TileDecorator<T> tileDecorator,
            final TileEventHandler<T> tileHandler,
            final double a, final int width, final int height) {

        this.tileDecorator = tileDecorator;
        this.tileHandler = tileHandler;

        this.width = width;
        this.height = height;

        this.a = a;

        tiles = (T[][]) new Object[width][height];

        w1 = Math.sqrt(3) * a;
        w2 = w1 / 2;

        h1 = a / 2;
        h2 = h1 * 3;

        w = width * w1 + w2;
        h = height * h2 + h1;
    }

    public Picture getPicture() {

        final Pictures pictures = new Pictures();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                double x = i * w1 - w / 2;
                final double y = j * h2 - h / 2;

                if (j % 2 != 0) {
                    x += w2;
                }

                final Polygon p = new Polygon(
                        new Vector(x + w2, y),
                        new Vector(x + w1, y + h1),
                        new Vector(x + w1, y + h2),
                        new Vector(x + w2, y + 2 * a),
                        new Vector(x, y + h2),
                        new Vector(x, y + h1)
                        );

                pictures.add(tileDecorator.decorate(tiles[i][j], p));
            }
        }
        return pictures;
    }

    public void on(final Event ev) {

    }

    public void setTileAt(final int x, final int y, final T tile) {
        tiles[x][y] = tile;
    }

    public void setTileAt(final TilePos p, final T tile) {
        tiles[p.x][p.y] = tile;
    }

    public T getTileAt(final int x, final int y) {
        return tiles[x][y];
    }

    public T getTileAt(final TilePos p) {
        return tiles[p.x][p.y];
    }

    public TilePos topLeft(final TilePos p) {
        if (p.y + 1 >= height) {
            return null;
        } else if (p.y % 2 == 0) {
            return p.x == 0 ? null : new TilePos(p.x - 1, p.y + 1);
        } else {
            return new TilePos(p.x, p.y + 1);
        }
    }

    public TilePos topRight(final TilePos p) {
        if (p.y + 1 >= height) {
            return null;
        } else if (p.y % 2 == 0) {
            return new TilePos(p.x, p.y + 1);
        } else {
            return p.x + 1 >= width ? null : new TilePos(p.x + 1, p.y + 1);
        }
    }

    public TilePos bottomLeft(final TilePos p) {
        if (p.y == 0) {
            return null;
        } else if (p.y % 2 == 0) {
            return p.x == 0 ? null : new TilePos(p.x - 1, p.y - 1);
        } else {
            return new TilePos(p.x, p.y - 1);
        }
    }

    public TilePos bottomRight(final TilePos p) {
        if (p.y == 0) {
            return null;
        } else if (p.y % 2 == 0) {
            return new TilePos(p.x, p.y - 1);
        } else {
            return p.x + 1 >= width ? null : new TilePos(p.x + 1, p.y - 1);
        }
    }

    public TilePos left(final TilePos p) {
        return p.x == 0 ? null : new TilePos(p.x - 1, p.y);
    }

    public TilePos right(final TilePos p) {
        return p.x + 1 >= width ? null : new TilePos(p.x + 1, p.y);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

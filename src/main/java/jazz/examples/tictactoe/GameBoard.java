package jazz.examples.tictactoe;

import jazz.Color;
import jazz.Event;
import jazz.Picture;
import jazz.World;
import jazz.grids.RectangularGrid;
import jazz.grids.TileEventHandler;
import jazz.grids.TileFactory;
import jazz.grids.TileRenderer;
import jazz.pictures.mutable.Circle;

public class GameBoard extends World {

    private boolean gameOver;
    private boolean turn;
    private Tile currentTile;

    private static class Tile {
        char value = '\0';
        boolean highlight;

        void reset() {
            value = '\0';
            highlight = false;
        }
    }

    @Override
    public void on(Event ev) {
        grid.on(ev);
    }

    @Override
    public Picture getPicture() {
        return grid.getPicture();
    }

    private final TileFactory<Tile> tileFactory = new TileFactory<Tile>() {
        @Override
        public Tile createTile(int x, int y) {
            return new Tile();
        }
    };

    private final TileEventHandler<Tile> tileEventHandler = new TileEventHandler<Tile>() {
        @Override
        public void on(Event ev, Tile tile) {
            if (gameOver) {
                if (ev.getType() == Event.Type.CLICK) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            grid.getTileAt(i, j).reset();
                        }
                    }
                    gameOver = false;
                }
            } else if (ev.getType() == Event.Type.CLICK && tile.value == '\0') {
                tile.value = turn ? 'o' : 'x';
                turn = !turn;

                check(0, 0, 1, 1, 2, 2);
                check(0, 2, 1, 1, 2, 0);

                check(0, 0, 0, 1, 0, 2);
                check(1, 0, 1, 1, 1, 2);
                check(2, 0, 2, 1, 2, 2);

                check(0, 0, 1, 0, 2, 0);
                check(0, 1, 1, 1, 2, 1);
                check(0, 2, 1, 2, 2, 2);

                if (grid.getTileAt(0, 0).value != '\0'
                        && grid.getTileAt(0, 1).value != '\0'
                        && grid.getTileAt(0, 2).value != '\0'
                        && grid.getTileAt(1, 0).value != '\0'
                        && grid.getTileAt(1, 1).value != '\0'
                        && grid.getTileAt(1, 2).value != '\0'
                        && grid.getTileAt(2, 0).value != '\0'
                        && grid.getTileAt(2, 1).value != '\0'
                        && grid.getTileAt(2, 2).value != '\0') {
                    gameOver = true;
                }
            } else if (ev.getType() == Event.Type.MOUSE_MOVE) {
                currentTile = tile;
            }
        }
    };

    private final TileRenderer<Tile> tileRenderer = new TileRenderer<Tile>() {
        @Override
        public Picture render(Tile tile, double x, double y,
                double width, double height) {
            final Picture picture;
            if (tile.value != '\0') {
                picture = (tile.value == 'x' ? new Cross(
                        width * 0.8)
                        : new Circle(width * 0.4)).filled(true);
            } else if (tile == currentTile) {
                picture = (turn ? new Circle(width * 0.4)
                        : new Cross(
                                width * 0.8));
            } else {
                return null;
            }
            return picture.translate(x, y).color(
                    tile.highlight ? Color.RED : Color.BLACK);
        }
    };

    private final RectangularGrid<Tile> grid = new RectangularGrid<>(
            3, 3, 200, 200, tileFactory, tileEventHandler, tileRenderer);

    private void check(int x0, int y0, int x1, int y1, int x2, int y2) {
        if (grid.getTileAt(x0, y0).value != '\0'
                && grid.getTileAt(x0, y0).value == grid.getTileAt(x1, y1).value
                && grid.getTileAt(x1, y1).value == grid.getTileAt(x2, y2).value) {
            grid.getTileAt(x0, y0).highlight = true;
            grid.getTileAt(x1, y1).highlight = true;
            grid.getTileAt(x2, y2).highlight = true;

            gameOver = true;
        }
    }
}

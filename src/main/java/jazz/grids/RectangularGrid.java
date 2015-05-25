package jazz.grids;

import jazz.pictures.UnmodifieablePictures;
import jazz.pictures.mutable.Pictures;
import de.scravy.jazz.Event;
import de.scravy.jazz.Picture;
import de.scravy.jazz.Vector;
import de.scravy.jazz.annotation.Experimental;
import de.scravy.jazz.grids.TileEventHandler;
import de.scravy.jazz.grids.TileFactory;
import de.scravy.jazz.grids.TileRenderer;

@Experimental
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

    this.gridWidth = width;
    this.gridHeight = height;

    this.tiles = (T[][]) new Object[this.gridWidth][this.gridHeight];

    for (int x = 0; x < this.gridWidth; x++) {
      for (int y = 0; y < this.gridHeight; y++) {
        this.tiles[x][y] = tileFactory.createTile(x, y);
      }
    }
  }

  @Override
  public void on(final Event e) {

    final Vector c = getLowerLeftCorner();
    final Vector p = e.getPosition();
    int x = (int) (p.getX() - c.getX());
    int y = (int) (p.getY() - c.getY());

    if (x >= 0 && y >= 0 && x < this.width && y < this.height) {

      x /= this.width / this.gridWidth;
      y /= this.height / this.gridHeight;

      this.tileHandler.on(e, this.tiles[x][y]);
    }
  }

  public T getTileAt(final int x, final int y) {
    return this.tiles[x][y];
  }

  @Override
  public Picture getPicture() {

    final Pictures pictures = new Pictures();
    final double tileWidth = getWidth() / this.gridWidth;
    final double tileHeight = getHeight() / this.gridHeight;

    final Vector p = getLowerLeftCorner();
    double posX = p.getX() + tileWidth / 2;
    final double upperY = p.getY() + tileHeight / 2;

    for (int x = 0; x < this.gridWidth; x++) {
      double posY = upperY;
      for (int y = 0; y < this.gridHeight; y++) {
        pictures.add(this.tileRenderer.render(
            this.tiles[x][y], posX, posY, tileWidth, tileHeight));
        posY += tileHeight;
      }
      posX += tileWidth;
    }
    return new UnmodifieablePictures(pictures);
  }
}

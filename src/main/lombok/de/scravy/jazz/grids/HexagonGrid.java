package de.scravy.jazz.grids;

import de.scravy.jazz.Event;
import de.scravy.jazz.Picture;
import de.scravy.jazz.Vector;
import de.scravy.jazz.annotation.Experimental;
import de.scravy.jazz.grids.TileDecorator;
import de.scravy.jazz.grids.TileEventHandler;
import de.scravy.jazz.grids.TileFactory;
import de.scravy.jazz.pictures.mutable.Pictures;
import de.scravy.jazz.pictures.mutable.Polygon;

@Experimental
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

    this.tiles = (T[][]) new Object[width][height];

    this.w1 = Math.sqrt(3) * a;
    this.w2 = this.w1 / 2;

    this.h1 = a / 2;
    this.h2 = this.h1 * 3;

    this.w = width * this.w1 + this.w2;
    this.h = height * this.h2 + this.h1;
  }

  public Picture getPicture() {

    final Pictures pictures = new Pictures();

    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {

        double x = i * this.w1 - this.w / 2;
        final double y = j * this.h2 - this.h / 2;

        if (j % 2 != 0) {
          x += this.w2;
        }

        final Polygon p = new Polygon(
            new Vector(x + this.w2, y),
            new Vector(x + this.w1, y + this.h1),
            new Vector(x + this.w1, y + this.h2),
            new Vector(x + this.w2, y + 2 * this.a),
            new Vector(x, y + this.h2),
            new Vector(x, y + this.h1)
            );

        pictures.add(this.tileDecorator.decorate(this.tiles[i][j], p));
      }
    }
    return pictures;
  }

  public void on(final Event ev) {

  }

  public void setTileAt(final int x, final int y, final T tile) {
    this.tiles[x][y] = tile;
  }

  public void setTileAt(final TilePos p, final T tile) {
    this.tiles[p.x][p.y] = tile;
  }

  public T getTileAt(final int x, final int y) {
    return this.tiles[x][y];
  }

  public T getTileAt(final TilePos p) {
    return this.tiles[p.x][p.y];
  }

  public TilePos topLeft(final TilePos p) {
    if (p.y + 1 >= this.height) {
      return null;
    } else if (p.y % 2 == 0) {
      return p.x == 0 ? null : new TilePos(p.x - 1, p.y + 1);
    } else {
      return new TilePos(p.x, p.y + 1);
    }
  }

  public TilePos topRight(final TilePos p) {
    if (p.y + 1 >= this.height) {
      return null;
    } else if (p.y % 2 == 0) {
      return new TilePos(p.x, p.y + 1);
    } else {
      return p.x + 1 >= this.width ? null : new TilePos(p.x + 1, p.y + 1);
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
      return p.x + 1 >= this.width ? null : new TilePos(p.x + 1, p.y - 1);
    }
  }

  public TilePos left(final TilePos p) {
    return p.x == 0 ? null : new TilePos(p.x - 1, p.y);
  }

  public TilePos right(final TilePos p) {
    return p.x + 1 >= this.width ? null : new TilePos(p.x + 1, p.y);
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }
}

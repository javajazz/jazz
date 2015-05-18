package jazz.grids;

public class TilePos {

  public final int x;
  public final int y;

  public TilePos(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return String.format("(%d,%d)", x, y);
  }
}

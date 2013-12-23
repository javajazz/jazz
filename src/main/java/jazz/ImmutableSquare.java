package jazz;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

final class ImmutableSquare extends ImmutableAbstractPicture<ImmutableSquare> {

  public ImmutableSquare(double side) {
    super(new Rectangle2D.Double(0, 0, side, side));
  }

  private ImmutableSquare(Shape shape) {
    super(shape);
  }

  @Override
  public ImmutableSquare clone() {
    return doClone(new ImmutableSquare(shape));
  }

}

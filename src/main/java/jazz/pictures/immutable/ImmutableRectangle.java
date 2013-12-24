package jazz.pictures.immutable;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import jazz.pictures.ImmutableAbstractPicture;

final class ImmutableRectangle extends
    ImmutableAbstractPicture<ImmutableRectangle> {

  public ImmutableRectangle(double width, double height) {
    super(new Rectangle2D.Double(0, 0, width, height));
  }

  private ImmutableRectangle(Shape shape) {
    super(shape);
  }

  @Override
  public ImmutableRectangle clone() {
    return doClone(new ImmutableRectangle(shape));
  }

}

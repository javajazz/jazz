package jazz.pictures.immutable;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import jazz.pictures.ImmutableAbstractPicture;

final class Ellipse extends ImmutableAbstractPicture<Ellipse> {

  public Ellipse(double a, double b) {
    super(new Ellipse2D.Double(0, 0, a * 2, b * 2));
  }

  private Ellipse(Shape shape) {
    super(shape);
  }

  @Override
  public Ellipse clone() {
    return doClone(new Ellipse(shape));
  }

}

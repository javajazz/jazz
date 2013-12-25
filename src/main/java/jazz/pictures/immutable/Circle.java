package jazz.pictures.immutable;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import jazz.pictures.ImmutableAbstractPicture;

public final class Circle extends ImmutableAbstractPicture<Circle> {

  public Circle(double radius) {
    super(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
  }

  private Circle(Shape shape) {
    super(shape);
  }

  @Override
  public Circle clone() {
    return doClone(new Circle(shape));
  }

}

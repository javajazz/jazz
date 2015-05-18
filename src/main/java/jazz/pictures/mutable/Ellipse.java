package jazz.pictures.mutable;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import jazz.pictures.MutableAbstractPicture;

public final class Ellipse extends MutableAbstractPicture<Ellipse> {

  public Ellipse(final double a, final double b) {
    super(new Ellipse2D.Double(0, 0, a * 2, b * 2));
  }

  private Ellipse(final Shape shape) {
    super(shape);
  }

  @Override
  public Ellipse clone() {
    return doClone(new Ellipse(shape));
  }

}

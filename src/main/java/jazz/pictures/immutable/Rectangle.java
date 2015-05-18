package jazz.pictures.immutable;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import jazz.pictures.ImmutableAbstractPicture;

public final class Rectangle extends
    ImmutableAbstractPicture<Rectangle> {

  public Rectangle(final double width, final double height) {
    super(new Rectangle2D.Double(0, 0, width, height));
  }

  private Rectangle(final Shape shape) {
    super(shape);
  }

  @Override
  public Rectangle clone() {
    return doClone(new Rectangle(shape));
  }

}

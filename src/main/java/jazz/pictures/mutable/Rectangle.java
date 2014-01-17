package jazz.pictures.mutable;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import jazz.pictures.MutableAbstractPicture;

public final class Rectangle extends MutableAbstractPicture<Rectangle> {

  public Rectangle(double width, double height) {
    super(new Rectangle2D.Double(0, 0, width, height));
  }

  private Rectangle(Shape shape) {
    super(shape);
  }

  @Override
  public Rectangle clone() {
    return doClone(new Rectangle(shape));
  }

}
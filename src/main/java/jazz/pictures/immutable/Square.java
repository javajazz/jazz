package jazz.pictures.immutable;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import jazz.pictures.ImmutableAbstractPicture;

public final class Square extends ImmutableAbstractPicture<Square> {

  public Square(final double side) {
    super(new Rectangle2D.Double(0, 0, side, side));
  }

  private Square(final Shape shape) {
    super(shape);
  }

  @Override
  public Square clone() {
    return doClone(new Square(shape));
  }

}

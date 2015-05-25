package de.scravy.jazz.pictures.mutable;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import de.scravy.jazz.pictures.MutableAbstractPicture;

public final class Rectangle extends MutableAbstractPicture<Rectangle> {

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

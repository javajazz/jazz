package de.scravy.jazz.pictures.mutable;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import de.scravy.jazz.pictures.MutableAbstractPicture;

public final class Circle extends MutableAbstractPicture<Circle> {

  private static final long serialVersionUID = 1L;

  public Circle(final double radius) {
    super(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
  }

  private Circle(final Shape shape) {
    super(shape);
  }

  @Override
  public Circle clone() {
    return doClone(new Circle(this.shape));
  }

}

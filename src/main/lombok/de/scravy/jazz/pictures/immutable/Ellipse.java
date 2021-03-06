package de.scravy.jazz.pictures.immutable;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import de.scravy.jazz.pictures.ImmutableAbstractPicture;

public final class Ellipse extends ImmutableAbstractPicture<Ellipse> {

  private static final long serialVersionUID = 1L;

  public Ellipse(final double a, final double b) {
    super(new Ellipse2D.Double(0, 0, a * 2, b * 2));
  }

  private Ellipse(final Shape shape) {
    super(shape);
  }

  @Override
  public Ellipse clone() {
    return doClone(new Ellipse(this.shape));
  }

}

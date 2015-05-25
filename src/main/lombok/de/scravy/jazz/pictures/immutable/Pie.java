package de.scravy.jazz.pictures.immutable;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;

import de.scravy.jazz.pictures.ImmutableAbstractPicture;

public final class Pie extends ImmutableAbstractPicture<Pie> {

  private static final long serialVersionUID = 1L;

  public Pie(final double w, final double h, final double start,
      final double extent) {
    super(new Arc2D.Double(-w / 4, -h / 4, w / 2, h / 2,
        360 - start, -(extent - start), Arc2D.PIE));
  }

  private Pie(final Shape shape) {
    super(shape);
  }

  @Override
  protected void doDraw(final Graphics2D g2d) {
    g2d.setTransform(getTransform(g2d.getTransform()));
    doRender(g2d);
  }

  @Override
  public Pie clone() {
    return doClone(new Pie(this.shape));
  }

}

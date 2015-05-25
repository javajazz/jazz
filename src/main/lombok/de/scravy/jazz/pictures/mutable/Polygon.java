package de.scravy.jazz.pictures.mutable;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

import de.scravy.jazz.Vector;
import de.scravy.jazz.pictures.MutableAbstractPicture;

public final class Polygon extends MutableAbstractPicture<Polygon> {

  public Polygon(final Vector... points) {
    super(makePath(points));
  }

  public Polygon(final int n, final double r) {
    super(makePath(n, r));
  }

  private static Shape makePath(final int n, final double r) {
    if (n < 3) {
      throw new IllegalArgumentException();
    }
    final GeneralPath path = new GeneralPath();
    path.moveTo(0, r);
    for (int i = 1; i < n; i++) {
      final double rad = 2 * Math.PI * i / n + Math.PI / 2;
      path.lineTo(Math.cos(rad) * r, Math.sin(rad) * r);
    }
    path.lineTo(0, r);
    return path;
  }

  private static Shape makePath(final Vector[] points) {
    final GeneralPath path = new GeneralPath();
    path.moveTo(points[0].getX(), points[0].getY());
    for (int i = 0; i < points.length; i++) {
      final int ix = (i + 1) % points.length;
      path.lineTo(points[ix].getX(), points[ix].getY());
    }
    return path;
  }

  private Polygon(final GeneralPath path) {
    super((GeneralPath) path.clone());
  }

  @Override
  protected void doDraw(final Graphics2D g2d) {
    g2d.setTransform(getTransform(g2d.getTransform()));
    doRender(g2d);
  }

  @Override
  public Polygon clone() {
    return doClone(new Polygon((GeneralPath) this.shape));
  }

}

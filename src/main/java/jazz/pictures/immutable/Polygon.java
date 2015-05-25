package jazz.pictures.immutable;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

import jazz.pictures.ImmutableAbstractPicture;
import de.scravy.jazz.Vector;

public final class Polygon extends ImmutableAbstractPicture<Polygon> {

  public Polygon(final Vector... points) {
    super(makePath(points));
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

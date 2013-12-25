package jazz.pictures.immutable;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

import jazz.Vector;
import jazz.pictures.ImmutableAbstractPicture;

public final class Polygon extends ImmutableAbstractPicture<Polygon> {

  public Polygon(Vector... points) {
    super(makePath(points));
  }

  private static Shape makePath(Vector[] points) {
    GeneralPath path = new GeneralPath();
    path.moveTo(points[0].x, points[0].y);
    for (int i = 0; i < points.length; i++) {
      int ix = (i + 1) % points.length;
      path.lineTo(points[ix].x, points[ix].y);
    }
    return path;
  }

  private Polygon(GeneralPath path) {
    super((GeneralPath) path.clone());
  }

  protected void doDraw(Graphics2D g2d) {
    g2d.setTransform(getTransform(g2d.getTransform()));
    doRender(g2d);
  }

  @Override
  public Polygon clone() {
    return doClone(new Polygon((GeneralPath) shape));
  }

}

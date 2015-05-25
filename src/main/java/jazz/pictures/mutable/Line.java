package jazz.pictures.mutable;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import jazz.pictures.MutableAbstractPicture;
import de.scravy.jazz.Vector;

public final class Line extends MutableAbstractPicture<Line> {

  public Line(final Vector from, final Vector to) {
    super(new Line2D.Double(from.getX(), from.getY(), to.getX(), to.getY()));
  }

  private Line(final Shape shape) {
    super(shape);
  }

  @Override
  protected void doDraw(final Graphics2D g2d) {
    g2d.setTransform(getTransform(g2d.getTransform()));
    doRender(g2d);
  }

  @Override
  public Line clone() {
    return doClone(new Line(this.shape));
  }

}

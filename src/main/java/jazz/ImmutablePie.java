package jazz;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;

class ImmutablePie extends ImmutableAbstractPicture<ImmutablePie> {

  public ImmutablePie(double w, double h, double start, double extent) {
    super(new Arc2D.Double(-w / 4, -h / 4, w / 2, h / 2,
        360 - start, -(extent - start), Arc2D.PIE));
  }

  private ImmutablePie(Shape shape) {
    super(shape);
  }

  void doDraw(Graphics2D g2d) {
    g2d.setTransform(getTransform(g2d.getTransform()));
    doRender(g2d);
  }

  @Override
  public ImmutablePie clone() {
    return doClone(new ImmutablePie(shape));
  }

}

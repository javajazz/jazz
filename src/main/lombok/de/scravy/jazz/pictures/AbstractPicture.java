package de.scravy.jazz.pictures;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayDeque;
import java.util.Deque;

import de.scravy.jazz.Color;
import de.scravy.jazz.Picture;

public abstract class AbstractPicture<P extends AbstractPicture<P>> implements
    Picture {

  protected final java.awt.Shape shape;
  protected final Deque<AffineTransform> transforms = new ArrayDeque<>();

  protected java.awt.Color color = null;
  protected java.awt.Stroke stroke = null;
  protected java.awt.Composite alpha = null;
  protected boolean filled = false;

  public AbstractPicture(final java.awt.Shape shape) {
    this.shape = shape;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P remove() {
    if (!transforms.isEmpty()) {
      transforms.removeFirst();
    }
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P reset() {
    transforms.clear();
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P translate(final double x, final double y) {
    transforms.addFirst(AffineTransform.getTranslateInstance(x, y));
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P rotate(final double angle) {
    transforms.addFirst(AffineTransform.getRotateInstance(angle / 180
        * Math.PI, 0, 0));
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P scale(final double x, final double y) {
    transforms.addFirst(AffineTransform.getScaleInstance(x, y));
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P shear(final double x, final double y) {
    transforms.addFirst(AffineTransform.getShearInstance(x, y));
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P transform(final AffineTransform transform) {
    transforms.addFirst(transform);
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P transform(final double m00, final double m10, final double m01,
      final double m11,
      final double m02, final double m12) {
    transforms.addFirst(new AffineTransform(m00, m10, m01, m11, m02, m12));
    return (P) this;
  }

  @Override
  public P flipX() {
    return scale(-1, 1);
  }

  @Override
  public P flipY() {
    return scale(1, -1);
  }

  @SuppressWarnings("unchecked")
  @Override
  public P filled(final boolean filled) {
    this.filled = filled;
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P stroke(final double width) {
    this.stroke = new BasicStroke((float) width);
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P alpha(final double alpha) {
    this.alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
        (float) alpha);
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P color(final Color color) {
    if (color != null) {
      this.color = color.getAWTColor();
    }
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P color(final int r, final int g, final int b) {
    this.color = new java.awt.Color(r, g, b);
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P color(final int r, final int g, final int b, final double alpha) {
    this.color = new java.awt.Color(r, g, b, (int) Math.floor(alpha * 255));
    return (P) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public P color(final float h, final float s, final float v) {
    this.color = java.awt.Color.getHSBColor(h, s, v);
    return (P) this;
  }

  @Override
  final public AffineTransform getTransform() {
    return getTransform(new AffineTransform());
  }

  protected final AffineTransform getTransform(
      final AffineTransform finalTransform) {
    final AffineTransform transform = new AffineTransform(finalTransform);
    for (final AffineTransform t : transforms) {
      transform.concatenate(t);
    }
    return transform;
  }

  protected final Point2D.Double getCenter() {
    final Point2D.Double pos = new Point2D.Double();
    getTransform().transform(new Point2D.Double(), pos);
    return pos;
  }

  protected void doDraw(final Graphics2D g2d) {
    final Rectangle2D bounds = shape.getBounds2D();
    final AffineTransform transform = getTransform(g2d.getTransform());

    transform.concatenate(AffineTransform.getTranslateInstance(
        bounds.getWidth() / -2, bounds.getHeight() / -2));
    g2d.setTransform(transform);

    doRender(g2d);
  }

  protected void doRender(final Graphics2D g2d) {
    if (color != null) {
      g2d.setColor(color);
    }
    if (stroke != null) {
      g2d.setStroke(stroke);
    }
    if (alpha != null) {
      g2d.setComposite(alpha);
    }
    if (filled) {
      g2d.fill(shape);
    } else {
      g2d.draw(shape);
    }
  }

  @Override
  final public void draw(final Graphics2D g2d) {
    doDraw(g2d);
  }

  @Override
  public String toString() {
    final Point2D.Double pos = getCenter();
    final Rectangle2D bounds = shape.getBounds2D();
    return String.format("%s(x=%.3f,y=%.3f,a=%.3f,b=%.3f)", getClass()
        .getSimpleName(), pos.x, pos.y, bounds.getWidth(), bounds
        .getHeight());
  }

  protected P doClone(final P object) {
    object.transforms.addAll(transforms);
    object.color = color;
    object.filled = filled;
    return object;
  }

  @Override
  abstract public P clone();
}

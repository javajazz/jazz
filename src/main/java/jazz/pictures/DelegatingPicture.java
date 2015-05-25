package jazz.pictures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import de.scravy.jazz.Color;
import jazz.Picture;

public abstract class DelegatingPicture<X extends Picture> implements Picture {

  private final Picture delegate;

  @Override
  public void draw(final Graphics2D g2d) {
    delegate.draw(g2d);
  }

  public DelegatingPicture(final Picture delegate) {
    this.delegate = delegate;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X alpha(final double alpha) {
    delegate.alpha(alpha);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X color(final Color color) {
    delegate.color(color);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X color(final int r, final int g, final int b) {
    delegate.color(r, g, b);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X color(final float h, final float s, final float v) {
    delegate.color(h, s, v);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X color(final int r, final int g, final int b, final double alpha) {
    delegate.color(r, g, b, alpha);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X translate(final double x, final double y) {
    delegate.translate(x, y);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X rotate(final double angle) {
    delegate.rotate(angle);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X scale(final double x, final double y) {
    delegate.scale(x, y);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X shear(final double x, final double y) {
    delegate.shear(x, y);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X stroke(final double width) {
    delegate.stroke(width);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X flipX() {
    delegate.flipX();
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X flipY() {
    delegate.flipY();
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X filled(final boolean filled) {
    delegate.filled(filled);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X remove() {
    delegate.remove();
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X reset() {
    delegate.reset();
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X transform(final AffineTransform transform) {
    delegate.transform(transform);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X transform(final double m00, final double m10, final double m01,
      final double m11,
      final double m02, final double m12) {
    delegate.transform(m00, m10, m01, m11, m02, m12);
    return (X) this;
  }

  @Override
  public X clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }

  @Override
  public AffineTransform getTransform() {
    return delegate.getTransform();
  }
}

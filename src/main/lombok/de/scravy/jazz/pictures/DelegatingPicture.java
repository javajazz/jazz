package de.scravy.jazz.pictures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import de.scravy.jazz.Color;
import de.scravy.jazz.Picture;

public abstract class DelegatingPicture<X extends Picture> implements Picture {

  private static final long serialVersionUID = 1L;

  private final Picture delegate;

  @Override
  public void draw(final Graphics2D g2d) {
    this.delegate.draw(g2d);
  }

  public DelegatingPicture(final Picture delegate) {
    this.delegate = delegate;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X alpha(final double alpha) {
    this.delegate.alpha(alpha);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X color(final Color color) {
    this.delegate.color(color);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X color(final int r, final int g, final int b) {
    this.delegate.color(r, g, b);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X color(final float h, final float s, final float v) {
    this.delegate.color(h, s, v);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X color(final int r, final int g, final int b, final double alpha) {
    this.delegate.color(r, g, b, alpha);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X translate(final double x, final double y) {
    this.delegate.translate(x, y);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X rotate(final double angle) {
    this.delegate.rotate(angle);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X scale(final double x, final double y) {
    this.delegate.scale(x, y);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X shear(final double x, final double y) {
    this.delegate.shear(x, y);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X stroke(final double width) {
    this.delegate.stroke(width);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X flipX() {
    this.delegate.flipX();
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X flipY() {
    this.delegate.flipY();
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X filled(final boolean filled) {
    this.delegate.filled(filled);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X remove() {
    this.delegate.remove();
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X reset() {
    this.delegate.reset();
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X transform(final AffineTransform transform) {
    this.delegate.transform(transform);
    return (X) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public X transform(final double m00, final double m10, final double m01,
      final double m11,
      final double m02, final double m12) {
    this.delegate.transform(m00, m10, m01, m11, m02, m12);
    return (X) this;
  }

  @Override
  public X clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }

  @Override
  public AffineTransform getTransform() {
    return this.delegate.getTransform();
  }
}

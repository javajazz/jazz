package de.scravy.jazz.pictures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

import de.scravy.jazz.Color;
import de.scravy.jazz.Picture;
import de.scravy.jazz.pictures.mutable.Pictures;

public class UnmodifieablePictures implements Picture {

  private static final long serialVersionUID = 1L;

  private final Pictures p;

  public UnmodifieablePictures(final Pictures p) {
    this.p = p;
  }

  public <P extends Picture> P get(final int index, final Class<P> clazz) {
    return this.p.get(index, clazz);
  }

  public Picture get(final int index) {
    return this.p.get(index);
  }

  @Override
  public int hashCode() {
    return this.p.hashCode();
  }

  @Override
  public String toString() {
    return this.p.toString();
  }

  public Iterator<Picture> iterator() {
    return this.p.iterator();
  }

  @Override
  public boolean equals(final Object obj) {
    return this.p.equals(obj);
  }

  @Override
  public final AffineTransform getTransform() {
    return this.p.getTransform();
  }

  @Override
  public final void draw(final Graphics2D g2d) {
    this.p.draw(g2d);
  }

  @Override
  public UnmodifieablePictures clone() {
    return new UnmodifieablePictures(this.p);
  }

  @Override
  public UnmodifieablePictures alpha(final double alpha) {
    return this;
  }

  @Override
  public UnmodifieablePictures color(final Color color) {
    return this;
  }

  @Override
  public UnmodifieablePictures color(final int r, final int g, final int b) {
    return this;
  }

  @Override
  public UnmodifieablePictures color(final float h, final float s,
      final float v) {
    return this;
  }

  @Override
  public UnmodifieablePictures color(final int r, final int g, final int b,
      final double alpha) {
    return this;
  }

  @Override
  public UnmodifieablePictures translate(final double x, final double y) {
    return this;
  }

  @Override
  public UnmodifieablePictures rotate(final double angle) {
    return this;
  }

  @Override
  public UnmodifieablePictures scale(final double x, final double y) {
    return this;
  }

  @Override
  public UnmodifieablePictures shear(final double x, final double y) {
    return this;
  }

  @Override
  public UnmodifieablePictures stroke(final double width) {
    return this;
  }

  @Override
  public UnmodifieablePictures flipX() {
    return this;
  }

  @Override
  public UnmodifieablePictures flipY() {
    return this;
  }

  @Override
  public UnmodifieablePictures filled(final boolean filled) {
    return this;
  }

  @Override
  public UnmodifieablePictures remove() {
    return this;
  }

  @Override
  public UnmodifieablePictures reset() {
    return this;
  }

  @Override
  public UnmodifieablePictures transform(final AffineTransform transform) {
    return this;
  }

  @Override
  public UnmodifieablePictures transform(final double m00, final double m10,
      final double m01,
      final double m11,
      final double m02, final double m12) {
    return this;
  }
}

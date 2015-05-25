package jazz.pictures;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

import de.scravy.jazz.Color;

public abstract class ImmutableAbstractPicture<P extends ImmutableAbstractPicture<P>>
    extends AbstractPicture<P> {

  public ImmutableAbstractPicture(final Shape shape) {
    super(shape);
  }

  @Override
  final public P remove() {
    final P picture = clone();
    picture._remove();
    return picture;
  }

  final void _remove() {
    super.remove();
  }

  @Override
  final public P reset() {
    final P picture = clone();
    picture._reset();
    return picture;
  }

  final void _reset() {
    super.reset();
  }

  @Override
  final public P translate(final double x, final double y) {
    final P picture = clone();
    picture._translate(x, y);
    return picture;
  }

  final void _translate(final double x, final double y) {
    super.translate(x, y);
  }

  @Override
  final public P rotate(final double angle) {
    final P picture = clone();
    picture._rotate(angle);
    return picture;
  }

  final void _rotate(final double angle) {
    super.rotate(angle);
  }

  @Override
  final public P scale(final double x, final double y) {
    final P picture = clone();
    picture._scale(x, y);
    return picture;
  }

  final void _scale(final double x, final double y) {
    super.scale(x, y);
  }

  @Override
  final public P shear(final double x, final double y) {
    final P picture = clone();
    picture._shear(x, y);
    return picture;
  }

  final void _shear(final double x, final double y) {
    super.shear(x, y);
  }

  @Override
  final public P transform(final AffineTransform transform) {
    final P picture = clone();
    picture._transform(transform);
    return picture;
  }

  final void _transform(final AffineTransform transform) {
    super.transform(transform);
  }

  @Override
  final public P transform(
      final double m00, final double m10,
      final double m01, final double m11,
      final double m02, final double m12) {
    final P picture = clone();
    picture._transform(m00, m10, m10, m11, m02, m12);
    return picture;
  }

  final void _transform(
      final double m00, final double m10,
      final double m01, final double m11,
      final double m02, final double m12) {
    super.transform(m00, m10, m01, m11, m02, m12);
  }

  @Override
  final public P flipX() {
    final P picture = clone();
    picture._flipX();
    return picture;
  }

  final void _flipX() {
    super.flipX();
  }

  @Override
  final public P flipY() {
    final P picture = clone();
    picture._flipY();
    return picture;
  }

  final void _flipY() {
    super.flipY();
  }

  @Override
  final public P filled(final boolean filled) {
    final P picture = clone();
    picture._filled(filled);
    return picture;
  }

  final void _filled(final boolean filled) {
    super.filled(filled);
  }

  @Override
  final public P color(final Color color) {
    final P picture = clone();
    picture._color(color);
    return picture;
  }

  final void _color(final Color color) {
    super.color(color);
  }

  @Override
  final public P color(final int r, final int g, final int b) {
    final P picture = clone();
    picture._color(r, g, b);
    return picture;
  }

  final void _color(final int r, final int g, final int b) {
    super.color(r, g, b);
  }

  @Override
  final public P color(final float h, final float s, final float v) {
    final P picture = clone();
    picture._color(h, s, v);
    return picture;
  }

  final void _color(final float h, final float s, final float v) {
    super.color(h, s, v);
  }

}

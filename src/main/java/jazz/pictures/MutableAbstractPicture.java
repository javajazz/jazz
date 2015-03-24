package jazz.pictures;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

import jazz.Color;
import jazz.Mutable;

public abstract class MutableAbstractPicture<P extends MutableAbstractPicture<P>>
        extends AbstractPicture<P> implements Mutable {

    public MutableAbstractPicture(final Shape shape) {
        super(shape);
    }

    @Override
    final public P remove() {
        return super.remove();
    }

    @Override
    final public P reset() {
        return super.reset();
    }

    @Override
    final public P translate(final double x, final double y) {
        return super.translate(x, y);
    }

    @Override
    final public P rotate(final double angle) {
        return super.rotate(angle);
    }

    @Override
    final public P scale(final double x, final double y) {
        return super.scale(x, y);
    }

    @Override
    final public P shear(final double x, final double y) {
        return super.shear(x, y);
    }

    @Override
    final public P transform(final AffineTransform transform) {
        return super.transform(transform);
    }

    @Override
    final public P transform(
            final double m00, final double m10,
            final double m01, final double m11,
            final double m02, final double m12) {
        return super.transform(m00, m10, m01, m11, m02, m12);
    }

    @Override
    final public P flipX() {
        return super.flipX();
    }

    @Override
    final public P flipY() {
        return super.flipY();
    }

    @Override
    final public P filled(final boolean filled) {
        return super.filled(filled);
    }

    @Override
    final public P color(final Color color) {
        return super.color(color);
    }

    @Override
    final public P color(final int r, final int g, final int b) {
        return super.color(r, g, b);
    }

    @Override
    final public P color(final float h, final float s, final float v) {
        return super.color(h, s, v);
    }

}

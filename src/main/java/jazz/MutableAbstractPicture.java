package jazz;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

abstract class MutableAbstractPicture<P extends MutableAbstractPicture<P>>
		extends AbstractPicture<P> implements Mutable {

	public MutableAbstractPicture(Shape shape) {
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
	final public P translate(double x, double y) {
		return super.translate(x, y);
	}

	@Override
	final public P rotate(double angle) {
		return super.rotate(angle);
	}

	@Override
	final public P scale(double x, double y) {
		return super.scale(x, y);
	}

	@Override
	final public P shear(double x, double y) {
		return super.shear(x, y);
	}
	
	@Override
	final public P transform(AffineTransform transform) {
		return super.transform(transform);
	}

	@Override
	final public P transform(
			double m00, double m10,
			double m01, double m11,
			double m02, double m12) {
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
	final public P filled(boolean filled) {
		return super.filled(filled);
	}

	@Override
	final public P color(Color color) {
		return super.color(color);
	}

	@Override
	final public P color(int r, int g, int b) {
		return super.color(r, g, b);
	}

	@Override
	final public P color(float h, float s, float v) {
		return super.color(h, s, v);
	}

}

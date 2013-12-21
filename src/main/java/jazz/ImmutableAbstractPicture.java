package jazz;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

abstract class ImmutableAbstractPicture<P extends ImmutableAbstractPicture<P>>
		extends AbstractPicture<P> implements Immutable {

	public ImmutableAbstractPicture(Shape shape) {
		super(shape);
	}

	@Override
	final public P remove() {
		P picture = clone();
		picture._remove();
		return picture;
	}

	final void _remove() {
		super.remove();
	}
	
	@Override
	final public P reset() {
		P picture = clone();
		picture._reset();
		return picture;
	}
	
	final void _reset() {
		super.reset();
	}

	@Override
	final public P translate(double x, double y) {
		P picture = clone();
		picture._translate(x, y);
		return picture;
	}

	final void _translate(double x, double y) {
		super.translate(x, y);
	}
	
	@Override
	final public P rotate(double angle) {
		P picture = clone();
		picture._rotate(angle);
		return picture;
	}
	
	final void _rotate(double angle) {
		super.rotate(angle);
	}

	@Override
	final public P scale(double x, double y) {
		P picture = clone();
		picture._scale(x, y);
		return picture;
	}

	final void _scale(double x, double y) {
		super.scale(x, y);
	}

	final public P shear(double x, double y) {
		P picture = clone();
		picture._shear(x, y);
		return picture;
	}
	
	final void _shear(double x, double y) {
		super.shear(x, y);
	}
	
	@Override
	final public P transform(AffineTransform transform) {
		P picture = clone();
		picture._transform(transform);
		return picture;
	}

	final void _transform(AffineTransform transform) {
		super.transform(transform);
	}

	@Override
	final public P transform(
			double m00, double m10,
			double m01, double m11,
			double m02, double m12) {
		P picture = clone();
		picture._transform(m00, m10, m10, m11, m02, m12);
		return picture;
	}

	final void _transform(
			double m00, double m10,
			double m01, double m11,
			double m02, double m12) {
		super.transform(m00, m10, m01, m11, m02, m12);
	}

	@Override
	final public P flipX() {
		P picture = clone();
		picture._flipX();
		return picture;
	}

	final void _flipX() {
		super.flipX();
	}

	@Override
	final public P flipY() {
		P picture = clone();
		picture._flipY();
		return picture;
	}

	final void _flipY() {
		super.flipY();
	}

	@Override
	final public P filled(boolean filled) {
		P picture = clone();
		picture._filled(filled);
		return picture;
	}

	final void _filled(boolean filled) {
		super.filled(filled);
	}

	@Override
	final public P color(Color color) {
		P picture = clone();
		picture._color(color);
		return picture;
	}

	final void _color(Color color) {
		super.color(color);
	}

	@Override
	final public P color(int r, int g, int b) {
		P picture = clone();
		picture._color(r, g, b);
		return picture;
	}

	final void _color(int r, int g, int b) {
		super.color(r, g, b);
	}

	@Override
	final public P color(float h, float s, float v) {
		P picture = clone();
		picture._color(h, s, v);
		return picture;
	}

	final void _color(float h, float s, float v) {
		super.color(h, s, v);
	}

}

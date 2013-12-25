package jazz.pictures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import jazz.Color;
import jazz.Picture;

public abstract class DelegatingPicture<X extends Picture> implements Picture {

	private final Picture delegate;

	public void draw(Graphics2D g2d) {
		delegate.draw(g2d);
	}

	public DelegatingPicture(Picture delegate) {
		this.delegate = delegate;
	}
	
	@SuppressWarnings("unchecked")
	public X alpha(double alpha) {
		delegate.alpha(alpha);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X color(Color color) {
		delegate.color(color);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X color(int r, int g, int b) {
		delegate.color(r, g, b);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X color(float h, float s, float v) {
		delegate.color(h, s, v);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X color(int r, int g, int b, double alpha) {
		delegate.color(r, g, b, alpha);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X translate(double x, double y) {
		delegate.translate(x, y);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X rotate(double angle) {
		delegate.rotate(angle);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X scale(double x, double y) {
		delegate.scale(x, y);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X shear(double x, double y) {
		delegate.shear(x, y);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X stroke(double width) {
		delegate.stroke(width);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X flipX() {
		delegate.flipX();
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X flipY() {
		delegate.flipY();
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X filled(boolean filled) {
		delegate.filled(filled);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X remove() {
		delegate.remove();
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X reset() {
		delegate.reset();
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X transform(AffineTransform transform) {
		delegate.transform(transform);
		return (X) this;
	}

	@SuppressWarnings("unchecked")
	public X transform(double m00, double m10, double m01, double m11,
			double m02, double m12) {
		delegate.transform(m00, m10, m01, m11, m02, m12);
		return (X) this;
	}

	public X clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public AffineTransform getTransform() {
		return delegate.getTransform();
	}
}

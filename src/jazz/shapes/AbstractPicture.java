package jazz.shapes;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayDeque;
import java.util.Deque;

import jazz.Color;
import jazz.HsvColor;
import jazz.Picture;
import jazz.RgbColor;

abstract class AbstractPicture<P extends AbstractPicture<P>> implements Picture {

	final java.awt.Shape shape;
	final Deque<AffineTransform> transforms = new ArrayDeque<>();

	java.awt.Color color = null;
	boolean filled = false;

	public AbstractPicture(java.awt.Shape shape) {
		this.shape = shape;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P remove() {
		if (!transforms.isEmpty()) {
			transforms.removeFirst();
		}
		return (P) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P reset() {
		transforms.clear();
		return (P) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P translate(double x, double y) {
		transforms.addFirst(AffineTransform.getTranslateInstance(x, y));
		return (P) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P rotate(double angle) {
		transforms.addFirst(AffineTransform.getRotateInstance(angle / 180
				* Math.PI, 0, 0));
		return (P) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P scale(double x, double y) {
		transforms.addFirst(AffineTransform.getScaleInstance(x, y));
		return (P) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P transform(AffineTransform transform) {
		transforms.addFirst(transform);
		return (P) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P transform(double m00, double m10, double m01,
			double m11,
			double m02, double m12) {
		transforms.addFirst(new AffineTransform(m00, m10, m01, m11, m02, m12));
		return (P) this;
	}

	@Override
	final public P flipX() {
		return scale(-1, 1);
	}

	@Override
	final public P flipY() {
		return scale(1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P filled(boolean filled) {
		this.filled = filled;
		return (P) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P color(Color color) {

		if (color instanceof RgbColor) {
			RgbColor rgb = (RgbColor) color;
			color(rgb.getR(), rgb.getG(), rgb.getB());
		} else if (color instanceof HsvColor) {
			HsvColor hsv = (HsvColor) color;
			color(hsv.getH(), hsv.getS(), hsv.getV());
		} else {
			this.color = null;
		}
		return (P) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P color(int r, int g, int b) {
		float[] hsb = new float[3];
		java.awt.Color.RGBtoHSB(r, g, b, hsb);
		color(hsb[0], hsb[1], hsb[2]);
		return (P) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public P color(float h, float s, float v) {
		this.color = java.awt.Color.getHSBColor(h, s, v);
		return (P) this;
	}

	final AffineTransform getTransform() {
		return getTransform(new AffineTransform());
	}

	final AffineTransform getTransform(AffineTransform finalTransform) {
		AffineTransform transform = new AffineTransform(finalTransform);
		for (AffineTransform t : transforms) {
			transform.concatenate(t);
		}
		return transform;
	}

	final Point2D.Double getCenter() {
		Point2D.Double pos = new Point2D.Double();
		getTransform().transform(new Point2D.Double(), pos);
		return pos;
	}

	void doDraw(Graphics2D g2d) {
		Rectangle2D bounds = shape.getBounds2D();
		AffineTransform transform = getTransform(g2d.getTransform());

		transform.concatenate(AffineTransform.getTranslateInstance(
				bounds.getWidth() / -2, bounds.getHeight() / -2));
		g2d.setTransform(transform);

		if (color != null) {
			g2d.setColor(color);
		}
		if (filled) {
			g2d.fill(shape);
		} else {
			g2d.draw(shape);
		}
	}

	final public void draw(Graphics2D g2d) {
		doDraw(g2d);
	}

	public String toString() {
		Point2D.Double pos = getCenter();
		Rectangle2D bounds = shape.getBounds2D();
		return String.format("%s(x=%.3f,y=%.3f,a=%.3f,b=%.3f)", getClass()
				.getSimpleName(), pos.x, pos.y, bounds.getWidth(), bounds
				.getHeight());
	}
	
	P doClone(P object) {
		object.transforms.addAll(transforms);
		object.color = color;
		object.filled = filled;
		return object;
	}
	
	abstract public P clone();
}

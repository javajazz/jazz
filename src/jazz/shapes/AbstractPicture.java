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

abstract class AbstractPicture implements Picture {

	private final java.awt.Shape shape;
	private final Deque<AffineTransform> transforms = new ArrayDeque<>();

	java.awt.Color color = null;
	boolean filled = false;

	public AbstractPicture(java.awt.Shape shape) {
		this.shape = shape;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public Picture remove() {
		if (!transforms.isEmpty()) {
			transforms.removeFirst();
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public S reset() {
		transforms.clear();
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public S translate(double x, double y) {
		transforms.addFirst(AffineTransform.getTranslateInstance(x, y));
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public S rotate(double angle) {
		transforms.addFirst(AffineTransform.getRotateInstance(angle / 180
				* Math.PI, 0, 0));
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public S scale(double x, double y) {

		transforms.addFirst(AffineTransform.getScaleInstance(x, y));
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public S transform(AffineTransform transform) {
		transforms.addFirst(transform);
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public S transform(double m00, double m10, double m01, double m11,
			double m02, double m12) {
		transforms.addFirst(new AffineTransform(m00, m10, m01, m11, m02, m12));
		return this;
	}

	@Override
	final public S flipX() {
		return scale(-1, 1);
	}

	@Override
	final public S flipY() {
		return scale(1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	final public S filled(boolean filled) {
		this.filled = filled;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public S color(Color color) {

		if (color instanceof RgbColor) {
			RgbColor rgb = (RgbColor) color;
			color(rgb.getR(), rgb.getG(), rgb.getB());
		} else if (color instanceof HsvColor) {
			HsvColor hsv = (HsvColor) color;
			color(hsv.getH(), hsv.getS(), hsv.getV());
		} else {
			this.color = null;
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public S color(int r, int g, int b) {
		float[] hsb = new float[3];
		java.awt.Color.RGBtoHSB(r, g, b, hsb);
		color(hsb[0], hsb[1], hsb[2]);
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	final public Picture color(float h, float s, float v) {
		this.color = java.awt.Color.getHSBColor(h, s, v);
		return this;
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
}

package jazz.util;

import jazz.Picture;
import jazz.Vector;

public abstract class AbstractGrid<A> {

	protected Vector center = Vector.ZERO;
	protected double width;
	protected double height;

	abstract Picture getPicture();

	public AbstractGrid(double width, double height) {
		setWidth(width);
		setHeight(height);
	}

	@SuppressWarnings("unchecked")
	public A setCenter(Vector p) {
		this.center = p;
		return (A) this;
	}

	@SuppressWarnings("unchecked")
	public A setCenter(double x, double y) {
		this.center = new Vector(x, y);
		return (A) this;
	}

	public Vector getCenter() {
		return center;
	}

	@SuppressWarnings("unchecked")
	public A setWidth(double width) {
		this.width = width;
		return (A) this;
	}

	public double getWidth() {
		return width;
	}

	@SuppressWarnings("unchecked")
	public A setHeight(double height) {
		this.height = height;
		return (A) this;
	}

	public double getHeight() {
		return height;
	}

	@SuppressWarnings("unchecked")
	public A setLowerLeftCorner(Vector p, boolean resize) {
		if (resize) {
			Vector c = getUpperRightCorner();
			setWidth(Math.abs(p.x - c.x));
			setHeight(Math.abs(p.y - c.y));
			setCenter((p.x + c.x) / 2, (p.y + c.y) / 2);
		} else {
			setCenter(new Vector(p.x + width / 2, p.y + height / 2));
		}
		return (A) this;
	}

	public Vector getLowerLeftCorner() {
		return new Vector(center.x - width / 2, center.y - height / 2);
	}

	@SuppressWarnings("unchecked")
	public A setUpperLeftCorner(Vector p, boolean resize) {
		if (resize) {
			Vector c = getLowerRightCorner();
			setWidth(Math.abs(p.x - c.x));
			setHeight(Math.abs(p.y - c.y));
			setCenter((p.x + c.x) / 2, (p.y + c.y) / 2);
		} else {
			setCenter(new Vector(p.x + width / 2, p.y - height / 2));
		}
		return (A) this;
	}

	public Vector getUpperLeftCorner() {
		return new Vector(center.x - width / 2, center.y + height / 2);
	}

	@SuppressWarnings("unchecked")
	public A setLowerRightCorner(Vector p, boolean resize) {
		if (resize) {
			Vector c = getUpperLeftCorner();
			setWidth(Math.abs(p.x - c.x));
			setHeight(Math.abs(p.y - c.y));
			setCenter((p.x + c.x) / 2, (p.y + c.y) / 2);
		} else {
			setCenter(new Vector(p.x - width / 2, p.y + height / 2));
		}
		return (A) this;
	}

	public Vector getLowerRightCorner() {
		return new Vector(center.x + width / 2, center.y - height / 2);
	}

	@SuppressWarnings("unchecked")
	public A setUpperRightCorner(Vector p, boolean resize) {
		if (resize) {
			Vector c = getLowerLeftCorner();
			setWidth(Math.abs(p.x - c.x));
			setHeight(Math.abs(p.y - c.y));
			setCenter((p.x + c.x) / 2, (p.y + c.y) / 2);
		} else {
			setCenter(new Vector(p.x - width / 2, p.y - height / 2));
		}
		return (A) this;
	}

	public Vector getUpperRightCorner() {
		return new Vector(center.x + width / 2, center.y + height / 2);
	}
}

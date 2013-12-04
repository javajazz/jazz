package jazz.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;

import jazz.Point;

public class Polygon extends MutableAbstractPicture<Polygon> {

	static int[] getXCoords(Point[] points) {
		int[] coords = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			coords[i] = (int) points[i].x;
		}
		return coords;
	}

	static int[] getYCoords(Point[] points) {
		int[] coords = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			coords[i] = (int) points[i].y;
		}
		return coords;
	}

	public Polygon(Point... points) {
		super(new java.awt.Polygon(
				getXCoords(points),
				getYCoords(points),
				points.length));
	}

	private Polygon(Shape shape) {
		super(shape);
	}

	void doDraw(Graphics2D g2d) {
		g2d.setTransform(getTransform(g2d.getTransform()));
		doRender(g2d);
	}

	@Override
	public Polygon clone() {
		return doClone(new Polygon(shape));
	}

}

package jazz.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;

import jazz.Point;

public class ImmutablePolygon extends
		ImmutableAbstractPicture<ImmutablePolygon> {

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

	public ImmutablePolygon(Point... points) {
		super(new java.awt.Polygon(
				getXCoords(points),
				getYCoords(points),
				points.length));
	}

	private ImmutablePolygon(Shape shape) {
		super(shape);
	}

	void doDraw(Graphics2D g2d) {
		g2d.setTransform(getTransform(g2d.getTransform()));
		doRender(g2d);
	}

	@Override
	public ImmutablePolygon clone() {
		return doClone(new ImmutablePolygon(shape));
	}

}

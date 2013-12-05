package jazz.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

import jazz.Point;

public class ImmutablePolygon extends ImmutableAbstractPicture<ImmutablePolygon> {

	public ImmutablePolygon(Point... points) {
		super(makePath(points));
	}

	private static Shape makePath(Point[] points) {
		GeneralPath path = new GeneralPath();
		path.moveTo(points[0].x, points[0].y);
		for (int i = 0; i < points.length; i++) {
			int ix = (i + 1) % points.length;
			path.lineTo(points[ix].x, points[ix].y);
		}
		return path;
	}

	private ImmutablePolygon(GeneralPath path) {
		super((GeneralPath) path.clone());
	}

	void doDraw(Graphics2D g2d) {
		g2d.setTransform(getTransform(g2d.getTransform()));
		doRender(g2d);
	}

	@Override
	public ImmutablePolygon clone() {
		return doClone(new ImmutablePolygon((GeneralPath) shape));
	}

}

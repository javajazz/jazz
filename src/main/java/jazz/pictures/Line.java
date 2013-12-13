package jazz.pictures;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import jazz.Point;

public final class Line extends MutableAbstractPicture<Line> {
	
	public Line(Point from, Point to) {
		super(new Line2D.Double(from.x, from.y, to.x, to.y));
	}
	
	private Line(Shape shape) {
		super(shape);
	}

	void doDraw(Graphics2D g2d) {
		g2d.setTransform(getTransform(g2d.getTransform()));
		doRender(g2d);
	}

	@Override
	public Line clone() {
		return doClone(new Line(shape));
	}

}

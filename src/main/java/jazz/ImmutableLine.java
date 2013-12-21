package jazz;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public final class ImmutableLine extends ImmutableAbstractPicture<ImmutableLine> {
	
	public ImmutableLine(Point from, Point to) {
		super(new Line2D.Double(from.x, from.y, to.x, to.y));
	}
	
	private ImmutableLine(Shape shape) {
		super(shape);
	}

	void doDraw(Graphics2D g2d) {
		g2d.setTransform(getTransform(g2d.getTransform()));
		doRender(g2d);
	}

	@Override
	public ImmutableLine clone() {
		return doClone(new ImmutableLine(shape));
	}

}

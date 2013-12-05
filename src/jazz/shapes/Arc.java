package jazz.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Arc extends MutableAbstractPicture<Arc> {
	
	public Arc(double radius) {
		super(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
	}
	
	private Arc(Shape shape) {
		super(shape);
	}

	@Override
	public Arc clone() {
		return doClone(new Arc(shape));
	}

}

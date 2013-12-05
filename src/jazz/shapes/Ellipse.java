package jazz.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public final class Ellipse extends MutableAbstractPicture<Ellipse> {

	public Ellipse(double a, double b) {
		super(new Ellipse2D.Double(0, 0, a * 2, b * 2));
	}

	private Ellipse(Shape shape) {
		super(shape);
	}
	
	@Override
	public Ellipse clone() {
		return doClone(new Ellipse(shape));
	}

}

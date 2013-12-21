package jazz;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public final class Circle extends MutableAbstractPicture<Circle> {
	
	public Circle(double radius) {
		super(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
	}
	
	private Circle(Shape shape) {
		super(shape);
	}

	@Override
	public Circle clone() {
		return doClone(new Circle(shape));
	}

}

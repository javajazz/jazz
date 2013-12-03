package jazz.shapes;

import java.awt.geom.Ellipse2D;

public class Circle extends AbstractPicture<Circle> {

	public Circle(double radius) {
		super(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
	}

}

package jazz.shapes;

import java.awt.geom.Ellipse2D;

public class Ellipse extends AbstractPicture<Ellipse> {

	public Ellipse(double a, double b) {
		super(new Ellipse2D.Double(0, 0, a * 2, b * 2));
	}

	@Override
	public Ellipse clone() {
		// TODO Auto-generated method stub
		return null;
	}

}

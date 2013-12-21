package jazz;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

final class ImmutableEllipse extends ImmutableAbstractPicture<ImmutableEllipse> {

	public ImmutableEllipse(double a, double b) {
		super(new Ellipse2D.Double(0, 0, a * 2, b * 2));
	}

	private ImmutableEllipse(Shape shape) {
		super(shape);
	}
	
	@Override
	public ImmutableEllipse clone() {
		return doClone(new ImmutableEllipse(shape));
	}

}

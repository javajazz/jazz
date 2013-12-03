package jazz.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class ImmutableCircle extends ImmutableAbstractPicture<ImmutableCircle> {
	
	public ImmutableCircle(double radius) {
		super(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
	}
	
	private ImmutableCircle(Shape shape) {
		super(shape);
	}

	@Override
	public ImmutableCircle clone() {
		return doClone(new ImmutableCircle(shape));
	}

}
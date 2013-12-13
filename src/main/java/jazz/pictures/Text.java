package jazz.pictures;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public final class Text extends MutableAbstractPicture<Text> {
	
	public Text(double radius) {
		super(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
	}
	
	private Text(Shape shape) {
		super(shape);
	}

	@Override
	public Text clone() {
		return doClone(new Text(shape));
	}

}

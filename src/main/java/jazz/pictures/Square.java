package jazz.pictures;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public final class Square extends MutableAbstractPicture<Square> {

	public Square(double side) {
		super(new Rectangle2D.Double(0, 0, side, side));
	}

	private Square(Shape shape) {
		super(shape);
	}

	@Override
	public Square clone() {
		return doClone(new Square(shape));
	}

}

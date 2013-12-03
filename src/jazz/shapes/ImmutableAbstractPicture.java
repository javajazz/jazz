package jazz.shapes;

import java.awt.Shape;

public class ImmutableAbstractPicture<P extends ImmutableAbstractPicture<P>> extends AbstractPicture<P> {

	public ImmutableAbstractPicture(Shape shape) {
		super(shape);
	}

	@Override
	public P clone() {
		return null;
	}

}

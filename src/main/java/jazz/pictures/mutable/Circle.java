package jazz.pictures.mutable;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import jazz.pictures.MutableAbstractPicture;

public final class Circle extends MutableAbstractPicture<Circle> {

    public Circle(final double radius) {
        super(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
    }

    private Circle(final Shape shape) {
        super(shape);
    }

    @Override
    public Circle clone() {
        return doClone(new Circle(shape));
    }

}

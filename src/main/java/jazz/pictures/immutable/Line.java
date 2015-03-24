package jazz.pictures.immutable;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import jazz.Vector;
import jazz.pictures.ImmutableAbstractPicture;

public final class Line extends ImmutableAbstractPicture<Line> {

    public Line(final Vector from, final Vector to) {
        super(new Line2D.Double(from.x, from.y, to.x, to.y));
    }

    private Line(final Shape shape) {
        super(shape);
    }

    @Override
    protected void doDraw(final Graphics2D g2d) {
        g2d.setTransform(getTransform(g2d.getTransform()));
        doRender(g2d);
    }

    @Override
    public Line clone() {
        return doClone(new Line(shape));
    }

}

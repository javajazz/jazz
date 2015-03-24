package jazz.pictures.mutable;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

import jazz.Vector;
import jazz.pictures.MutableAbstractPicture;

public final class Polygon extends MutableAbstractPicture<Polygon> {

    public Polygon(Vector... points) {
        super(makePath(points));
    }

    public Polygon(int n, double r) {
        super(makePath(n, r));
    }

    private static Shape makePath(int n, double r) {
        if (n < 3) {
            throw new IllegalArgumentException();
        }
        GeneralPath path = new GeneralPath();
        path.moveTo(0, r);
        for (int i = 1; i < n; i++) {
            double rad = 2 * Math.PI * i / n + Math.PI / 2;
            path.lineTo(Math.cos(rad) * r, Math.sin(rad) * r);
        }
        path.lineTo(0, r);
        return path;
    }

    private static Shape makePath(Vector[] points) {
        GeneralPath path = new GeneralPath();
        path.moveTo(points[0].x, points[0].y);
        for (int i = 0; i < points.length; i++) {
            int ix = (i + 1) % points.length;
            path.lineTo(points[ix].x, points[ix].y);
        }
        return path;
    }

    private Polygon(GeneralPath path) {
        super((GeneralPath) path.clone());
    }

    protected void doDraw(Graphics2D g2d) {
        g2d.setTransform(getTransform(g2d.getTransform()));
        doRender(g2d);
    }

    @Override
    public Polygon clone() {
        return doClone(new Polygon((GeneralPath) shape));
    }

}

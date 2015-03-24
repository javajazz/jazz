package jazz.grids;

import jazz.Vector;

public abstract class AbstractGrid<A, T> implements Grid<A, T> {

    protected Vector center = Vector.ZERO;
    protected double width;
    protected double height;

    protected final TileEventHandler<T> tileHandler;
    protected final TileRenderer<T> tileRenderer;

    public AbstractGrid(
            final TileEventHandler<T> tileHandler,
            final TileRenderer<T> tileRenderer,
            final double width, final double height) {

        this.tileHandler = tileHandler;
        this.tileRenderer = tileRenderer;

        setWidth(width);
        setHeight(height);
    }

    @Override
    @SuppressWarnings("unchecked")
    public A setCenter(final Vector p) {
        this.center = p;
        return (A) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public A setCenter(final double x, final double y) {
        this.center = new Vector(x, y);
        return (A) this;
    }

    @Override
    public Vector getCenter() {
        return center;
    }

    @Override
    @SuppressWarnings("unchecked")
    public A setWidth(final double width) {
        this.width = width;
        return (A) this;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    @SuppressWarnings("unchecked")
    public A setHeight(final double height) {
        this.height = height;
        return (A) this;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    @SuppressWarnings("unchecked")
    public A setLowerLeftCorner(final Vector p, final boolean resize) {
        if (resize) {
            final Vector c = getUpperRightCorner();
            setWidth(Math.abs(p.x - c.x));
            setHeight(Math.abs(p.y - c.y));
            setCenter((p.x + c.x) / 2, (p.y + c.y) / 2);
        } else {
            setCenter(new Vector(p.x + width / 2, p.y + height / 2));
        }
        return (A) this;
    }

    @Override
    public Vector getLowerLeftCorner() {
        return new Vector(center.x - width / 2, center.y - height / 2);
    }

    @Override
    @SuppressWarnings("unchecked")
    public A setUpperLeftCorner(final Vector p, final boolean resize) {
        if (resize) {
            final Vector c = getLowerRightCorner();
            setWidth(Math.abs(p.x - c.x));
            setHeight(Math.abs(p.y - c.y));
            setCenter((p.x + c.x) / 2, (p.y + c.y) / 2);
        } else {
            setCenter(new Vector(p.x + width / 2, p.y - height / 2));
        }
        return (A) this;
    }

    @Override
    public Vector getUpperLeftCorner() {
        return new Vector(center.x - width / 2, center.y + height / 2);
    }

    @Override
    @SuppressWarnings("unchecked")
    public A setLowerRightCorner(final Vector p, final boolean resize) {
        if (resize) {
            final Vector c = getUpperLeftCorner();
            setWidth(Math.abs(p.x - c.x));
            setHeight(Math.abs(p.y - c.y));
            setCenter((p.x + c.x) / 2, (p.y + c.y) / 2);
        } else {
            setCenter(new Vector(p.x - width / 2, p.y + height / 2));
        }
        return (A) this;
    }

    @Override
    public Vector getLowerRightCorner() {
        return new Vector(center.x + width / 2, center.y - height / 2);
    }

    @Override
    @SuppressWarnings("unchecked")
    public A setUpperRightCorner(final Vector p, final boolean resize) {
        if (resize) {
            final Vector c = getLowerLeftCorner();
            setWidth(Math.abs(p.x - c.x));
            setHeight(Math.abs(p.y - c.y));
            setCenter((p.x + c.x) / 2, (p.y + c.y) / 2);
        } else {
            setCenter(new Vector(p.x - width / 2, p.y - height / 2));
        }
        return (A) this;
    }

    @Override
    public Vector getUpperRightCorner() {
        return new Vector(center.x + width / 2, center.y + height / 2);
    }
}

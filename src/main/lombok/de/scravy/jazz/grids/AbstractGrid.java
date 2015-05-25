package de.scravy.jazz.grids;

import de.scravy.jazz.Vector;
import de.scravy.jazz.annotation.Experimental;
import de.scravy.jazz.grids.TileEventHandler;
import de.scravy.jazz.grids.TileRenderer;

@Experimental
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
    return this.center;
  }

  @Override
  @SuppressWarnings("unchecked")
  public A setWidth(final double width) {
    this.width = width;
    return (A) this;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  @SuppressWarnings("unchecked")
  public A setHeight(final double height) {
    this.height = height;
    return (A) this;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  @SuppressWarnings("unchecked")
  public A setLowerLeftCorner(final Vector p, final boolean resize) {
    if (resize) {
      final Vector c = getUpperRightCorner();
      setWidth(Math.abs(p.getX() - c.getX()));
      setHeight(Math.abs(p.getY() - c.getY()));
      setCenter((p.getX() + c.getX()) / 2, (p.getY() + c.getY()) / 2);
    } else {
      setCenter(new Vector(
          p.getX() + this.width / 2,
          p.getY() + this.height / 2));
    }
    return (A) this;
  }

  @Override
  public Vector getLowerLeftCorner() {
    return new Vector(
        this.center.getX() - this.width / 2,
        this.center.getY() - this.height / 2);
  }

  @Override
  @SuppressWarnings("unchecked")
  public A setUpperLeftCorner(final Vector p, final boolean resize) {
    if (resize) {
      final Vector c = getLowerRightCorner();
      setWidth(Math.abs(p.getX() - c.getX()));
      setHeight(Math.abs(p.getY() - c.getY()));
      setCenter((p.getX() + c.getX()) / 2, (p.getY() + c.getY()) / 2);
    } else {
      setCenter(new Vector(
          p.getX() + this.width / 2,
          p.getY() - this.height / 2));
    }
    return (A) this;
  }

  @Override
  public Vector getUpperLeftCorner() {
    return new Vector(
        this.center.getX() - this.width / 2,
        this.center.getY() + this.height / 2);
  }

  @Override
  @SuppressWarnings("unchecked")
  public A setLowerRightCorner(final Vector p, final boolean resize) {
    if (resize) {
      final Vector c = getUpperLeftCorner();
      setWidth(Math.abs(p.getX() - c.getX()));
      setHeight(Math.abs(p.getY() - c.getY()));
      setCenter(
          (p.getX() + c.getX()) / 2,
          (p.getY() + c.getY()) / 2);
    } else {
      setCenter(new Vector(
          p.getX() - this.width / 2,
          p.getY() + this.height / 2));
    }
    return (A) this;
  }

  @Override
  public Vector getLowerRightCorner() {
    return new Vector(
        this.center.getX() + this.width / 2,
        this.center.getY() - this.height / 2);
  }

  @Override
  @SuppressWarnings("unchecked")
  public A setUpperRightCorner(final Vector p, final boolean resize) {
    if (resize) {
      final Vector c = getLowerLeftCorner();
      setWidth(Math.abs(p.getX() - c.getX()));
      setHeight(Math.abs(p.getY() - c.getY()));
      setCenter((p.getX() + c.getX()) / 2, (p.getY() + c.getY()) / 2);
    } else {
      setCenter(new Vector(
          p.getX() - this.width / 2,
          p.getY() - this.height / 2));
    }
    return (A) this;
  }

  @Override
  public Vector getUpperRightCorner() {
    return new Vector(
        this.center.getX() + this.width / 2,
        this.center.getY() + this.height / 2);
  }
}

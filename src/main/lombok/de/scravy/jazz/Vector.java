package de.scravy.jazz;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import lombok.Value;
import de.scravy.pair.ComparableSerializablePair;
import de.scravy.pair.Pair;
import de.scravy.pair.Pairs;

/**
 * An immutable point in the two dimensional plane.
 */
@Value
public class Vector implements ComparableSerializablePair<Double, Double> {

  private static final long serialVersionUID = 1L;

  /**
   * The origin (a point with the coordinates (0,0).
   */
  public final static Vector ZERO = new Vector(0, 0);

  /**
   * The x coordinate of this point.
   */
  private final double x;

  /**
   * The y coordinate of this point.
   */
  private final double y;

  /**
   * Translate this point by the coordinates of the given point.
   *
   * @param p
   *          The point to be used for translating this points location.
   * @return A new point, a copy of this point translated by p.x and p.y.
   */
  public Vector translate(final Vector p) {
    return translate(p.x, p.y);
  }

  /**
   * Translate this point by the given coordinates.
   *
   * @param x
   *          The horizontal translation.
   * @param y
   *          The vertical translation.
   * @return A new point, a copy of this point translated by x and y.
   */
  public Vector translate(final double x, final double y) {
    return new Vector(this.x + x, this.y + y);
  }

  /**
   * Scales the point as if it was a vector or as if the underlying plane was
   * scaled.
   *
   * @param x
   *          The horizontal scale.
   * @param y
   *          The vertical scale.
   * @return A new point, a copy of this point slaced by the given x and y
   *         values, as if it was a vector.
   */
  public Vector scale(final double x, final double y) {
    return new Vector(this.x * x, this.y * y);
  }

  /**
   * Rotates the underlying plane around the origin.
   *
   * @param angle
   *          The number of degrees (0 to 360, values are taken modulo 360,
   *          negative values are allowed).
   * @return A new point, a copy of this point rotated around the origin by the
   *         given angle.
   */
  public Vector rotate(final double angle) {
    return rotate(ZERO, angle);
  }

  public Vector rotate(final Vector origin, final double angle) {
    final Point2D.Double p = new Point2D.Double(this.x, this.y);
    final Point2D.Double t = new Point2D.Double(0, 0);
    AffineTransform
        .getRotateInstance(angle / 180 * Math.PI, origin.x, origin.y)
        .transform(p, t);
    return new Vector(t.x, t.y);
  }

  public static double angleOfLine(final Vector start, final Vector end) {
    return end.translate(-start.x, -start.y).angleFromOrigin();
  }

  public double distanceTo(final Vector p) {
    return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
  }

  public double distanceFromOrigin() {
    return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
  }

  public double angleFromOrigin() {
    final double result = -(Math.atan2(this.x, this.y) / Math.PI * 180 - 90);
    return result < 0 ? 360 + result : result;
  }

  public static double distance(final Vector p1, final Vector p2) {
    return p1.distanceTo(p2);
  }

  public static double angleBetween(final Vector p1, final Vector p2) {
    return p2.angleFromOrigin() - p1.angleFromOrigin();
  }

  public static void main(final String... args) {
    System.out.println(new Vector(1, -1).angleFromOrigin());
  }

  @Override
  public String toString() {
    return String.format("(%f,%f)", this.x, this.y);
  }

  @Override
  public boolean equals(final Object p) {
    if (p instanceof Pair) {
      return Pairs.equals(this, p);
    }
    if (p instanceof Vector) {
      return this.x == ((Vector) p).x && this.y == ((Vector) p).y;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Pairs.hashCode(this);
  }

  @Override
  public Double getFirst() {
    return this.x;
  }

  @Override
  public Double getSecond() {
    return this.y;
  }

  @Override
  public int compareTo(final ComparableSerializablePair<Double, Double> o) {
    return Pairs.compare(this, o);
  }
}

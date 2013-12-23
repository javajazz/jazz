package jazz;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Objects;

/**
 * An immutable point in the two dimensional plane.
 */
public class Point implements Serializable {

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = -637242503868681706L;

  /**
   * The origin (a point with the coordinates (0,0).
   */
  public final static Point ORIGIN = new Point(0, 0);

  /**
   * The x coordinate of this point.
   */
  public final double x;

  /**
   * The y coordinate of this point.
   */
  public final double y;

  /**
   * Creates a new point with the coordinates (x,y).
   * 
   * @param x
   *          The x coordinate.
   * @param y
   *          The y coordinate.
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return the x coordinate of this point.
   * 
   * @return The x coordinate of this point.
   */
  public double getX() {
    return x;
  }

  /**
   * Return the y coordinate of this point.
   * 
   * @return The y coordinate of this point.
   */
  public double getY() {
    return y;
  }

  /**
   * Translate this point by the coordinates of the given point.
   * 
   * @param p
   *          The point to be used for translating this points location.
   * @return A new point, a copy of this point translated by p.x and p.y.
   */
  public Point translate(Point p) {
    return translate(p.x, p.y);
  }

  public Point translate(double x, double y) {
    return new Point(this.x + x, this.y + y);
  }

  public Point scale(double x, double y) {
    return new Point(this.x * x, this.y * y);
  }

  public Point rotate(double angle) {
    return rotate(ORIGIN, angle);
  }

  public Point rotate(Point origin, double angle) {
    Point2D.Double p = new Point2D.Double(x, y);
    Point2D.Double t = new Point2D.Double(0, 0);
    AffineTransform
        .getRotateInstance(angle / 180 * Math.PI, origin.x, origin.y)
        .transform(p, t);
    return new Point(t.x, t.y);
  }

  public static double angleOfLine(Point start, Point end) {
    return end.translate(-start.x, -start.y).angleFromOrigin();
  }

  public double distanceTo(Point p) {
    return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
  }

  public double distanceFromOrigin() {
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
  }

  public double angleFromOrigin() {
    double result = -(Math.atan2(x, y) / Math.PI * 180 - 90);
    return result < 0 ? 360 + result : result;
  }

  public static double distance(Point p1, Point p2) {
    return p1.distanceTo(p2);
  }

  public static double angleBetween(Point p1, Point p2) {
    return p2.angleFromOrigin() - p1.angleFromOrigin();
  }

  public static void main(String... args) {
    System.out.println(new Point(1, -1).angleFromOrigin());
  }

  @Override
  public String toString() {
    return String.format("(%f,%f)", x, y);
  }

  @Override
  public boolean equals(Object p) {
    if (p instanceof Point) {
      return x == ((Point) p).x && y == ((Point) p).y;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}

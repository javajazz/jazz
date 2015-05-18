package jazz.grids;

import jazz.Event;
import jazz.Picture;
import jazz.Vector;

/**
 * @author Julian Fleischer
 *
 * @param <A>
 * @param <T>
 */
public interface Grid<A, T> {

  /**
   * @return
   */
  Picture getPicture();

  /**
   * @param ev
   */
  void on(final Event ev);

  /**
   * @param p
   * @return
   */
  A setCenter(final Vector p);

  /**
   * @param x
   * @param y
   * @return
   */
  A setCenter(final double x, final double y);

  /**
   * @return
   */
  Vector getCenter();

  /**
   * @param width
   * @return
   */
  A setWidth(final double width);

  /**
   * @return
   */
  double getWidth();

  /**
   * @param height
   * @return
   */
  A setHeight(final double height);

  /**
   * @return
   */
  double getHeight();

  /**
   * @param p
   * @param resize
   * @return
   */
  A setLowerLeftCorner(final Vector p, final boolean resize);

  /**
   * @return
   */
  Vector getLowerLeftCorner();

  /**
   * @param p
   * @param resize
   * @return
   */
  A setUpperLeftCorner(final Vector p, final boolean resize);

  /**
   * @return
   */
  Vector getUpperLeftCorner();

  /**
   * @param p
   * @param resize
   * @return
   */
  A setLowerRightCorner(final Vector p, final boolean resize);

  /**
   * @return
   */
  Vector getLowerRightCorner();

  /**
   * @param p
   * @param resize
   * @return
   */
  A setUpperRightCorner(final Vector p, final boolean resize);

  /**
   * @return
   */
  Vector getUpperRightCorner();
}
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
    void on(Event ev);

    /**
     * @param p
     * @return
     */
    A setCenter(Vector p);

    /**
     * @param x
     * @param y
     * @return
     */
    A setCenter(double x, double y);

    /**
     * @return
     */
    Vector getCenter();

    /**
     * @param width
     * @return
     */
    A setWidth(double width);

    /**
     * @return
     */
    double getWidth();

    /**
     * @param height
     * @return
     */
    A setHeight(double height);

    /**
     * @return
     */
    double getHeight();

    /**
     * @param p
     * @param resize
     * @return
     */
    A setLowerLeftCorner(Vector p, boolean resize);

    /**
     * @return
     */
    Vector getLowerLeftCorner();

    /**
     * @param p
     * @param resize
     * @return
     */
    A setUpperLeftCorner(Vector p, boolean resize);

    /**
     * @return
     */
    Vector getUpperLeftCorner();

    /**
     * @param p
     * @param resize
     * @return
     */
    A setLowerRightCorner(Vector p, boolean resize);

    /**
     * @return
     */
    Vector getLowerRightCorner();

    /**
     * @param p
     * @param resize
     * @return
     */
    A setUpperRightCorner(Vector p, boolean resize);

    /**
     * @return
     */
    Vector getUpperRightCorner();
}
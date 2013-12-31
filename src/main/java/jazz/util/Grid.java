package jazz.util;

import jazz.Event;
import jazz.Picture;
import jazz.Vector;

public interface Grid<A, T> {

	Picture getPicture();

	void on(Event ev);

	A setCenter(Vector p);

	A setCenter(double x, double y);

	Vector getCenter();

	A setWidth(double width);

	double getWidth();

	A setHeight(double height);

	double getHeight();

	A setLowerLeftCorner(Vector p, boolean resize);

	Vector getLowerLeftCorner();

	A setUpperLeftCorner(Vector p, boolean resize);

	Vector getUpperLeftCorner();

	A setLowerRightCorner(Vector p, boolean resize);

	Vector getLowerRightCorner();

	A setUpperRightCorner(Vector p, boolean resize);

	Vector getUpperRightCorner();

}
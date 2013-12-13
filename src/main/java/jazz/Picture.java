package jazz;

import java.awt.geom.AffineTransform;

import jazz.util.Drawable;

public interface Picture extends Cloneable, Drawable {

	Picture color(Color color);

	Picture color(int r, int g, int b);

	Picture color(float h, float s, float v);

	Picture color(int r, int g, int b, double alpha);

	Picture translate(double x, double y);

	Picture rotate(double angle);

	Picture scale(double x, double y);
	
	Picture shear(double x, double y);
	
	Picture stroke(double width);

	Picture flipX();

	Picture flipY();

	Picture filled(boolean filled);

	Picture remove();

	Picture reset();

	Picture transform(AffineTransform transform);

	Picture transform(double m00, double m10, double m01, double m11,
			double m02, double m12);

	Picture clone() throws CloneNotSupportedException;

	AffineTransform getTransform();

}

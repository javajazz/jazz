package jazz;

import java.awt.geom.AffineTransform;

public interface Picture extends Cloneable, Drawable {

    Picture alpha(final double alpha);

    Picture color(final Color color);

    Picture color(final int r, final int g, final int b);

    Picture color(final float h, final float s, final float v);

    Picture color(final int r, final int g, final int b, final double alpha);

    Picture translate(final double x, final double y);

    Picture rotate(final double angle);

    Picture scale(final double x, final double y);

    Picture shear(final double x, final double y);

    Picture stroke(final double width);

    Picture flipX();

    Picture flipY();

    Picture filled(final boolean filled);

    Picture remove();

    Picture reset();

    Picture transform(final AffineTransform transform);

    Picture transform(final double m00, final double m10, final double m01,
            final double m11,
            final double m02, final double m12);

    Picture clone() throws CloneNotSupportedException;

    AffineTransform getTransform();

}

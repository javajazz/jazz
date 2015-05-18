package jazz.pictures;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

import jazz.Mutable;

public abstract class MutableAbstractPicture<P extends MutableAbstractPicture<P>>
    extends AbstractPicture<P> implements Mutable {

  public MutableAbstractPicture(final Shape shape) {
    super(shape);
  }

  @Override
  final public P transform(final AffineTransform transform) {
    return super.transform(transform);
  }

  @Override
  final public P transform(
      final double m00, final double m10,
      final double m01, final double m11,
      final double m02, final double m12) {
    return super.transform(m00, m10, m01, m11, m02, m12);
  }
}

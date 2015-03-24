package jazz.pictures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

import jazz.Color;
import jazz.Picture;
import jazz.pictures.mutable.Pictures;

public class UnmodifieablePictures implements Picture {

    private final Pictures p;

    public UnmodifieablePictures(final Pictures p) {
        this.p = p;
    }

    public <P extends Picture> P get(final int index, final Class<P> clazz) {
        return p.get(index, clazz);
    }

    public Picture get(final int index) {
        return p.get(index);
    }

    @Override
    public int hashCode() {
        return p.hashCode();
    }

    @Override
    public String toString() {
        return p.toString();
    }

    public Iterator<Picture> iterator() {
        return p.iterator();
    }

    @Override
    public boolean equals(final Object obj) {
        return p.equals(obj);
    }

    @Override
    public final AffineTransform getTransform() {
        return p.getTransform();
    }

    @Override
    public final void draw(final Graphics2D g2d) {
        p.draw(g2d);
    }

    @Override
    public UnmodifieablePictures clone() {
        return new UnmodifieablePictures(p);
    }

    @Override
    public UnmodifieablePictures alpha(final double alpha) {
        return this;
    }

    @Override
    public UnmodifieablePictures color(final Color color) {
        return this;
    }

    @Override
    public UnmodifieablePictures color(final int r, final int g, final int b) {
        return this;
    }

    @Override
    public UnmodifieablePictures color(final float h, final float s,
            final float v) {
        return this;
    }

    @Override
    public UnmodifieablePictures color(final int r, final int g, final int b,
            final double alpha) {
        return this;
    }

    @Override
    public UnmodifieablePictures translate(final double x, final double y) {
        return this;
    }

    @Override
    public UnmodifieablePictures rotate(final double angle) {
        return this;
    }

    @Override
    public UnmodifieablePictures scale(final double x, final double y) {
        return this;
    }

    @Override
    public UnmodifieablePictures shear(final double x, final double y) {
        return this;
    }

    @Override
    public UnmodifieablePictures stroke(final double width) {
        return this;
    }

    @Override
    public UnmodifieablePictures flipX() {
        return this;
    }

    @Override
    public UnmodifieablePictures flipY() {
        return this;
    }

    @Override
    public UnmodifieablePictures filled(final boolean filled) {
        return this;
    }

    @Override
    public UnmodifieablePictures remove() {
        return this;
    }

    @Override
    public UnmodifieablePictures reset() {
        return this;
    }

    @Override
    public UnmodifieablePictures transform(final AffineTransform transform) {
        return this;
    }

    @Override
    public UnmodifieablePictures transform(final double m00, final double m10,
            final double m01,
            final double m11,
            final double m02, final double m12) {
        return this;
    }
}

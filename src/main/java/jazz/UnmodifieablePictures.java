package jazz;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

public class UnmodifieablePictures implements Picture {

	private final Pictures p;
	
	UnmodifieablePictures(Pictures p) {
		this.p = p;
	}

	public <P extends Picture> P get(int index, Class<P> clazz) {
		return p.get(index, clazz);
	}

	public Picture get(int index) {
		return p.get(index);
	}

	public int hashCode() {
		return p.hashCode();
	}

	public String toString() {
		return p.toString();
	}

	public Iterator<Picture> iterator() {
		return p.iterator();
	}

	public boolean equals(Object obj) {
		return p.equals(obj);
	}

	public final AffineTransform getTransform() {
		return p.getTransform();
	}

	public final void draw(Graphics2D g2d) {
		p.draw(g2d);
	}

	public UnmodifieablePictures clone() {
		return new UnmodifieablePictures(p);
	}

	@Override
	public UnmodifieablePictures alpha(double alpha) {
		return this;
	}

	@Override
	public UnmodifieablePictures color(Color color) {
		return this;
	}

	@Override
	public UnmodifieablePictures color(int r, int g, int b) {
		return this;
	}

	@Override
	public UnmodifieablePictures color(float h, float s, float v) {
		return this;
	}

	@Override
	public UnmodifieablePictures color(int r, int g, int b, double alpha) {
		return this;
	}

	@Override
	public UnmodifieablePictures translate(double x, double y) {
		return this;
	}

	@Override
	public UnmodifieablePictures rotate(double angle) {
		return this;
	}

	@Override
	public UnmodifieablePictures scale(double x, double y) {
		return this;
	}

	@Override
	public UnmodifieablePictures shear(double x, double y) {
		return this;
	}

	@Override
	public UnmodifieablePictures stroke(double width) {
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
	public UnmodifieablePictures filled(boolean filled) {
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
	public UnmodifieablePictures transform(AffineTransform transform) {
		return this;
	}

	@Override
	public UnmodifieablePictures transform(double m00, double m10, double m01, double m11,
			double m02, double m12) {
		return this;
	}
}

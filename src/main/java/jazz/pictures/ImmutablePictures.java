package jazz.pictures;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

import jazz.Color;
import jazz.Picture;

public class ImmutablePictures implements Picture {

	private final Pictures p;
	
	ImmutablePictures(Pictures p) {
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

	public ImmutablePictures clone() {
		return new ImmutablePictures(p);
	}

	@Override
	public ImmutablePictures alpha(double alpha) {
		return this;
	}

	@Override
	public ImmutablePictures color(Color color) {
		return this;
	}

	@Override
	public ImmutablePictures color(int r, int g, int b) {
		return this;
	}

	@Override
	public ImmutablePictures color(float h, float s, float v) {
		return this;
	}

	@Override
	public ImmutablePictures color(int r, int g, int b, double alpha) {
		return this;
	}

	@Override
	public ImmutablePictures translate(double x, double y) {
		return this;
	}

	@Override
	public ImmutablePictures rotate(double angle) {
		return this;
	}

	@Override
	public ImmutablePictures scale(double x, double y) {
		return this;
	}

	@Override
	public ImmutablePictures shear(double x, double y) {
		return this;
	}

	@Override
	public ImmutablePictures stroke(double width) {
		return this;
	}

	@Override
	public ImmutablePictures flipX() {
		return this;
	}

	@Override
	public ImmutablePictures flipY() {
		return this;
	}

	@Override
	public ImmutablePictures filled(boolean filled) {
		return this;
	}

	@Override
	public ImmutablePictures remove() {
		return this;
	}

	@Override
	public ImmutablePictures reset() {
		return this;
	}

	@Override
	public ImmutablePictures transform(AffineTransform transform) {
		return this;
	}

	@Override
	public ImmutablePictures transform(double m00, double m10, double m01, double m11,
			double m02, double m12) {
		return this;
	}
}

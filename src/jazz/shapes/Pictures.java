package jazz.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import jazz.Picture;

public class Pictures extends MutableAbstractPicture<Pictures> implements Iterable<Picture> {

	private final List<Picture> pictures = new ArrayList<>();

	private Pictures() {
		super(null);
	}

	public Pictures(Collection<? extends Picture> pictures) {
		this();
		this.pictures.addAll(pictures);
	}

	public Pictures add(Picture pic) {
		this.pictures.add(pic);
		return this;
	}

	public Pictures remove(int index) {
		try {
			this.pictures.remove(index);
		} catch (IndexOutOfBoundsException exc) {
			// do nothing
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public <P extends Picture> P get(int index, Class<P> clazz) {
		try {
			Picture p = pictures.get(index);
			if (p == null) {
				return null;
			}
			if (clazz.isAssignableFrom(p.getClass())) {
				return (P) p;
			}
			return null;
		} catch (IndexOutOfBoundsException exc) {
			return null;
		}
	}

	public Picture get(int index) {
		try {
			return pictures.get(index);
		} catch (IndexOutOfBoundsException exc) {
			return null;
		}
	}

	@SafeVarargs
	public Pictures(Picture... pictures) {
		this(Arrays.asList(pictures));
	}

	@Override
	void doDraw(Graphics2D g2d) {
		if (color != null) {
			g2d.setColor(color);
		}
		g2d.setTransform(getTransform(g2d.getTransform()));
		for (Picture picture : pictures) {
			AffineTransform savedTransform = g2d.getTransform();
			Color savedColor = g2d.getColor();
			picture.draw(g2d);
			g2d.setColor(savedColor);
			g2d.setTransform(savedTransform);
		}
	}

	public String toString() {
		return pictures.toString();
	}

	@Override
	public Pictures clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Picture> iterator() {
		return pictures.iterator();
	}
}

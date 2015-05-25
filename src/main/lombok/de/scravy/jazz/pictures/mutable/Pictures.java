package de.scravy.jazz.pictures.mutable;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import de.scravy.jazz.Picture;
import de.scravy.jazz.pictures.MutableAbstractPicture;
import de.scravy.jazz.pictures.UnmodifieablePictures;

public final class Pictures extends MutableAbstractPicture<Pictures> implements
    Iterable<Picture> {

  private static final long serialVersionUID = 1L;

  private final List<Picture> pictures = new ArrayList<>();

  private Pictures() {
    super(null);
  }

  public Pictures(final Collection<? extends Picture> pictures) {
    this();
    this.pictures.addAll(pictures);
  }

  public UnmodifieablePictures getImmutable() {
    return new UnmodifieablePictures(this);
  }

  public Pictures add(final Picture pic) {
    this.pictures.add(pic);
    return this;
  }

  public Pictures remove(final int index) {
    try {
      this.pictures.remove(index);
    } catch (final IndexOutOfBoundsException exc) {
      // do nothing
    }
    return this;
  }

  @SuppressWarnings("unchecked")
  public <P extends Picture> P get(final int index, final Class<P> clazz) {
    try {
      final Picture p = this.pictures.get(index);
      if (p == null) {
        return null;
      }
      if (clazz.isAssignableFrom(p.getClass())) {
        return (P) p;
      }
      return null;
    } catch (final IndexOutOfBoundsException exc) {
      return null;
    }
  }

  public Picture get(final int index) {
    try {
      return this.pictures.get(index);
    } catch (final IndexOutOfBoundsException exc) {
      return null;
    }
  }

  @SafeVarargs
  public Pictures(final Picture... pictures) {
    this(Arrays.asList(pictures));
  }

  @Override
  protected void doDraw(final Graphics2D g2d) {
    if (this.color != null) {
      g2d.setColor(this.color);
    }
    g2d.setTransform(getTransform(g2d.getTransform()));
    for (final Picture picture : this.pictures) {
      if (picture != null) {
        final AffineTransform savedTransform = g2d.getTransform();
        final Composite alpha = g2d.getComposite();
        final Stroke stroke = g2d.getStroke();
        final Color savedColor = g2d.getColor();
        picture.draw(g2d);
        g2d.setColor(savedColor);
        g2d.setStroke(stroke);
        g2d.setComposite(alpha);
        g2d.setTransform(savedTransform);
      }
    }
  }

  @Override
  public String toString() {
    return this.pictures.toString();
  }

  @Override
  public Pictures clone() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterator<Picture> iterator() {
    return this.pictures.iterator();
  }
}

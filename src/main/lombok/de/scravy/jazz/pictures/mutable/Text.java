package de.scravy.jazz.pictures.mutable;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import de.scravy.jazz.pictures.MutableAbstractPicture;

public final class Text extends MutableAbstractPicture<Text> {

  private static final long serialVersionUID = 1L;

  private final String text;
  private Rectangle2D bounds = null;

  public Text(final String text) {
    super(null);
    this.text = text;
  }

  @Override
  protected void doDraw(final Graphics2D g2d) {
    if (this.bounds == null) {
      this.bounds = g2d.getFontMetrics().getStringBounds(this.text, g2d);
    }
    final AffineTransform transform = getTransform(g2d.getTransform());

    transform.concatenate(AffineTransform.getTranslateInstance(
        this.bounds.getWidth() / -2, this.bounds.getHeight() / -2));
    transform.concatenate(AffineTransform.getScaleInstance(1, -1));
    g2d.setTransform(transform);

    doRender(g2d);
  }

  @Override
  protected void doRender(final Graphics2D g2d) {
    if (this.color != null) {
      g2d.setColor(this.color);
    }
    g2d.drawString(this.text, 0, 0);
  }

  @Override
  public Text clone() {
    return doClone(new Text(this.text));
  }

}

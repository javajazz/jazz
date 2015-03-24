package jazz.pictures.mutable;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import jazz.pictures.MutableAbstractPicture;

public final class Text extends MutableAbstractPicture<Text> {

    final String text;
    Rectangle2D bounds = null;

    public Text(String text) {
        super(null);
        this.text = text;
    }

    protected void doDraw(Graphics2D g2d) {
        if (bounds == null) {
            bounds = g2d.getFontMetrics().getStringBounds(text, g2d);
        }
        AffineTransform transform = getTransform(g2d.getTransform());

        transform.concatenate(AffineTransform.getTranslateInstance(
                bounds.getWidth() / -2, bounds.getHeight() / -2));
        transform.concatenate(AffineTransform.getScaleInstance(1, -1));
        g2d.setTransform(transform);

        doRender(g2d);
    }

    @Override
    protected void doRender(Graphics2D g2d) {
        if (color != null) {
            g2d.setColor(color);
        }
        g2d.drawString(text, 0, 0);
    }

    @Override
    public Text clone() {
        return doClone(new Text(text));
    }

}

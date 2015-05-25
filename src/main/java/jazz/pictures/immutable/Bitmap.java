package jazz.pictures.immutable;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import de.scravy.jazz.util.ImageLoader;
import jazz.pictures.ImmutableAbstractPicture;

public final class Bitmap extends ImmutableAbstractPicture<Bitmap> {

  private final BufferedImage bufferedImage;

  public Bitmap(final Class<?> clazz, final String resource)
      throws IOException {
    this(ImageLoader.loadImage(clazz.getResourceAsStream(resource),
        resource));
  }

  public Bitmap(final String fileName) throws IOException {
    this(ImageLoader
        .loadImage(fileName == null ? null : new File(fileName)));
  }

  private Bitmap(final BufferedImage bufferedImage) {
    super(new Rectangle2D.Double(0, 0, bufferedImage.getWidth(),
        bufferedImage.getHeight()));
    this.bufferedImage = bufferedImage;
  }

  @Override
  protected void doRender(final Graphics2D g2d) {

    g2d.drawImage(bufferedImage, new AffineTransform(),
        new ImageObserver() {
          @Override
          public boolean imageUpdate(
              final Image img, final int infoflags, final int x,
              final int y, final int width,
              final int height) {
            return true;
          }
        });
  }

  @Override
  public Bitmap clone() {
    return doClone(new Bitmap(bufferedImage));
  }

}

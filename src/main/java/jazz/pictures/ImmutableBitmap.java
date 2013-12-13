package jazz.pictures;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public final class ImmutableBitmap extends ImmutableAbstractPicture<ImmutableBitmap> {

	private final BufferedImage bufferedImage;

	public ImmutableBitmap(Class<?> clazz, String resource) throws IOException {
		this(ImageLoader.loadImage(clazz.getResourceAsStream(resource), resource));
	}

	public ImmutableBitmap(final String fileName) throws IOException {
		this(ImageLoader.loadImage(fileName == null ? null : new File(fileName)));
	}

	private ImmutableBitmap(BufferedImage bufferedImage) {
		super(new Rectangle2D.Double(0, 0, bufferedImage.getWidth(),
				bufferedImage.getHeight()));
		this.bufferedImage = bufferedImage;
	}

	@Override
	void doRender(Graphics2D g2d) {

		g2d.drawImage(bufferedImage, new AffineTransform(),
				new ImageObserver() {
					@Override
					public boolean imageUpdate(
							Image img, int infoflags, int x, int y, int width,
							int height) {
						return true;
					}
				});
	}

	@Override
	public ImmutableBitmap clone() {
		return doClone(new ImmutableBitmap(bufferedImage));
	}

}

package jazz.pictures;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;

final class ImageLoader {

	static BufferedImage loadImage(final File file) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException(
					"The `fileName` must not be null.");
		}
		if (!file.exists()) {
			throw new IOException(String.format(
					"The file `%s` does not exist",
					file.getAbsolutePath()));
		}
		if (!file.canRead()) {
			throw new IOException(String.format(
					"The file `%s` does exist but may not be read.",
					file.getAbsolutePath()));
		}
		return loadImage(new FileInputStream(file), file.getName());
	}

	static BufferedImage loadImage(
			final InputStream stream,
			final String name) throws IOException {

		if (stream == null) {
			throw new IOException(String.format(
					"The supplied resource `%s` could not be found.", name));
		}

		final String[] suffixes = name.split("\\.");
		final String suffix = suffixes[suffixes.length - 1];

		String mimeType = "";
		switch (suffix.toUpperCase()) {
		case "PNG":
			mimeType = "image/png";
			break;
		case "JPG":
		case "JPEG":
			mimeType = "image/jpeg";
			break;
		}

		final Iterator<ImageReader> imageReaders =
				ImageIO.getImageReadersByMIMEType(mimeType);

		if (!imageReaders.hasNext()) {
			throw new IllegalArgumentException();
		}
		final ImageReader imageReader = imageReaders.next();

		imageReader.setInput(new MemoryCacheImageInputStream(stream));
		BufferedImage image = imageReader.read(0);

		AffineTransform transform = AffineTransform.getScaleInstance(1, -1);
		transform.concatenate(AffineTransform.getTranslateInstance(0,
				-image.getHeight()));

		AffineTransformOp transformOp = new AffineTransformOp(
				transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage bufferedImage = new BufferedImage(
				image.getWidth(), image.getHeight(), image.getType());
		transformOp.filter(image, bufferedImage);

		return bufferedImage;
	}

}

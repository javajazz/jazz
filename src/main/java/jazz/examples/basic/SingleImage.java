package jazz.examples.basic;

import java.io.IOException;

import jazz.Color;
import jazz.Jazz;
import jazz.pictures.mutable.Bitmap;
import jazz.pictures.mutable.Circle;
import jazz.pictures.mutable.Pictures;

/**
 * An example that shows a single translucent image on top of a circle.
 * 
 * @author Julian Fleischer
 */
public class SingleImage {

	/**
	 * main method.
	 * 
	 * @param args
	 *            All arguments are ignored.
	 * @throws IOException
	 *             If the image could not be loaded.
	 */
	public static void main(String... args) throws IOException {

		Jazz.display(
				"On this page you see a little girl giggling at a Hippopotamus",
				1400, 900, new Pictures(
						new Circle(40).color(Color.RED).stroke(5),
						new Bitmap(SingleImage.class, "hippo.png")
								.alpha(0.1)));

	}
}

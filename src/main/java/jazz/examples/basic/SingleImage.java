package jazz.examples.basic;

import java.io.IOException;

import jazz.Animation;
import jazz.Color;
import jazz.Jazz;
import jazz.Picture;
import jazz.pictures.mutable.Bitmap;
import jazz.pictures.mutable.Circle;
import jazz.pictures.mutable.Pictures;

public class SingleImage {

	public static void main(String... args) throws IOException {

		Jazz.animate(
				"On this page you see a little girl giggling at a Hippopotamus",
				1400, 900, new Animation() {

					Picture pictures = new Pictures(
							new Circle(40).color(Color.RED).stroke(5),
							new Bitmap(SingleImage.class, "hippo.png")
									.alpha(0.1));

					@Override
					public void update(double time, double delta) {

					}

					@Override
					public Picture getPicture() {
						return pictures;
					}

				}).maxFps(80);

	}
}

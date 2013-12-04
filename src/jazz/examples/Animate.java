package jazz.examples;

import jazz.Animation;
import jazz.Jazz;
import jazz.Picture;
import jazz.shapes.Pictures;
import jazz.shapes.Rectangle;

public class Animate {

	public static void main(String... args) {
		Jazz
				.display(
						"Simple Animation",
						1000, 600,
						new Animation() {

							private Pictures pictures = new Pictures(
									new Rectangle(200, 200).color(0, 0, 0, 0.5)
											.filled(true).rotate(0),
									new Rectangle(200, 200).color(0, 0, 0, 0.5)
											.filled(true).rotate(0)
							);
							
							@Override
							public void update(double time) {
								pictures.get(0).remove().rotate(time * 40);
								pictures.get(1).remove().rotate(time * -40);
							}

							@Override
							public Picture getPicture() {
								return pictures;
							}
						})
				.onClose(new Runnable() {
					public void run() {
						System.exit(0);
					}
				})
				.antialias(true);
		;
	}
}

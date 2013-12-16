package jazz.examples;

import jazz.Animation;
import jazz.Jazz;
import jazz.Picture;
import jazz.pictures.Pictures;
import jazz.pictures.Polygon;

public class Polygons2 {

	public static void main(String... args) {
		Jazz.animate(
				"Woohoo", 0, 480, new Animation() {

					Pictures pictures = new Pictures();
					
					{
						for (int i = 3; i < 15; i++) {
							pictures.add(new Polygon(i, (i - 2) * 25));
						}
					}
					
					@Override
					public void update(double time, double delta) {
						double i = 30;
						for (Picture p : pictures) {
							p.reset().rotate(time * i);
							i *= -1;
						}
					}

					@Override
					public Picture getPicture() {
						return pictures;
					}
				}).onClose(new Runnable() {
					@Override
					public void run() {
						System.exit(0);
					}
				}).maxFps(120);
	}

}

package jazz.example;

import jazz.Picture;
import jazz.RandomColor;
import jazz.World;
import jazz.shapes.Rectangle;

public class TombWorld extends World {

	public TombWorld() {
		final int size = 10;

		Rectangle r = new Rectangle(size, size).filled(true);

		for (int i = -550; i <= 550; i += size) {
			for (int j = -550; j <= 550; j += size) {
				pictures.add(r.clone().color(new RandomColor(50, 200)).translate(i, j));
			}
		}
	}

	@Override
	public void update(double time) {
		pictures.reset()
				.scale(4 + 3 * Math.sin(time * 1.5), 4 + 3 * Math.sin(time * 1.5))
				.rotate(Math.cos(time * 0.5) * 360);
	}

}

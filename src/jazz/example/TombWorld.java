package jazz.example;

import jazz.Picture;
import jazz.RandomColor;
import jazz.World;
import jazz.shapes.Pictures;
import jazz.shapes.Rectangle;

public class TombWorld extends World {

	Pictures pictures = new Pictures();

	public TombWorld() {
		Rectangle r = new Rectangle(50, 50).filled(true);

		for (int i = -525; i <= 525; i += 50) {
			for (int j = -325; j <= 325; j += 50) {
				pictures.add(r.clone().color(new RandomColor()).translate(i, j));
			}
		}
	}

	@Override
	public void update(double time) {
		for (Picture p : pictures) {
			p.color(new RandomColor());
		}
		pictures.reset()
				.scale(2 * Math.sin(time), 2 * Math.sin(time))
				.rotate(time * 100);
	}

	@Override
	public Picture getPicture() {
		return pictures;
	}

}

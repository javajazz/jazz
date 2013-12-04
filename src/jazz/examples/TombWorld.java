package jazz.examples;

import jazz.Event;
import jazz.RandomColor;
import jazz.World;
import jazz.shapes.Rectangle;

public class TombWorld extends World {

	public TombWorld() {
		final int size = 10;

		Rectangle r = new Rectangle(size, size).filled(true);

		for (int i = -550; i <= 550; i += size) {
			for (int j = -550; j <= 550; j += size) {
				pictures.add(r.clone().color(new RandomColor(0, 150))
						.translate(i, j));
			}
		}
	}

	@Override
	public void on(Event e) {
		if (e.getType() == Event.Type.CLICK) {
			e.getWindow().close();
		}
	}

	@Override
	public void update(double time) {
		pictures.reset()
				.scale(4 + 3 * Math.sin(time * 1.5),
						4 + 3 * Math.sin(time * 1.5))
				.rotate(Math.cos(time * 0.5) * 360);
	}

}
